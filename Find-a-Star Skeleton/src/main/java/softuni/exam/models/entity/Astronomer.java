package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "astronomers")
public class Astronomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false, name = "average_observation_hours")
    private double averageObservationHours;



    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "observing_star_id")
      private  Star star;

    public Astronomer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }


    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
