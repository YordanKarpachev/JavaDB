package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportStarDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StartTypeEnum;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private StarRepository starRepository;

    private Gson gson;
    private Validator validator;

    private ModelMapper modelMapper;

    private ConstellationRepository constellationRepository;

    private AstronomerRepository astronomerRepository;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, Gson gson, Validator validator, ModelMapper modelMapper, ConstellationRepository constellationRepository, AstronomerRepository astronomerRepository) {
        this.starRepository = starRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.constellationRepository = constellationRepository;
        this.astronomerRepository = astronomerRepository;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/stars.json"));
    }

    @Override
    public String importStars() throws IOException {
        ImportStarDTO[] importStarDTOS = this.gson.fromJson(readStarsFileContent(), ImportStarDTO[].class);
     return    Arrays.stream(importStarDTOS).map(this::importStar).collect(Collectors.joining(System.lineSeparator()));

    }

    private String importStar(ImportStarDTO dto){
        if (!validator.validate(dto).isEmpty()) {
            return "Invalid star";
        }

        if (this.starRepository.findByName(dto.getName()).isPresent()) {
            return "Invalid star";
        }


        Constellation constellation = this.constellationRepository.findById(dto.getConstellation()).get();
        Star starToSafe = modelMapper.map(dto, Star.class);
        starToSafe.setConstellation(constellation);

        this.starRepository.save(starToSafe);

        Locale.setDefault(Locale.US);
        return String.format("Successfully imported star %s - %.2f light years", dto.getName(), dto.getLightYears());
    }

    @Override
    public String exportStars() {

        List<Star> allByStarType = this.starRepository.findAllByStarType(StartTypeEnum.RED_GIANT);

        List<Star> sorted = allByStarType.stream().sorted(Comparator.comparing(Star::getLightYears).reversed()).collect(Collectors.toList());
        List<Astronomer> allAstronomer = this.astronomerRepository.findAll();


List<Star> stars = new ArrayList<>();


        StringBuilder sb = new StringBuilder();

        for (Star star : sorted) {

            boolean isTrue = false;
            long id = star.getId();

            for (Astronomer astronomer : allAstronomer) {
                if (astronomer.getStar().getId() == id) {
                    isTrue = true;
                    break;
                }
            }

            if (!isTrue) {
                sb.append(System.lineSeparator());
                sb.append(star.toString());
                stars.add(star);
            }

        }

        List<Star> collect = stars.stream().sorted(Comparator.comparing(Star::getLightYears)).collect(Collectors.toList());

      return   collect.stream().map(Star::toString).collect(Collectors.joining(System.lineSeparator()));


    }


}
