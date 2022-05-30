package springboot.coursework_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.coursework_spring.entity.Mark;

import javax.transaction.Transactional;
import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    List<Mark> findAllByValue(int value);

    List<Mark> findAllBySubjectName(String subjectName);

    List<Mark> findAllByValueAndSubjectName(Integer markValue,String subjectName);

    @Transactional
    void deleteById(int id);
}
