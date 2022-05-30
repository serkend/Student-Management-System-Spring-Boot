package springboot.coursework_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.coursework_spring.entity.Mark;
import springboot.coursework_spring.entity.Person;
import springboot.coursework_spring.entity.Subject;
import springboot.coursework_spring.service.MarkService;
import springboot.coursework_spring.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/marks_api")
public class MarkController {

    @Autowired
    MarkService markService;

    @Autowired
    PersonService personService;

    @GetMapping("/marks")
    public List<Mark> getAllMarks(){
        return markService.getAllMarks();
    }

    @PostMapping("/marks")
    public Mark saveMark(@RequestBody Mark mark) {
        return markService.saveMark(mark);
    }

}
