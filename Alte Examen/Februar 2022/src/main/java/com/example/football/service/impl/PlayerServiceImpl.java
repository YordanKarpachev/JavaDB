package com.example.football.service.impl;

import com.example.football.models.dto.PlayerDTO;
import com.example.football.models.dto.PlayersWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private Validator validator;
    private PlayerRepository playerRepository;

    private StatRepository statRepository;

    private ModelMapper modelMapper;

    private TownRepository townRepository;

    private TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(Validator validator, PlayerRepository playerRepository, StatRepository statRepository, ModelMapper modelMapper, TownRepository townRepository, TeamRepository teamRepository) {
        this.validator = validator;
        this.playerRepository = playerRepository;
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/stats.xml"));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {

        JAXBContext jaxbContext = JAXBContext.newInstance(PlayersWrapperDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        ModelMapper modelMapper = new ModelMapper();

        //dd/MM/yyyy

        File file = new File("src/main/resources/files/xml/players.xml");

        PlayersWrapperDTO playersWrapperDTO = (PlayersWrapperDTO) unmarshaller.unmarshal(file);

        return playersWrapperDTO.getPlayers()
                .stream()
                .map(this::importPLayer)
                .collect(Collectors.joining(System.lineSeparator()));


    }

    @Override
    public String exportBestPlayers() {
        LocalDate dateAfter = LocalDate.of(1995, 1, 1);
        LocalDate dateBefore = LocalDate.of(2003, 1, 1);

        List<Player> players = this.playerRepository.findPlayersByBirthDateAfterAndBirthDateBeforeOrderByStatShootingDescStatPassingDescStatEnduranceDescLastName(dateAfter, dateBefore);

       return   players
                .stream()
                .map(Player::toString)
                .collect(Collectors.joining(System.lineSeparator()));


    }


    private String importPLayer(PlayerDTO playerDTO) {

        if (!validator.validate(playerDTO).isEmpty()) {
            return "Invalid Player";
        }

        if (this.playerRepository.findByEmail(playerDTO.getEmail()).isPresent()) {
            return "Invalid Player";
        }

        Stat stat = this.statRepository.findById(playerDTO.getSatDTO().getId()).get();
        Town town = this.townRepository.findByName(playerDTO.getTown().getName()).get();
        Team team = this.teamRepository.findByName(playerDTO.getTeam().getName()).get();

        Player playerToSafe = this.modelMapper.map(playerDTO, Player.class);

        playerToSafe.setStat(stat);
        playerToSafe.setTeam(team);
        playerToSafe.setTown(town);

        this.playerRepository.save(playerToSafe);


        return String.format("Successfully imported Player %s %s - %s"
                , playerToSafe.getFirstName(), playerToSafe.getLastName(), playerToSafe.getPosition());
    }
}
