package springboot.coursework_spring.service;

import springboot.coursework_spring.entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {

    Person savePerson(Person person);
    Person updatePerson(Person person);

    Person getPersonById(int id);
    List<Person> getAllPeople();
    List<Person> getPeopleByName(String name);
    List<Person> getPeopleBySurname(String surname);
    List<Person> getPeopleByFatherName(String fathername);
    List<Person> getPeopleByGroupName(String groupName);
    Map<Integer, Person> getPeopleByMarkValue(int markValue);
    Map<Integer,Person> getPeopleBySubject(String subjectName);
    Map<Integer, Person> getPeopleByMarkAndSubject(Integer markValue, String subjectName);

    String deletePeopleByName(String name);
    String deletePersonById(int id);

}
