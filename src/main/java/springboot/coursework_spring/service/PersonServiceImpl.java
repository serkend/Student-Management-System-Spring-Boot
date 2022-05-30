package springboot.coursework_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.coursework_spring.entity.Group;
import springboot.coursework_spring.entity.Mark;
import springboot.coursework_spring.entity.Person;
import springboot.coursework_spring.entity.Subject;
import springboot.coursework_spring.exception_handling.NoSuchEntityException;
import springboot.coursework_spring.exception_handling.WrongEntityParameterException;
import springboot.coursework_spring.repository.GroupRepository;
import springboot.coursework_spring.repository.MarkRepository;
import springboot.coursework_spring.repository.PersonRepository;
import springboot.coursework_spring.repository.SubjectRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Person> getAllPeople() {
        List<Person> people = personRepository.findAll();
//        if (people.isEmpty()) {
//            return null;
//        }
        return people;
    }

    @Override
    public List<Person> getPeopleByName(String name) {
        List<Person> people = personRepository.findPeopleByFirstName(name);
        if (people.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Name = " + name + "!!!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleBySurname(String surname) {
        List<Person> people = personRepository.findPeopleByLastName(surname);
        if (people.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Surname = " + surname + "!!!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByFatherName(String fathername) {
        List<Person> people = personRepository.findPeopleByFatherName(fathername);
        if (people.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Fathername = " + fathername + "!!!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByGroupName(String groupName) {
        List<Person> people = personRepository.findPeopleByGroupName(groupName);
        if (people.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Group name = " + groupName + "!!!");
        }
        return people;
    }

    @Override
    public Person getPersonById(int id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchEntityException("There are no person with such Id = " + id + "!!!");
        }
        return personOptional.get();
    }

    @Override
    public Map<Integer, Person> getPeopleByMarkValue(int markValue) {
        List<Mark> marks = markRepository.findAllByValue(markValue);
        if (marks.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Mark value = " + markValue + "!!!");
        }
        Map<Integer, Person> personMap = new HashMap<>();
        for (Mark m : marks) {
            personMap.put(m.getStudent().getId(), m.getStudent());  //Чтобы не выводился несколько раз один и тот же студент
        }
        return personMap;
    }

    @Override
    public Map<Integer, Person> getPeopleBySubject(String subjectName) {
        List<Mark> marks = markRepository.findAllBySubjectName(subjectName);
        if (marks.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Subject = " + subjectName + "!!!");
        }
        Map<Integer, Person> personMap = new HashMap<>();
        for (Mark m : marks) {
            personMap.put(m.getStudent().getId(), m.getStudent());
        }
        return personMap;
    }

    @Override
    public Map<Integer, Person> getPeopleByMarkAndSubject(Integer markValue, String subjectName) {
        List<Mark> marks = markRepository.findAllByValueAndSubjectName(markValue, subjectName);

        if (marks.isEmpty()) {
            throw new NoSuchEntityException("There are no people with such Mark = " + markValue + " and Subject = " + subjectName + "!!!");
        }
        Map<Integer, Person> personMap = new HashMap<>();
        for (Mark m : marks) {
            personMap.put(m.getStudent().getId(), m.getStudent());
        }
        return personMap;
    }

    @Override
    public Person savePerson(Person person) {
        checkInfo(person);
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        checkInfo(person);
        return personRepository.save(person);
    }

    @Override
    public String deletePeopleByName(String name) {
        List<Person> people = personRepository.findPeopleByFirstName(name);
        if (people.isEmpty()) {
            throw new NoSuchEntityException("It is impossible to delete a person with such Name = " + name + "!!!");
        }
        personRepository.deleteAllByFirstName(name);
        return "People with Name = " + name + " were deleted!";
    }

    @Override
    public String deletePersonById(int id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            throw new NoSuchEntityException("It is impossible to delete a person with non-existent Id = " + id + "!!!");
        }
        Person person = personOptional.get();
        if(person.getType() == 'T')
        {
            List<Mark> marks = markRepository.findAll();
            for(Mark m : marks) {
                if(m.getTeacher().getFirstName().equals(person.getFirstName())) {
                    markRepository.deleteById(m.getId());
                }
            }
        }
        personRepository.deleteById(id);
        return "The person with ID = " + id + " was deleted!";
    }


    public void checkInfo(Person person) {
        if (person.getType() != 'S' && person.getType()!='T') {
            throw new WrongEntityParameterException("Wrong type of person!!!");
        }
        if (person.getType() == 'T' && person.getGroup() != null) {
            throw new WrongEntityParameterException("Teacher can't have group!!!");
        }
        if (person.getType() == 'T' && !person.getMarks().isEmpty()) {
            throw new WrongEntityParameterException("Teacher can't have marks!!!");
        }
        if (person.getType() == 'T') {
            return;
        }
        if (person.getType() == 'S' && person.getGroup() == null) {
            throw new WrongEntityParameterException("Student should have group!!!");
        }
//        if (person.getType() == 'S' && (person.getMarks()==null||person.getMarks().isEmpty())) {
//            throw new WrongEntityParameterException("Student should have marks!!!");
//        }
//        List<Mark> marks = person.getMarks();
//        List<String> subjects = marks.stream().map(i -> i.getSubject().getName()).distinct().collect(Collectors.toList());
//        System.out.println("Subjects  : " + subjects.size() + "\n Marks : " + marks.size());
//        if(marks.size() != subjects.size()) {
//            throw new WrongEntityParameterException("Student has marks on same subjects!!!");
//        }

        if (person.getType() == 'S') {
            List<Mark> marks1 = person.getMarks();
            for (Mark m : marks1) {
                m.setStudent(person);
            }
        }
    }
}
