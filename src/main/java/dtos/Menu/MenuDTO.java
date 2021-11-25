package dtos.Menu;

import dtos.Course.CourseDTO;
import entities.Course;
import entities.Menu;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuDTO {
    private final String restaurant;
    private List<CourseDTO> courses;

    public static List<MenuDTO> getFromList(List<Menu> menus) {
        return menus.stream()
                .map(menu -> new MenuDTO(menu))
                .collect(Collectors.toList());
    }

    public MenuDTO(Menu menu) {
        this.restaurant = menu.getRestaurant();
        this.courses = CourseDTO.getFromList(menu.getCourses());
    }

    public MenuDTO() {
        this.restaurant = "CustomCatering";
    }

    public String getRestaurant() {
        return restaurant;
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
        return Objects.equals(restaurant, menuDTO.restaurant) && Objects.equals(courses, menuDTO.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurant, courses);
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "restaurant='" + restaurant + '\'' +
                ", courses=" + courses +
                '}';
    }
}
