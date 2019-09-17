package java.movies.spring.data.neo4j.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.movies.spring.data.neo4j.domain.Movie;
import java.movies.spring.data.neo4j.domain.Person;
import java.movies.spring.data.neo4j.domain.Role;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Before
    void setUp() {
        Movie matrix = new Movie("The Matrix", 1999, "Welcome to the Real Worl");

        movieRepository.save(matrix);

        Person keanu = new Person("Keanu Reeves", 1964);

        personRepository.save(keanu);

        Role neo = new Role(keanu, matrix);
        neo.addRoleName("Neo");

        movieRepository.save(matrix);
    }
    
    @Test
    void findByTitle() {
    }

    @Test
    void findByTitleLike() {
    }

    @Test
    void graph() {
    }
}