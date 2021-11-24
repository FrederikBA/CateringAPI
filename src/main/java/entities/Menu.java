package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Menu.deleteAllRows", query = "DELETE from Menu ")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String restaurant;


    @OneToMany(mappedBy = "menu", cascade = CascadeType.PERSIST)
    private List<Course> courses;

    public Menu() {
        this.restaurant = "CustomCatering";
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Menu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void addToMenu(Course course) {
        this.courses.add(course);
        if (course != null) {
            course.setMenu(this);
        }
    }
}
