package java.movies.spring.data.neo4j.services.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.applet.resources.MsgAppletViewer;

import java.movies.spring.data.neo4j.domain.Movie;
import java.movies.spring.data.neo4j.domain.Role;
import java.movies.spring.data.neo4j.repositories.MovieRepository;
import java.movies.spring.data.neo4j.services.MovieService;
import java.util.*;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private Map<String, Object> toD3Format(Collection<Movie> movies) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        Iterator<Movie> result = movies.iterator();

        while (result.hasNext()) {
            Movie movie = result.next();
            nodes.add(map("title", movie.getTitle(), "label", "Movie"));
            int target = i;
            i++;

            for (Role role : movie.getRoles()) {
                Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "Actor");

                int source = nodes.indexOf(actor);

                if(source == -1) {
                    nodes.add(actor);
                    source = i++;
                }

                rels.add(map("source", source, "target", target));
            }
        }

        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<>(2);

        result.put(key1, value1);
        result.put(key2, value2);

        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Movie findByTitle(String title) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Movie> findByTitleLike(String title) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> graph(int limit) {
        return null;
    }
}
