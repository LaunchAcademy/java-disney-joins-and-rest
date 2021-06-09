package com.launchacademy.disneycharacters.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {
  @Autowired CharactersSeeder charactersSeeder;
  @Autowired FilmsSeeder filmsSeeder;

  @Override
  public void run(String... args) throws Exception {
    filmsSeeder.seed();
    charactersSeeder.seed();
  }
}
