package springboot.coursework_spring.service;

import springboot.coursework_spring.entity.Mark;

import java.util.List;

public interface MarkService {

    List<Mark> getAllMarks();

    Mark saveMark(Mark mark);

}
