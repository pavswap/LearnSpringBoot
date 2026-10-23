/*
 * Single-file Spring Boot 4 CLI app: Spring Web + OpenAI Chat Completions + CommandLineRunner.
 *
 * Required dependency (generate the project at start.spring.io with Boot 4.x, Java 17+):
 *
 *   <dependency>
 *       <groupId>org.springframework.boot</groupId>
 *       <artifactId>spring-boot-starter-webmvc</artifactId>
 *   </dependency>
 *
 * Put this file in src/main/java (default package is fine), then:
 *
 *   export OPENAI_API_KEY=sk-...
 *   export OPENAI_MODEL=gpt-4o-mini      # optional
 *   ./mvnw package && java -jar target/*.jar
 */

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class OpenAiCliApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpenAiCliApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE); // pure CLI, no Tomcat
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    // ---- OpenAI client -------------------------------------------------------

    record Message(String role, String content) {}
    record ChatRequest(String model, List<Message> messages) {}
    record Choice(Message message) {}
    record ChatResponse(List<Choice> choices) {}

    static class OpenAiClient {
        private final RestClient http;
        private final String model;

        OpenAiClient(String apiKey, String model) {
            this.model = model;
            this.http = RestClient.builder()
                    .baseUrl("https://api.openai.com/v1")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .build();
        }

        String chat(List<Message> messages) {
            ChatResponse response = http.post()
                    .uri("/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ChatRequest(model, messages))
                    .retrieve()
                    .body(ChatResponse.class);
            return response.choices().get(0).message().content();
        }
    }

    @Bean
    OpenAiClient openAiClient() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Set the OPENAI_API_KEY environment variable.");
        }
        String model = System.getenv().getOrDefault("OPENAI_MODEL", "gpt-4o-mini");
        return new OpenAiClient(apiKey, model);
    }

    // ---- CLI loop ------------------------------------------------------------

    @Bean
    CommandLineRunner chatRunner(OpenAiClient openAi) {
        return args -> {
            List<Message> history = new ArrayList<>();
            history.add(new Message("system", "You are a helpful assistant."));

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ask anything (type 'exit' to quit).");

            while (true) {
                System.out.print("\n> ");
                if (!scanner.hasNextLine()) break;
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;
                if (input.equalsIgnoreCase("exit")) break;

                history.add(new Message("user", input));
                try {
                    String reply = openAi.chat(history);
                    history.add(new Message("assistant", reply));
                    System.out.println("\n" + reply);
                } catch (Exception e) {
                    history.remove(history.size() - 1); // drop the failed prompt
                    System.err.println("Request failed: " + e.getMessage());
                }
            }
        };
    }
}
