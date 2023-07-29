package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private ConstellationRepository constellationRepository;

    private Gson gson;
    private Validator validator;

   private ModelMapper modelMapper;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, Validator validator, ModelMapper modelMapper) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {

        return Files.readString(Path.of("src/main/resources/files/json/constellations.json"));
    }

    @Override
    public String importConstellations() throws IOException {
        ConstellationImportDTO[] constellationImportDTOS = this.gson.fromJson(readConstellationsFromFile(), ConstellationImportDTO[].class);

      return   Arrays.stream(constellationImportDTOS).map(this::importCon).collect(Collectors.joining(System.lineSeparator()));


    }

    private String importCon(ConstellationImportDTO dto){
        if (this.constellationRepository.findByName(dto.getName()).isPresent()) {
            return "Invalid constellation";
        }

        if (!this.validator.validate(dto).isEmpty()) {
            return "Invalid constellation";
        }

        this.constellationRepository.save(modelMapper.map(dto, Constellation.class));

        return String.format("Successfully imported constellation %s - %s", dto.getName(), dto.getDescription());
    }
}
