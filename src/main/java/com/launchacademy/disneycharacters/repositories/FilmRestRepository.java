package com.launchacademy.disneycharacters.repositories;

import com.launchacademy.disneycharacters.models.Film;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRestRepository extends PagingAndSortingRepository<Film, Integer> {

}
