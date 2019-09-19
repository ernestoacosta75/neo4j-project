package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Person;
import movies.spring.data.neo4j.domain.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class MovieRepositoryTestIT {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    private final String MOVIE_TITLE = "The Matrix";

    @Before
    public void setUp() throws Exception {
        Movie matrix = new Movie(MOVIE_TITLE, 1999, "Welcome to the Real World");

        movieRepository.save(matrix);

        Person keanu = new Person("Keanu Reeves", 1964);

        personRepository.save(keanu);

        Role neo = new Role(matrix, keanu);
        neo.addRoleName("Neo");

        matrix.addRole(neo);

        movieRepository.save(matrix);
    }

    /**
     * Test of findByTitle method, of class MovieRepository.
     */
    @Test
    public void findByTitle() {
        String title = "The Matrix";
        Movie result = movieRepository.findByTitle(MOVIE_TITLE);
        Assert.assertNotNull(result);
        Assert.assertEquals(1999, result.getReleased());
    }

    /**
     * Test of findByTitleLike method, of class MovieRepository.
     */
    @Test
    public void findByTitleLike() {
        String title = "*Matrix*";
        Collection<Movie> result = movieRepository.findByTitleLike(MOVIE_TITLE);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    /**
     * Test of graph method, of class MovieRepository.
     */
    @Test
    public void graph() {
        Collection<Movie> graph = movieRepository.graph(5);

        Assert.assertEquals(1, graph.size());

        Movie movie = graph.iterator().next();

        Assert.assertEquals(1, movie.getRoles().size());

        Assert.assertEquals("The Matrix", movie.getTitle());
        Assert.assertEquals("Keanu Reeves", movie.getRoles().iterator().next().getPerson().getName());
    }
}