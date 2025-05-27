import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prgm2 {

    static class Student {
        String name;
        LocalDate dob;

        Student(String name, String dobStr) {
            // Try parsing DD-MM-YYYY, if fails try YYYY-MM-DD
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                dob = LocalDate.parse(dobStr, formatter1);
            } catch (Exception e) {
                dob = LocalDate.parse(dobStr, formatter2);
            }
            this.name = name;
        }

        int getAge() {
            LocalDate today = LocalDate.now();
            return Period.between(dob, today).getYears();
        }

        void displayInfo() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + getAge());
        }
    }

    static class StudentCourseInformation {
        Map<Integer, List<String>> courses = new HashMap<>();

        void addCourse(int semester, String courseName, int marks) {
            courses.putIfAbsent(semester, new ArrayList<>());
            courses.get(semester).add(courseName + " - " + marks);
        }

        void displayCourses() {
            for (int sem : courses.keySet()) {
                System.out.println("Semester " + sem + ":");
                for (String course : courses.get(sem)) {
                    System.out.println("  " + course);
                }
            }
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Alice", "15-06-2004");
        student.displayInfo();

        StudentCourseInformation sci = new StudentCourseInformation();
        sci.addCourse(1, "Math", 85);
        sci.addCourse(1, "Physics", 90);
        sci.addCourse(2, "Chemistry", 88);

        sci.displayCourses();
    }
}
