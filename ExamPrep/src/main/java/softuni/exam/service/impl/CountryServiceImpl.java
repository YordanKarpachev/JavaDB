package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {

    private final Path PATH_OF_JSON_FILES = Path.of("src/main/resources/files/json/countries.json");

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {

        return Files.readString(PATH_OF_JSON_FILES);

    }

    @Override
    public String importCountries() throws IOException {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ModelMapper modelMapper = new ModelMapper();

        StringBuilder sb = new StringBuilder();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        CountryImportDTO[] countryImportDTO = gson.fromJson(readCountriesFromFile(), CountryImportDTO[].class);

        for (CountryImportDTO importDTO : countryImportDTO) {

            if (this.countryRepository.findFirstByCountryName(importDTO.getCountryName()).isPresent()) {
                sb.append("Invalid country").append(System.lineSeparator());
                continue;
            }
            boolean empty = validator.validate(importDTO).isEmpty();
            if (!empty) {
                sb.append("Invalid country").append(System.lineSeparator());
            } else {
                sb.append(String.format("Successfully imported country %s - %s", importDTO.getCountryName(), importDTO.getCurrency()));
                Country map = modelMapper.map(importDTO, Country.class);
                this.countryRepository.save(map);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();


    }
}
