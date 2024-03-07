package uk.ac.nulondon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a school database.
 */
public class SchoolDatabase {

    List<Student> roster = new ArrayList<>();

    List<Transcript> records = new ArrayList<>();

    /**
     * Add a student to the database.
     * @param studentName the student's name.
     * @return the unique ID for the new student
     */
    public int addStudent(String studentName) {
        //TODO: Unique ID
        //TODO: Empty Transcript
        Student s = new Student(0, studentName);
        roster.add(s);
        return s.studentID;
    }

    /**
     * Removes the student with the ID from the course roster and records.
     * @param studentID the student's ID.
     */
    public void deleteStudent(int studentID) {
        for(Student student : roster) {
            if (student.studentID == studentID) {
                roster.remove(student);
                return;
            }
        }
        //TODO: Remove Transcript
        //TODO: Nonexistent Error
    }

    /**
     * Adds the location for the given course to the given student's record.
     * Adding a second location in a course should throw an error.
     * @param studentID the ID of the student
     * @param course the name of the course
     * @param courseLocation the city the course was taken
     */
    public void addCourse(int studentID, String course, String courseLocation) {
        Transcript studentTranscript = null;

        // find the transcript
        for (Transcript t : records) {
            if (t.student.studentID == studentID) {
                studentTranscript = t;
            }
        }

        // add the course
        if (studentTranscript != null) {
            Course newGrade = new Course(course, courseLocation);
            studentTranscript.courses.add(newGrade);
        }
    }

    /**
     * Given a student ID and course name, getLocation should return the city of the course.
     * @param studentID the ID for the student we're looking for
     * @param course the course
     * @return the city where the student took the course
     */
    public String getLocation(int studentID, String course) {
        Transcript transcript = null;

        // find the transcript
        for (Transcript t : records) {
            if (t.student.studentID == studentID) {
                transcript = t;
            }
        }

        // search transcript for course
        if (transcript != null) {
            for (Course cg : transcript.courses) {
                if (cg.courseName.equals(course)) {
                    return cg.location;
                }
            }
        }

        return "";
    }

    /**
     * Gets all the IDs for students with the given name
     * @param studentName the student name
     * @return the list of IDs
     */
    public List<Integer> nameToIDs(String studentName) {
        List<Integer> ids = new ArrayList<>();

        for (Student s : roster) {
            if (s.studentName.equals(studentName)){
                ids.add(s.studentID);
            }
        }

        return ids;
    }
}
