package com.launchacademy.disneycharacters.services;

import com.launchacademy.disneycharacters.models.Character;
import com.launchacademy.disneycharacters.repositories.CharacterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
  private CharacterRepository characterRepository;

  @Autowired
  public CharacterService(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  public List<Character> findAll() {
    return (List<Character>)characterRepository.findAll();
  }

  public void save(Character character) {
    characterRepository.save(character);
  }

  public long count() {
    return characterRepository.count();
  }
}