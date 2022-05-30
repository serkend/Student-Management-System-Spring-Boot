package springboot.coursework_spring.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 3, max = 15, message = "Firstname should have the length between 3 and 15 symbols!!!")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3, max = 15, message = "Lastname should have the length between 3 and 15 symbols!!!")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 3, max = 15, message = "Fathername should have the length between 3 and 15 symbols!!!")
    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "type")
    private char type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "group_id")
    public Group group;

//    @Column(name = "idOfGroup")
//    public int idOfGroup;

    @Transient
    public String nameOfGroup;

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Mark> marks;

    public void addMark(Mark mark) {
//        if (!marks.isEmpty()) { // Добавление нескольких оценок по одному предмету
//            for (Mark m : marks) {
//                if (m.getSubject().getName().equals(mark.getSubject().getName())) {
//                    throw new MarkAlreadyExistsException("The mark on subject already exists! Should be updated!");
//                }
//            }
//        }
        marks.add(mark);
        if (mark.getStudent() == null || mark.getStudent() != this) {
            mark.setStudent(this);
        }
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
//        if(!group.getStudents().contains(this)) {
//            group.addStudent(this);
//        }
    }

    public Person() {
        marks = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String fatherName, char type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.type = type;
        marks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", fatherName='" + fatherName + '\'' + ", type=" + type + '}';
    }


    public Group createGroup() {
        return new Group();
    }
}
