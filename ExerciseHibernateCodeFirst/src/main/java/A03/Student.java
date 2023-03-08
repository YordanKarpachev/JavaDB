package A03;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "student")
public class Student extends Person {

    @Column(name = "average_grade")
    private double averageGrade;

    @Column
    private int attendance;

    @JoinTable(name = "student_id_courses_id", joinColumns =
    @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "courses_id", referencedColumnName = "id"))
    private Set<Course> courses;


    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public Student(double averageGrade, int attendance) {
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }


    public Student() {
        super();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
