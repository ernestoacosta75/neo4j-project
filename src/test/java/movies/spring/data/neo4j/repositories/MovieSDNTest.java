package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.Movie;
import org.junit.jupiter.api.Test;
import org.neo4j.ogm.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// tag::sdn-neo4j-testcontainer-setup[]
@Testcontainers
@DataNeo4jTest  //<1>
public class MovieSDNTest {

    // end::sdn-neo4j-testcontainer-setup[]

    // tag::copy-plugin[]
    @Container
    private static final Neo4jContainer databaseServer = (Neo4jContainer) new Neo4jContainer()
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("movie-toolbox.jar"),
                    "/var/lib/neo4/plugins/")
            .withClasspathResourceMapping(
                    "/test-graph.db",
                    "/data/databases/graph.db", BindMode.READ_WRITE)
            .withEnv("NEO4J_dbms_security_procedures_unrestricted", "apoc.*,algo.*");
    // end::copy-plugin[]

    /**
    static final String TEST_DATA = "" +
            " MERGE(:Movie {title: 'The Matrix', released: 1993})" +
            " MERGE(:Movie {title: 'Forrest Gump', released: 1984})" +
            " MERGE(:Movie {title: 'Warriors', released: 2015})" +
            " CREATE (:Movie {title: 'Terminator'})";

    @BeforeAll
    static void prepareTestdata() {
        String password = databaseServer.getAdminPassword();

        AuthToken authToken = AuthTokens.basic("neo4j", password);

        try {
            Driver driver = GraphDatabase.driver(
                    databaseServer.getBoltUrl(), authToken);

            Session session = driver.session();

            session.writeTransaction(work -> work.run(TEST_DATA));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    // tag::sdn-neo4j-testcontainer-setup[]
    @TestConfiguration  //<2>
    static class Config {
        @Bean   // <3>
        public org.neo4j.ogm.config.Configuration configuration() {
            return new Configuration.Builder()
                    .uri(databaseServer.getBoltUrl())
                    .credentials("neo4j", databaseServer.getAdminPassword())
                    .build();
        }
    }

    private final MovieRepository movieRepository;

    @Autowired  // <4>
    public MovieSDNTest(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    // end::sdn-neo4j-testcontainer-setup[]

    // tag::boring-sdn-test[]
    @Test
    void someQueryShouldWork() {

        List<Movie> movie = movieRepository.findMovieByTitleMatchesRegex("The \\.");
        assertThat(movie).hasSize(1);
    }
    // tag::boring-sdn-test[]

}
