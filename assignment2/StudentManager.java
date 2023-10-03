import java.util.*;
import java.io.*;
import java.util.stream.*;

/**
 * This is a program to develop a simple program to compute statistics of students' marks in an assignment.
 *
 * @author (anupa)
 * @version (version 1.0 28/09/2023)
 */
public class StudentManager
{
    // Inner class to represent individual students
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
}
