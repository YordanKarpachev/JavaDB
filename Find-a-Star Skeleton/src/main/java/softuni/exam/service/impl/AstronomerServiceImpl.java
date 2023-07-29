package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerDTO;
import softuni.exam.models.dto.AstronomerWrapperDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private AstronomerRepository astronomerRepository;

    private Validator validator;

    private ModelMapper modelMapper;
    private StarRepository starRepository;

    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, Validator validator, ModelMapper modelMapper, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.starRepository = starRepository;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/astronomers.xml"));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AstronomerWrapperDTO.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File file = new File("src/main/resources/files/xml/astronomers.xml");

        AstronomerWrapperDTO wrapper = (AstronomerWrapperDTO) unmarshaller.unmarshal(file);

       return wrapper.getAstronomer().stream().map(this::importAstronom).collect(Collectors.joining(System.lineSeparator()));
    }


    private String importAstronom(AstronomerDTO dto){
        if (!this.validator.validate(dto).isEmpty()) {
            return "Invalid astronomer";
        }

        if (this.astronomerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName()).isPresent()) {
            return "Invalid astronomer";
        }

        if (this.starRepository.findById(dto.getObserving_star_id()).isEmpty()) {
            return "Invalid astronomer";
        }

        Star star = this.starRepository.findById(dto.getObserving_star_id()).get();

        Astronomer astronomerToSafe = modelMapper.map(dto, Astronomer.class);
        astronomerToSafe.setStar(star);
        this.astronomerRepository.save(astronomerToSafe);
Locale.setDefault(Locale.US);
        return String.format("Successfully imported astronomer %s - %.2f", dto.getFirstName() + " " + dto.getLastName(), dto.getAverage_observation_hours());


    }
}
