package A03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "person")
@Entity()
public class Student extends Person {

    @Column(name = "average_grade")
    private double averageGrade;

    @Column
    private int attendance;



    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public Student(double averageGrade, int attendance) {
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }


    public Student(){

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
