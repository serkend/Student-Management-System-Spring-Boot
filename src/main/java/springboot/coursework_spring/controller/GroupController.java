package springboot.coursework_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.coursework_spring.entity.Group;
import springboot.coursework_spring.entity.Person;
import springboot.coursework_spring.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups_api")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
}
