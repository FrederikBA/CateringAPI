package dtos.Course;

import dtos.Role.RoleDTO;
import entities.Course;
import entities.Role;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseDTO {
    private int id;
    private String title;
    private String image;
    private int courseId;


    public static List<CourseDTO> getFromList(List<Course> courses) {
        return courses.stream()
                .map(course -> new CourseDTO(course))
                .collect(Collectors.toList());
    }

    public CourseDTO(Course course) {
        this.title = course.getTitle();
        this.image = course.getImage();
        this.courseId = course.getCourseID();
    }

    public CourseDTO(String title, String image, int courseId) {
        this.id = -1;
        this.title = title;
        this.image = image;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return id == courseDTO.id && Objects.equals(title, courseDTO.title) && Objects.equals(image, courseDTO.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, image);
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
