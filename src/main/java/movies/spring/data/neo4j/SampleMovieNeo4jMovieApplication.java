package movies.spring.data.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jAuditing
@EnableNeo4jRepositories("movies.spring.data.neo4j.repositories")
public class SampleMovieNeo4jMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleMovieNeo4jMovieApplication.class, args);
    }
}
