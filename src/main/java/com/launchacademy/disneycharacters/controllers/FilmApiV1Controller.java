package com.launchacademy.disneycharacters.controllers;

import com.launchacademy.disneycharacters.models.Film;
import com.launchacademy.disneycharacters.services.FilmRestService;
import com.launchacademy.disneycharacters.services.FilmService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/films")
public class FilmApiV1Controller {
  private FilmService filmService;
  private FilmRestService filmRestService;

  @Autowired
  public FilmApiV1Controller(FilmService filmService, FilmRestService filmRestService) {
    this.filmService = filmService;
    this.filmRestService = filmRestService;
  }

//  @GetMapping
//  public Iterable<Film> getFilmsApi() {
//    return filmService.findAll();
//  }

//  @GetMapping
//  public Map<String, Iterable<Film>> getFilmsApi() {
//    Map<String, Iterable<Film>> dataMap = new HashMap<>();
//    dataMap.put("films", filmService.findAll());
//    return dataMap;
//  }

  @GetMapping
  public Map<String, Page<Film>> getFilmsApi(Pageable pageable) {
    Map<String, Page<Film>> dataMap = new HashMap<>();
    dataMap.put("films", filmRestService.findAll(pageable));
    return dataMap;
  }
}
