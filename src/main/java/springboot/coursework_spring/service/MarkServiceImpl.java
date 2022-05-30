package springboot.coursework_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.coursework_spring.entity.Mark;
import springboot.coursework_spring.exception_handling.NoSuchEntityException;
import springboot.coursework_spring.repository.MarkRepository;
import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    MarkRepository markRepository;

    @Override
    public List<Mark> getAllMarks() {
        List<Mark> marks = markRepository.findAll();
        if (marks.isEmpty()) {
            throw new NoSuchEntityException("There are no marks in the database!!!");
        }
        return marks;
    }

    @Override
    public Mark saveMark(Mark mark) {
        markRepository.save(mark);
        return mark;
    }
}
