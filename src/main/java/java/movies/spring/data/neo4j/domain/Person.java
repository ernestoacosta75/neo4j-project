package java.movies.spring.data.neo4j.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int born;

    @Relationship(type = "ACTED_IN")
    private List<Movie> movies = new ArrayList<>();

    public Person(String name, int born) {
        this.name = name;
        this.born = born;
    }
}
