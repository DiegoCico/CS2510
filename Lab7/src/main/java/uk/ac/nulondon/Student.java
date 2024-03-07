package uk.ac.nulondon;

/**
 * A class to represent a student containing their name and ID.
 */
public class Student {
    final int studentID;
    final String studentName;

    public Student(int id, String name){
        this.studentID = id;
        this.studentName = name;
    }
}
