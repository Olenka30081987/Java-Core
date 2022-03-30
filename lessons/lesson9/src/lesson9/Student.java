package lesson9;

import java.util.Objects;
import java.util.List;

public class Student {
    private String name;
    private List<Course> courses;

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return courses;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courses);
    }

    @Override
    public String toString() {
        return "\n" + name + ", курсы = " + courses;
    }
}
