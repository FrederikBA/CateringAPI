package dtos.Course;

import dtos.Role.RoleDTO;
import entities.Course;
import entities.Role;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseDTO {
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
        this.title = title;
        this.image = image;
        this.courseId = courseId;
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
        return courseId == courseDTO.courseId && Objects.equals(title, courseDTO.title) && Objects.equals(image, courseDTO.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, image, courseId);
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
