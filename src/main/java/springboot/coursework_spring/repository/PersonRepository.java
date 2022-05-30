package springboot.coursework_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.coursework_spring.entity.Person;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findPersonByFirstName(String name);
    List<Person> findPeopleByFirstName(String name);
    List<Person> findPeopleByLastName(String surname);
    List<Person> findPeopleByFatherName(String fatherName);

    List<Person> findPeopleByGroupName(String groupName);

    @Transactional
    void deleteAllByFirstName(String name);
}
