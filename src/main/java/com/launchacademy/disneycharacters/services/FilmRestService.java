package com.launchacademy.disneycharacters.services;

import com.launchacademy.disneycharacters.models.Film;
import com.launchacademy.disneycharacters.repositories.FilmRestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmRestService {
  private FilmRestRepository filmRestRepository;

  @Autowired
  public FilmRestService(FilmRestRepository filmRestRepository) {
    this.filmRestRepository = filmRestRepository;
  }

  public Page<Film> findAll(Pageable pageable) {
    return filmRestRepository.findAll(pageable);
  }
}
