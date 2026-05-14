package com.SpringJDBC.CLIDBAccessTool;

import com.SpringJDBC.DAO.Player;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class ClidbAccessToolApplication implements CommandLineRunner {

	PlayerService playerService;

	ClidbAccessToolApplication(PlayerService playerService) {
		this.playerService = playerService;
	}

	public static void main(String[] args) {

		SpringApplication.run(ClidbAccessToolApplication.class, args);
		System.out.println("Running Applcation");
	}

	@Override
	public void run(String... args) throws Exception {

		var player = new Player(29, "BalticRacer", -1);
		playerService.updatePlayerName(player);
	}
}

@Service
class PlayerService {

	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {

		this.playerRepository = playerRepository;
	}

	public void addPlayer(Player player) {
		playerRepository.create(player);
	}

	public void updatePlayerName(Player player) {
		playerRepository.update(player);
	}
}

@Repository
class PlayerRepository {

	private final JdbcTemplate jdbcTemplate;

	public PlayerRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void create(Player p) {

		String sql = "INSERT INTO players (id, p_name, p_rank) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, p.getId(), p.getP_name(), p.getP_rank());
	}

	public void update(Player p) {

		String sql = "UPDATE players SET p_name = ? WHERE id = ?";
		jdbcTemplate.update(sql, p.getP_name(), p.getId());
	}
}



