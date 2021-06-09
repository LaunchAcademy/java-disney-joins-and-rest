package com.launchacademy.disneycharacters.repositories;

import com.launchacademy.disneycharacters.models.Character;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Integer> {

}
