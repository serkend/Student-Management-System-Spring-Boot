package springboot.coursework_spring.service;

import springboot.coursework_spring.entity.Group;
import springboot.coursework_spring.entity.Mark;
import springboot.coursework_spring.entity.Person;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    Group findGroupById(int id);
    List<Group> findGroupByName(String name);

}
