package com.pavswap.LearningSpringBoot.colours.implementation;

import com.pavswap.LearningSpringBoot.colours.Hello;
import com.pavswap.LearningSpringBoot.colours.HelloRunner;

public class HelloRunnerImpli implements HelloRunner {

    Hello hello;

    public HelloRunnerImpli() {
        this.hello = new EnglishHello();
    }
    @Override
    public String sayHello() {
        return hello.print();
    }
}
