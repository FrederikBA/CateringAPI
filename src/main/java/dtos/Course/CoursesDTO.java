package dtos.Course;

import entities.Course;

import java.util.List;
import java.util.Objects;

public class CoursesDTO {
    private List<CourseDTO> courses;


    public CoursesDTO(List<Course> courses) {
        this.courses = CourseDTO.getFromList(courses);
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
        CoursesDTO that = (CoursesDTO) o;
        return Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }

    @Override
    public String toString() {
        return "CoursesDTO{" +
                "courses=" + courses +
                '}';
    }
}
