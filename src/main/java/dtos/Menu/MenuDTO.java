package dtos.Menu;

import dtos.Course.CourseDTO;
import entities.Course;
import entities.Menu;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuDTO {
    private Integer id;
    private Date created;
    private String deliveryDate;
    private List<CourseDTO> courses;
    private int servings;
    private double totalPrice;
    private String deliveryAddress;

    public static List<MenuDTO> getFromList(List<Menu> menus) {
        return menus.stream()
                .map(menu -> new MenuDTO(menu))
                .collect(Collectors.toList());
    }


    public MenuDTO(Menu menu) {
        this.id = menu.getId();
        this.created = menu.getCreated();
        this.deliveryDate = menu.getDeliveryDate();
        this.courses = CourseDTO.getFromList(menu.getCourses());
        this.servings = menu.getServings();
        this.totalPrice = menu.getTotalPrice();
        this.deliveryAddress = menu.getDeliveryAddress();
        for (CourseDTO c : courses) {
            this.totalPrice += c.getPrice() * servings;
        }
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDTO menuDTO = (MenuDTO) o;
        return Objects.equals(created, menuDTO.created) && Objects.equals(deliveryDate, menuDTO.deliveryDate) && Objects.equals(courses, menuDTO.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, deliveryDate, courses);
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "created=" + created +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", courses=" + courses +
                '}';
    }
}
