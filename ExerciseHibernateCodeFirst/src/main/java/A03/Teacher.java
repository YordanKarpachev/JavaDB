package A03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class Teacher extends Person {

    @Column
    private String email;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;


    public Teacher() {

    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }

    public Teacher(String email, double salaryPerHour) {
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
