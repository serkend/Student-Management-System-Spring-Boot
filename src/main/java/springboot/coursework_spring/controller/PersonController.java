package springboot.coursework_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.coursework_spring.entity.Group;
import springboot.coursework_spring.entity.Mark;
import springboot.coursework_spring.entity.Person;
import springboot.coursework_spring.entity.Subject;
import springboot.coursework_spring.service.GroupService;
import springboot.coursework_spring.service.MarkService;
import springboot.coursework_spring.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/people_api")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    MarkService markService;

    @Autowired
    GroupService groupService;

    @GetMapping("/gpeople")
    public String getAllPeople(Model model) {
        List<Person> people = personService.getAllPeople();
        Mark mark = new Mark();
        model.addAttribute("people", people);
        model.addAttribute("mark", mark);
        return "all-people";
    }

//    @PostMapping("/people")
//    public Person savePerson(@Valid @RequestBody Person person) {
//        return personService.savePerson(person);
//    }

    @PostMapping("/people")
    public String savePerson(@ModelAttribute("person") Person person) {
        List<Group> groups = groupService.findGroupByName(person.nameOfGroup);
        person.setGroup(groups.get(0));
        personService.savePerson(person);
//        System.err.println(person.idOfGroup);
        return "redirect:/people_api/gpeople";
    }

    @PostMapping("/people/saveMark")
    public String saveMark(@RequestParam("sid") int sid, @ModelAttribute("mark") Mark mark) {
        Person person = personService.getPersonById(sid);
        mark.setStudent(person);
        // markService.saveMark(mark);
        person.addMark(mark);
        personService.savePerson(person);
        System.out.println(mark);
        return "redirect:/people_api/gpeople";
    }

    @GetMapping("/people/new")
    public String createPersonForm(Model model) {
        Person person = new Person();
        Group group1 = new Group();
        person.setGroup(group1);
        List<Group> groups = groupService.getAllGroups();
        //  model.addAttribute("group", group);
        model.addAttribute("person", person);
        model.addAttribute("groups", groups);
        return "create_person";
    }

    @GetMapping("/people/newMark")
    public String createMarksForm(Model model, @RequestParam("sid") int sid, @ModelAttribute("mark") Mark mark) {

        //markService.saveMark(mark);
        model.addAttribute("sid", sid);
        model.addAttribute("mark", mark);
        System.out.println(mark);
        return "create_marks";
    }

    @GetMapping("/people/{sid}/teacherToMark")
    public String addTeacherToMark(Model model, @RequestParam("tid") int tid, @ModelAttribute("mark") Mark mark,@PathVariable("sid") int sid) {
        mark.setTeacher(personService.getPersonById(tid));
        model.addAttribute("mark", mark);
        model.addAttribute("sid", sid);
        System.out.println(mark);
        return "redirect:/people_api/people/newMark?sid=" + sid;
    }

    @GetMapping("/people/showMarks")
    public String showMarksForm(Model model, @RequestParam("sid") int sid) {
        Person person = personService.getPersonById(sid);
        List<Mark> marks = person.getMarks();
        model.addAttribute("marks", marks);
        return "show_marks";
    }

    @GetMapping("/people/showTeachers")
    public String showTeacherForm(Model model, @RequestParam("sid") int sid) {
        List<Person> people = personService.getAllPeople();
        List<Person> teachers = new ArrayList<>();
        for (Person p : people) {
            if (p.getType() == 'T') {
                teachers.add(p);
            }
        }
        //List<Mark> marks = person.getMarks();
        model.addAttribute("teachers", teachers);
        model.addAttribute("sid", sid);
        return "show_teachers";
    }

    @GetMapping("/people/showSubjects")
    public String showSubjectsForm(Model model, @RequestParam("sid") int sid) {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("English"));
        subjects.add(new Subject("Geography"));
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("Physics"));
        List<Person> teachers = new ArrayList<>();

        model.addAttribute("subjects", subjects);
        model.addAttribute("sid", sid);
        return "show_subjects";
    }

    @GetMapping("/people/{sid}/subjectToMark")
    public String addSubjectToMark(Model model, @ModelAttribute("mark") Mark mark, @RequestParam("name") String sname, @PathVariable("sid") int sid) {
        Subject subject = new Subject("sname");
        mark.setSubject(subject);
        model.addAttribute("mark", mark);
        model.addAttribute("sid", sid);
        System.out.println(mark);
        return "redirect:/people_api/people/newMark?sid=" + sid;
    }

    @GetMapping("/people/id/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/people/name/{name}")
    public List<Person> getPersonByName(@PathVariable String name) {
        return personService.getPeopleByName(name);
    }

    @GetMapping("/people/surname/{surname}")
    public List<Person> getPersonBySurname(@PathVariable String surname) {
        return personService.getPeopleBySurname(surname);
    }

    @GetMapping("/people/mark_value/{markValue}")
    public Map<Integer, Person> getPeopleByMarksValue(@PathVariable int markValue) {
        return personService.getPeopleByMarkValue(markValue);
    }

    @GetMapping("/people/fathername/{fatherName}")
    public List<Person> getPeopleByFatherName(@PathVariable String fatherName) {
        return personService.getPeopleByFatherName(fatherName);
    }

    @GetMapping("/people/group_name/{groupName}")
    public List<Person> getPeopleByGroupName(@PathVariable String groupName) {
        return personService.getPeopleByGroupName(groupName);
    }

    @GetMapping("/people/mark_subject/{markValue}/{subjectName}")
    public Map<Integer, Person> getPeopleByMarkAndSubject(@PathVariable int markValue, @PathVariable String subjectName) {
        return personService.getPeopleByMarkAndSubject(markValue, subjectName);
    }

    @GetMapping("/people/subject/{subjectName}")
    public Map<Integer, Person> getMarksOnSubject(@PathVariable String subjectName) {
        return personService.getPeopleBySubject(subjectName);
    }

    @PutMapping("/people")
    public Person updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("/people/id/{id}")
    public String deletePersonById(@PathVariable int id) {
        return personService.deletePersonById(id);
    }

    @DeleteMapping("/people/name/{name}")
    public String deletePeopleByName(@PathVariable String name) {
        return personService.deletePeopleByName(name);
    }

    @GetMapping("/people_example")
    public String saveAllPeople() {
        Person teacher1 = new Person("Maria", "Petrova", "Alexander", 'T');
        Person teacher2 = new Person("Victor", "Alexeev", "Victor", 'T');
        Group group1 = new Group("a");
        Group group2 = new Group("b");
        Subject subject1 = new Subject("English");
        Subject subject2 = new Subject("History");
        Subject subject3 = new Subject("Geography");

        Mark mark = new Mark(7);
        Person student1 = new Person("Alex", "Ivanov", "Evgeniy", 'S');
        student1.setGroup(group1);
        mark.setSubject(subject1);
        mark.setTeacher(teacher1);
        student1.addMark(mark);

        Mark mark2 = new Mark(3);
        mark2.setSubject(subject2);
        mark2.setTeacher(teacher1);
        student1.addMark(mark2);

        Mark mark3 = new Mark(4);
        Person student2 = new Person("Alexey", "Smirnov", "Anton", 'S');
        student2.setGroup(group1);
        mark3.setSubject(subject3);
        mark3.setTeacher(teacher2);
        student2.addMark(mark3);

        Mark mark4 = new Mark(6);
        Person student3 = new Person("Andrey", "Kobolev", "Vladislav", 'S');
        student3.setGroup(group2);
        mark4.setSubject(subject2);
        mark4.setTeacher(teacher1);
        student3.addMark(mark4);

        Person student4 = new Person("Stanislav", "Doychev", "Igor", 'S');
        Mark mark5 = new Mark(6);
        mark5.setSubject(subject2);
        mark5.setTeacher(teacher1);
        student4.setGroup(group2);
        student4.addMark(mark5);

        Mark mark6 = new Mark(6);
        mark6.setSubject(subject2);
        mark6.setTeacher(teacher1);
        Person student5 = new Person("Kirill", "Jelezov", "Artem", 'S');
        student5.setGroup(group1);
        student5.addMark(mark6);

        personService.savePerson(student1);
        personService.savePerson(student2);
        personService.savePerson(student3);
        personService.savePerson(student4);
        personService.savePerson(student5);

        return "redirect:/people_api/gpeople";
    }
}
