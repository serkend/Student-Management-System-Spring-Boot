package springboot.coursework_spring.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "groupsst")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Size(min = 1, max = 10, message = "Group name should have the length between 1 and 10 symbols!!!")
    @Column(name = "name")
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
