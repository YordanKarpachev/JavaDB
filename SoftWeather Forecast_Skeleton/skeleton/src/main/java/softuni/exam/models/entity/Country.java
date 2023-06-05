package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country_name", unique = true, nullable = false)
    @Size(min = 2, max = 60)
    private String countryName;
    @Size(min = 2, max = 60)
    @Column(nullable = false)
    private String currency;



    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
