package softuni.exam.models.dto;

import softuni.exam.models.entity.enums.DaysOfWeek;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    @XmlElement(name = "day_of_week")
    private DaysOfWeek dayOfWeek;


    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    @NotNull
    @XmlElement(name = "min_temperature")
    private Double minTemperature;

    @DecimalMin(value = "-20")
    @DecimalMax(value = "60")
    @NotNull
    @XmlElement(name = "max_temperature")
    private Double maxTemperature;


    @NotNull
    @XmlElement(name = "sunrise")
    private String sunrise;


    @NotNull
    @XmlElement
    private String sunset;

    @XmlElement
    private Long city;

    public ForecastImportDTO() {
    }


    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }




    public Long getCityId() {
        return city;
    }

    public void setCityId(Long cityId) {
        this.city = cityId;
    }

    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DaysOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
