package entities;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Menu.deleteAllRows", query = "DELETE from Menu ")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Date created;
    private String deliveryDate;
    private int servings;
    private double totalPrice;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.PERSIST)
    private List<Course> courses;

    @ManyToOne
    private User user;

    public Menu(String deliveryDate) {
        this.created = new Date();
        this.deliveryDate = deliveryDate;
        this.courses = new ArrayList<>();

    }

    public Menu(String deliveryDate, int servings) {
        this.created = new Date();
        this.deliveryDate = deliveryDate;
        this.courses = new ArrayList<>();
        this.servings = servings;
        this.totalPrice = totalPrice * servings;
    }

    public Menu() {
    }



    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addToMenu(Course course) {
        this.courses.add(course);
        if (course != null) {
            course.setMenu(this);
        }
    }
}
