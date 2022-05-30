package springboot.coursework_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.coursework_spring.entity.Group;
import springboot.coursework_spring.entity.Person;
import springboot.coursework_spring.exception_handling.NoSuchEntityException;
import springboot.coursework_spring.repository.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        if (groups.isEmpty()) {
            throw new NoSuchEntityException("There are no groups in database!!!");
        }
        return groups;
    }

    @Override
    public Group findGroupById(int id) {
        return groupRepository.findGroupById(id);
    }

    @Override
    public List<Group> findGroupByName(String name) {
        return groupRepository.findGroupByName(name);
    }
}
