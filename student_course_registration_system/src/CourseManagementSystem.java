// Simplified Course Management System

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    int capacity;
    int enrolled;

    Course(String courseCode, String title, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    boolean enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + " (Capacity: " + capacity + ", Enrolled: " + enrolled + ")";
    }
}

class Student {
    String studentID;
    String name;
    List<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    boolean registerForCourse(Course course) {
        if (!registeredCourses.contains(course) && course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

public class CourseManagementSystem {
    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding sample courses
        courses.add(new Course("CS101", "Intro to Programming", 3));
        courses.add(new Course("MATH101", "Calculus", 2));

        int choice;
        do {
            System.out.println("\n1. List Courses\n2. Register Student\n3. Enroll in Course\n4. Drop Course\n5. View Student Info\n6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    enrollInCourse(scanner);
                    break;
                case 4:
                    dropCourse(scanner);
                    break;
                case 5:
                    viewStudentInfo(scanner);
                    break;
                case 6:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    static void registerStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        students.add(new Student(studentID, name));
        System.out.println("Student registered successfully.");
    }

    static void enrollInCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        listCourses();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);

        if (course != null && student.registerForCourse(course)) {
            System.out.println("Enrolled in course successfully.");
        } else {
            System.out.println("Failed to enroll. Course may be full or already registered.");
        }
    }

    static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        for (Course course : student.registeredCourses) {
            System.out.println(course);
        }
        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);

        if (course != null && student.dropCourse(course)) {
            System.out.println("Dropped course successfully.");
        } else {
            System.out.println("Failed to drop course.");
        }
    }

    static void viewStudentInfo(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);

        if (student != null) {
            System.out.println(student);
            System.out.println("Registered Courses:");
            for (Course course : student.registeredCourses) {
                System.out.println(course);
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    static Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
