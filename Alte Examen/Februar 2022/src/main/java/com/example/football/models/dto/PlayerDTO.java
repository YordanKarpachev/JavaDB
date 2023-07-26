package com.example.football.models.dto;

import com.example.football.models.entity.enums.PositionEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)

public class PlayerDTO {

    @XmlElement(name = "first-name")
    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    @XmlElement(name = "last-name")
    private String lastName;


    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;


    @XmlElement
    private PositionEnum position;

    @XmlElement(name = "town")
    private NameDTO town;


    @XmlElement(name = "team")
    private NameDTO team;

    @XmlElement(name = "stat")
    private SatDTO satDTO;


    public PlayerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public NameDTO getTown() {
        return town;
    }

    public NameDTO getTeam() {
        return team;
    }

    public SatDTO getSatDTO() {
        return satDTO;
    }
}
