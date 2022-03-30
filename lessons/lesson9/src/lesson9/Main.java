package lesson9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;


public class Main {
    private Student student;
    private Course course;
    private static List<Course> coursesAll;
    private static Random random = new Random();



    public static void main(String[] args) {
        coursesAll = listAllCourses();
        List<Student> studentList = getListStudent();
        System.out.println(studentList);
        Course course = coursesAll.get(random.nextInt(coursesAll.size()));

        System.out.println("список уникальных курсов : " + getListUniqueCourses(studentList));
        System.out.println("Самые любознательные студенты: " + getListСuriousStudents(studentList));
        List<Student> students = getListStudentsAttendingTheCourse(studentList,course);
        System.out.println("Курс: " + course + "посещают студенты: ");
        for (Student item : students) {
            System.out.println( item.getName());
        }
    }

    private static List<Course> listAllCourses() {
        ArrayList<Course> coursesAll = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            String word = "Курс" + i;
            coursesAll.add(new Course(word));
        }
        return coursesAll;
    }

    private static List<Course> getListCourses() {
        List<Course> courses = new ArrayList<>();
        int size =  random.nextInt(10) +1;
        while (courses.size() != size) {
            Course newCourse = coursesAll.get(random.nextInt(coursesAll.size()));
            if(!courses.contains(newCourse))
                courses.add(newCourse );
        }
        return courses;
    }

    private static List<Student> getListStudent(){
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Студент" + i;
            Student student = new Student(name,getListCourses());
            students.add(student);
        }
        return students;
    }

    public static List<Course> getListUniqueCourses(List<Student> students){
        List<Course>  result = students.stream().flatMap(student -> student.getAllCourses().stream()).distinct().collect(Collectors.toList());
        return result;
    }

    public static List<Student> getListСuriousStudents(List<Student> students){
        List<Student>  result = students.stream().sorted((o1,o2) -> o2.getAllCourses().size() - o1.getAllCourses().size()).limit(3).collect(Collectors.toList());
        return result;
    }

    public static List<Student> getListStudentsAttendingTheCourse(List<Student> students, Course course){
        List<Student>  result = students.stream().filter(student -> student.getAllCourses().contains(course)).collect(Collectors.toList());
        return result;
    }
}
