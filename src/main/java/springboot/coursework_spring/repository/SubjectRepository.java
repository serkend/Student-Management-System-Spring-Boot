package springboot.coursework_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.coursework_spring.entity.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAllByName(String name);
}
