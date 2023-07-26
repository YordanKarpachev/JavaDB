package com.example.football.service.impl;

import com.example.football.models.dto.StatImportDTO;
import com.example.football.models.dto.StatWrapperDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;
    private final ModelMapper modelMapper;

private Validator validator;


    @Autowired
    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.statRepository = statRepository;

        this.modelMapper = modelMapper;

        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/stats.xml"));
    }

    @Override
    public String importStats() throws IOException, JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(StatWrapperDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File("src/main/resources/files/xml/stats.xml");

        StatWrapperDTO statWrapperDTO = (StatWrapperDTO) unmarshaller.unmarshal(file);

        return statWrapperDTO.getStatImportDTOS().stream().map(this::importStat)
                .collect(Collectors.joining(System.lineSeparator()));


    }


    private String importStat(StatImportDTO statImportDTO) {
        if (this.statRepository
                .findByShootingAndPassingAndEndurance(statImportDTO.getShooting(),
                        statImportDTO.getPassing(), statImportDTO.getEndurance()).isPresent()) {
            return "Invalid Stat";
        }

        if (!validator.validate(statImportDTO).isEmpty()) {
            return "Invalid Stat";
        }

        this.statRepository.save(modelMapper.map(statImportDTO, Stat.class));

       return String.format("Successfully imported Stat %.2f - %.2f - %.2f",statImportDTO.getShooting(), statImportDTO.getPassing(),  statImportDTO.getEndurance());
    }
}
