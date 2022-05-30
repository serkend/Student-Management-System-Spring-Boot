package springboot.coursework_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.coursework_spring.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findGroupById(int id);
    List<Group> findGroupByName(String name);
}
