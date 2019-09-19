package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
}
