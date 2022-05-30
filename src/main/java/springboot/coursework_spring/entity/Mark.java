package springboot.coursework_spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Min(value = 1, message = "Mark can't be lower than 1!!!")
    @Max(value = 10, message = "Mark can't be higher than 10!!!")
    @Column(name = "value")
    private int value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Person student;

    //@NotNull()
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name = "teacher_id")
    private Person teacher;

    //@NotNull()
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Transient
    private String subjName;

    @Transient
    private String teacherName;

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getTeacherPathername() {
        return teacherPathername;
    }

    public void setTeacherPathername(String teacherPathername) {
        this.teacherPathername = teacherPathername;
    }

    @Transient
    private String teacherSurname;

    @Transient
    private String teacherPathername;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setStudent(Person student) {
        this.student = student;

//        if (!student.getMarks().contains(this)) {
//            student.addMark(this);
//        }
    }

    public Person getStudent() {
        return student;
    }

    public Mark(int value) {
        this.value = value;
    }

    public Mark() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", value=" + value +
                ", student=" + student +
                ", teacher=" + teacher +
                ", subject=" + subject +
                '}';
    }
}
