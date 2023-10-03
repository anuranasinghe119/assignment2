
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
    }
    
     // Utility method to represent student information as a string
        @Override
        public String toString() {
            return "Student{" + "name='" + name + '\'' + ", studentID='" + studentID + '\'' + ", marks=" + Arrays.toString(marks) + ", totalMark=" + totalMark + '}';
    }
}
