```
dropdb disney_characters
createdb disney_characters
```

With line 15 of the `MainSeeder` commented out (so as not to seed characters), run `./mvnw spring-boot:run` to boot up the server.

### Provided Code

- Migrations
- Entities (sans joins)
- Seeders (with CharactersSeeder commented out in Main Seeder)

### Create FilmsApiV1Controller index API endpoint

1. Render an index API endpoint that simply returns data (no map)
```
@GetMapping
public Iterable<Film> getList() {
  return filmService.findAll();
}
```
2. Update the index API endpoint to nest the data
```
@GetMapping
public Map<String, Iterable<Film>> getList() {
  Map<String, Iterable<Film>> map = new HashMap<>();
  map.put("films", filmService.findAll());
  return map;
}
```

### Create Character Joins

1. Create without @JsonIgnoreProperties first

2. Uncomment `CharactersSeeder` in `MainSeeder`, shut down and restart your server.
  - Walk them into the circular reference 
  - Talk about how it's only necessary when you have the two-way join

3. Add @JsonIgnoreProperties and see the characters appear on both index and show endpoint
  - Mention how Serializers, tonight, will show them how to hide that from the index API endpoint

```
@ManyToOne
@JoinColumn(name="film_id", nullable = false)
@JsonIgnoreProperties("characters")
private Film film;
```

```
@OneToMany(mappedBy = "film")
@JsonIgnoreProperties("film")
private List<Character> characters;
```


### Update to use PagingAndSortingRepository

1. Create `FilmRestRepository`
```
...

@Repository
public interface FilmRestRepository extends PagingAndSortingRepository<Film, Integer> {
}
```

2. Create `FilmRestService`
```
...

@Service
public class FilmRestService {
  private FilmRestRepository filmRepository;

  @Autowired
  public FilmRestService(FilmRestRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public Page<Film> findAll(Pageable pageable) {
    return filmRepository.findAll(pageable);
  }
}
```

3. Autowire this service to controller
```
private FilmService filmService;
private FilmRestService filmRestService;

@Autowired
public FilmsApiV1Controller(FilmService filmService, FilmRestService filmRestService) {
  this.filmService = filmService;
  this.filmRestService = filmRestService;
}
```

4. Comment existing Index API endpoint and replace with a pageable one:
```
@GetMapping
public Map<String, Page<Film>> getList(Pageable pageable) {
  Map<String, Page<Film>> map = new HashMap<>();
  map.put("films", filmRestService.findAll(pageable));
  return map;
}
```

5. Navigate to http://localhost:8080/api/v1/films?size=2 and then http://localhost:8080/api/v1/films?size=2&page=2 to see results
  - Talk about WHY paging is important from a backend data to frontend perspective - use social media apps as an example of how paging works

### IF TIME: Create Show API endpoint

1. Add a show API endpoint
```
@GetMapping("/{id}")
public Map<String, Optional<Film>> getFilmById(@PathVariable Integer id) {
  Map<String, Optional<Film>> map = new HashMap<>();
  map.put("film", filmService.findById(id));
  return map;
}
```
2. Talk about what happens if we try to go to a film that doesn't exist -- we want a 404!!

3. Update to return a 404 if none is found:
```
@NoArgsConstructor
private class FilmNotFoundException extends RuntimeException {};

@ControllerAdvice
private class FilmNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(FilmNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String filmNotFoundHandler(FilmNotFoundException ex) {
    return ex.getMessage();
  }
}

@GetMapping("/{id}")
public Film getOne(@PathVariable Integer id) {
  return filmRestService.findById(id).orElseThrow(() -> new FilmNotFoundException());
}
```