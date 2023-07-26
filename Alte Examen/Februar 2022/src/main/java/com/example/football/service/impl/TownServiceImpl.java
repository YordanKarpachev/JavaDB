package com.example.football.service.impl;

import com.example.football.models.dto.ImportTownDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final Gson gson;

    private final Validator validator;

    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, Validator validator, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/towns.json"));
    }

    @Override
    public String importTowns() throws IOException {

        StringBuilder sb = new StringBuilder();

        ImportTownDTO[] importTownDTOS = gson.fromJson(readTownsFileContent(), ImportTownDTO[].class);

        for (ImportTownDTO importTownDTO : importTownDTOS) {

            if (validator.validate(importTownDTO).isEmpty()) {

                if (this.townRepository.findByName(importTownDTO.getName()).isEmpty()) {

                    this.townRepository.save(modelMapper.map(importTownDTO, Town.class));
                    sb.append(String.format("Successfully imported Town %s - %d%n", importTownDTO.getName(), importTownDTO.getPopulation()));

                } else {
                    sb.append("Invalid Town").append(System.lineSeparator());
                }

            } else {
                sb.append("Invalid Town").append(System.lineSeparator());
            }

        }



        return sb.toString();


    }
}
