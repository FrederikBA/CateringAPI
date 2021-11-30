package entities;

import dtos.Course.CourseDTO;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Course.deleteAllRows", query = "DELETE from Course ")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String title;
    private String image;
    private int courseID;
    private double price = 150;

    @ManyToOne
    Menu menu;

    public Course(String title, String image, int courseID) {
        this.title = title;
        this.image = image;
        this.courseID = courseID;

    }

    public Course(String title, String image, int courseID, double price) {
        this.title = title;
        this.image = image;
        this.courseID = courseID;
        this.price = price;
    }

    public Course(CourseDTO courseDTO) {
        this.title = courseDTO.getTitle();
        this.image = courseDTO.getImage();
        this.courseID = courseDTO.getId();
        this.price = courseDTO.getPrice();
    }

    public Course() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
