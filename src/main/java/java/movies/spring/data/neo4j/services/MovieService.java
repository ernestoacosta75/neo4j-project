package java.movies.spring.data.neo4j.services;

import java.movies.spring.data.neo4j.domain.Movie;
import java.util.Collection;
import java.util.Map;

public interface MovieService {

    public Movie findByTitle(String title);

    public Collection<Movie> findByTitleLike(String title);

    public Map<String, Object> graph(int limit);
}
