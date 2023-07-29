package softuni.exam.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerDTO {

    @XmlElement
    @Min(500)
    private double average_observation_hours;


    @XmlElement
    private String birthday;
    @XmlElement(name = "first_name")
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @XmlElement(name = "last_name")
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @XmlElement
    @Min(15000)

    private double salary;

    @XmlElement

    private long observing_star_id;

    public double getAverage_observation_hours() {
        return average_observation_hours;
    }

    public void setAverage_observation_hours(double average_observation_hours) {
        this.average_observation_hours = average_observation_hours;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public long getObserving_star_id() {
        return observing_star_id;
    }

    public void setObserving_star_id(long observing_star_id) {
        this.observing_star_id = observing_star_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
