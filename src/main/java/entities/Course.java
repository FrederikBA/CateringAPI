package entities;

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


    @ManyToOne
    Menu menu;

    public Course(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Course() {
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
