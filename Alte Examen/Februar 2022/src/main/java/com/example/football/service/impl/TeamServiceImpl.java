package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final TownRepository townRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;

    private final Gson gson;
    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/teams.json"));
    }

    @Override
    public String importTeams() throws IOException {


      return   Arrays.stream(gson.fromJson(readTeamsFileContent(), ImportTeamDTO[].class))
              .map(this::importTeam).collect(Collectors.joining(System.lineSeparator()));
    }

    private String importTeam(ImportTeamDTO dto){

        if(this.teamRepository.findByName(dto.getName()).isPresent()){
            return "Invalid Team";
        }

        if (!validator.validate(dto).isEmpty()) {
            return "Invalid Team";
        }

        Town town = this.townRepository.findByName(dto.getTownName()).get();
        Team teamToSafe = modelMapper.map(dto, Team.class);
        teamToSafe.setTown(town);
        this.teamRepository.save(teamToSafe);

        return String.format("Successfully imported Team %s - %d", teamToSafe.getName(), teamToSafe.getFanBase());

    }


}
