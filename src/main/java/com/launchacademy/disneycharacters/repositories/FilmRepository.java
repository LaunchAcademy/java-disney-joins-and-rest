package com.launchacademy.disneycharacters.repositories;

import com.launchacademy.disneycharacters.models.Film;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
  public List<Film> getByTitle(String title);
}

