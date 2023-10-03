import java.util.*;
import java.io.*;
import java.util.stream.*;

/**
 * This is a program to develop a simple program to compute statistics of students' marks in an assignment.
 *
 * @author (anupa)
 * @version (version 1.0 28/09/2023)
 */

// Main class that contains methods for student management
public class StudentManager {

    // Inner class to represent individual students
    static class Student {
        String name;
        String studentID;
        int[] marks = new int[3];
        int totalMark;

        // Constructor to initialize student attributes
        public Student(String name, String studentID, int[] marks) {
            this.name = name;
            this.studentID = studentID;
            this.marks = marks;
            // Calculate total marks right when the object is created
            this.totalMark = IntStream.of(marks).sum();
        }

        // Utility method to represent student information as a string
        @Override
        public String toString() {
            return "Student{" + "name='" + name + '\'' + ", studentID='" + studentID + '\'' + ", marks=" + Arrays.toString(marks) + ", totalMark=" + totalMark + '}';
        }
    }

    // Function to read student details from a file
    public static List<Student> readFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Ignore comment lines starting with '#'
                if (!line.startsWith("#") && !line.trim().isEmpty()) {
                    // Split the line into attributes and create a Student object
                    String[] parts = line.split(", ");
                    int[] marks = {Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])};
                    Student student = new Student(parts[0], parts[1], marks);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Function to filter students based on total marks below a given threshold
    public static List<Student> studentsBelowThreshold(List<Student> students, int threshold) {
        return students.stream()
        .filter(s -> s.totalMark < threshold)
        .collect(Collectors.toList());
    }

    // Function to get top 5 students based on highest marks
    public static List<Student> topFiveHighest(List<Student> students) {
        return students.stream()
        .sorted((s1, s2) -> Integer.compare(s2.totalMark, s1.totalMark))
        .limit(5)
        .collect(Collectors.toList());
    }

    // Function to get top 5 students based on lowest marks
    public static List<Student> topFiveLowest(List<Student> students) {
        return students.stream()
        .sorted(Comparator.comparingInt(s -> s.totalMark))
        .limit(5)
        .collect(Collectors.toList());
    }

    // Main function where the program execution starts
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Main loop for menu-driven interaction
        while (true) {
            System.out.println("1. Read from file");
            System.out.println("2. Print all students and total marks");
            System.out.println("3. Students below threshold");
            System.out.println("4. Top 5 students");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            // Handle menu choices
            switch (choice) {
                case 1:
                    System.out.print("Enter filename: ");
                    String fileName = scanner.next();
                    students = readFromFile(fileName);
                    break;
                case 2:
                    // Print all students' details
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    System.out.print("Enter threshold: ");
                    int threshold = scanner.nextInt();
                    // Fetch and print students below the given threshold
                    List<Student> belowThreshold = studentsBelowThreshold(students, threshold);
                    for (Student s : belowThreshold) {
                        System.out.println(s);
                    }
                    break;
            }

        }
    }
}

