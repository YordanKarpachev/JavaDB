package softuni.exam.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ImportCitiesDTO {


    @Size(min = 2, max = 60)
    @NotNull
    private String cityName;

    @Min(500)
    private int population;

    private Long country;

    public ImportCitiesDTO() {
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Long getCountryId() {
        return country;
    }

    public void setCountryId(Long countryId) {
        this.country = countryId;
    }
}
