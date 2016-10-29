package config;

import com.vklindukhov.dao.ActorRepository;
import com.vklindukhov.dao.MovieRepository;
import com.vklindukhov.dao.SearchLogRepository;
import com.vklindukhov.entity.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.*;

import static com.vklindukhov.entity.Category.ADVENTURE;
import static com.vklindukhov.entity.Category.CRIME;
import static com.vklindukhov.entity.Category.DRAM;
import static com.vklindukhov.entity.Language.RUSSIAN;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@WebAppConfiguration
public class RepositoryTest {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SearchLogRepository searchLogRepository;
    private List<Movie> movies;

    @Before
    @Rollback(false)
    public void setUp() {
        Movie durak = new Movie(
                "Дурак",
                "Дурак Description",
                DRAM.name,
                RUSSIAN.name,
                Arrays.asList(new Actor("Артём Быстров"), new Actor("Наталья Суркова"))
        );
        durak.setReleaseYear((short) 2014);
        durak.setLength((short) 7153);
        durak.setRating(8.2f);

        Movie kollektor = new Movie(
                "Коллектор",
                "Коллектор Description",
                DRAM.name,
                RUSSIAN.name,
                Collections.singletonList(new Actor("Константин Хабенский")));
        kollektor.setReleaseYear((short) 2016);
        kollektor.setLength((short) 4382);
        kollektor.setRating(7.6f);

        Movie duelant = new Movie(
                "Дуэлянт",
                "Дуэлянт Description",
                ADVENTURE.name,
                RUSSIAN.name,
                Arrays.asList(new Actor("Пётр Фёдоров"), new Actor("Владимир Машков"), new Actor("Юлия Хлынина"), new Actor("Сергей Гармаш")
                )
        );
        duelant.setReleaseYear((short) 2016);
        duelant.setLength((short) 7053);
        duelant.setRating(7.2f);

        Movie bespredel = new Movie(
                "Беспредел",
                "Беспредел Description",
                CRIME.name,
                RUSSIAN.name,
                Arrays.asList(new Actor("Лев Дуров"), new Actor("Сергей Гармаш"), new Actor("Виктор Павлов"))
        );
        bespredel.setReleaseYear((short) 1989);
        bespredel.setLength((short) 6547);
        bespredel.setRating(7.2f);

        movies = Arrays.asList(durak, kollektor, duelant, bespredel);
        Iterable<Movie> saved = movieRepository.save(movies);


        SearchLog entity = new SearchLog();
        entity.setTime(new Date());
        entity.setFieldsToSearch(new ArrayList<>());
        entity.setResultCount(1);
        entity.setFoundMovies(Collections.singletonList(durak));
        entity.setFacebookId("Basyl");
        searchLogRepository.save(entity);
    }

    @Test
    public void testCount() {
        Iterator<Movie> iterator = movieRepository.findAll().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(movies.size(), count);

    }

    @Test
    public void testFindById() {
        Movie found = movieRepository.findOne(movies.get(0).getId());
        assertEquals("Дурак", found.getTitle());
        assertEquals("Дурак Description", found.getDescription());
    }

    @Test
    public void testFindByName() {
        List<Movie> list = movieRepository.findByTitle("Дурак");
        assertEquals(1, list.size());
        Movie foundDurak = list.get(0);
        assertEquals("Дурак", foundDurak.getTitle());
        assertEquals("Дурак Description", foundDurak.getDescription());
    }

    @Test
    public void testFindByDescription() {
        List<Movie> list = movieRepository.findByDescription("Дурак Description");
        assertEquals(1, list.size());
        Movie foundDurak = list.get(0);
        assertEquals("Дурак", foundDurak.getTitle());
        assertEquals("Дурак Description", foundDurak.getDescription());
    }

    @Test
    public void testFindByCategoryName() {
        List<Movie> list = movieRepository.findByCategoryName("Dram");
        assertEquals(2, list.size());
        for (Movie movie : list) {
            assertEquals("Dram", movie.getCategoryName());
        }
    }

    @Test
    public void testFindByLanguageName() {
        List<Movie> list = movieRepository.findByLanguageName("Russian");
        assertEquals(4, list.size());
        for (Movie movie : list) {
            assertEquals("Russian", movie.getLanguageName());
        }
    }

//    @Test
//    public void testFindByActorName() {
//        List<Movie> list = movieRepository.findByActorsName("Сергей Гармаш");
//        list.forEach(System.out::println);
//        assertEquals(2, list.size());
//        for (Movie movie : list) {
//            long count = movie.getActors().stream().filter(x -> "Сергей Гармаш".equals(x.getName())).count();
//            assertEquals(1, count);
//        }
//    }

    @Test
    public void testFindAllSearchLogs() {
        List<SearchLog> list = searchLogRepository.findAll();
        assertEquals(1, list.size());
        SearchLog actual = list.get(0);
        assertEquals("Basyl", actual.getFacebookId());
        assertEquals(new ArrayList<String>(), actual.getFieldsToSearch());
        assertEquals(Collections.singletonList(movies.get(0)), actual.getFoundMovies());
        assertEquals(1, actual.getResultCount());
    }

    @Test
    public void find() {
        List<Movie> list = movieRepository.find("Дурак");
        assertEquals(1, list.size());
        Movie foundDurak = list.get(0);
        assertEquals("Дурак", foundDurak.getTitle());
        assertEquals("Дурак Description", foundDurak.getDescription());

        list = movieRepository.find("Дуэлянт Description");
        assertEquals(1, list.size());
        foundDurak = list.get(0);
        assertEquals("Дуэлянт", foundDurak.getTitle());
        assertEquals("Дуэлянт Description", foundDurak.getDescription());

        list = movieRepository.find("Dram");
        assertEquals(2, list.size());
        for (Movie movie : list) {
            assertEquals("Dram", movie.getCategoryName());
        }

        list = movieRepository.find("Russian");
        assertEquals(4, list.size());
        for (Movie movie : list) {
            assertEquals("Russian", movie.getLanguageName());
        }

        list = movieRepository.find("Сергей Гармаш");
        assertEquals(2, list.size());
        for (Movie movie : list) {
            long count = movie.getActors().stream().filter(x -> "Сергей Гармаш".equals(x.getName())).count();
            assertEquals(1, count);
        }
    }

    @After
    public void clean() {
        movieRepository.deleteAll();
    }
}
