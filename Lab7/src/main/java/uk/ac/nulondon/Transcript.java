package uk.ac.nulondon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a transcript with a student and an array of their grades.
 */
public class Transcript {
    Student student;
    List<Course> courses;

    public Transcript(Student student) {
        this.student = student;
        this.courses = new ArrayList<>();
    }
}
