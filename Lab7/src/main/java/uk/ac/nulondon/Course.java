package uk.ac.nulondon;

/**
 * A class to represent a course grade with the course name and grade.
 */
public class Course {
    final String courseName;
    final String location;

    public Course(String name, String location) {
        this.courseName = name;
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Course)) {
            return false;
        }

        final Course other = (Course) obj;
        return other.location.equals(this.location) && other.courseName.equals(this.courseName);
    }
}
