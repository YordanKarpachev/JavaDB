package com.example.football.models.dto;


import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class ImportTownDTO {

    @Size(min = 2)
    private String name;

    @PositiveOrZero
    private int population;

    @Size(min = 10)
    private String travelGuide;

    public ImportTownDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
