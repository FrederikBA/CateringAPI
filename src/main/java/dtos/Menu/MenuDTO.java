package dtos.Menu;

import dtos.Course.CourseDTO;
import entities.Course;
import entities.Menu;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuDTO {
    private int id;
    private String restaurant;
    private List<CourseDTO> courses;

    public static List<MenuDTO> getFromList(List<Menu> menus) {
        return menus.stream()
                .map(menu -> new MenuDTO(menu))
                .collect(Collectors.toList());
    }
    //TODO: Fix DTO by adding back ID to constructor and figure out why it returns null when added
    public MenuDTO(Menu menu) {
        this.restaurant = menu.getRestaurant();
        this.courses = CourseDTO.getFromList(menu.getCourses());
    }

    public MenuDTO() {
        this.restaurant = "CustomCatering";
        this.id = -1;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDTO menuDTO = (MenuDTO) o;
        return id == menuDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "id=" + id +
                '}';
    }
}
