package com.launchacademy.disneycharacters.services;

import com.launchacademy.disneycharacters.models.Film;
import com.launchacademy.disneycharacters.repositories.FilmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
  private FilmRepository filmRepository;

  @Autowired
  public FilmService(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public List<Film> findAll() {
    return (List<Film>)filmRepository.findAll();
  }

  public Optional<Film> findById(Integer id) {
    return filmRepository.findById(id);
  }

  public void save(Film film) {
    filmRepository.save(film);
  }

  public long count() {
    return filmRepository.count();
  }

  public List<Film> getByTitle(String title) {
    return filmRepository.getByTitle(title);
  };
}
