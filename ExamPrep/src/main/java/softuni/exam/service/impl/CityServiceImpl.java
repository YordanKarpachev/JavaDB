package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.dto.ImportCitiesDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    private CountryRepository countryRepository;

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {

        return Files.readString(Path.of("src/main/resources/files/json/cities.json"));

    }

    @Override
    public String importCities() throws IOException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        StringBuilder sb = new StringBuilder();

        ModelMapper modelMapper = new ModelMapper();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ImportCitiesDTO[] citiesDTOS = gson.fromJson(readCitiesFileContent(), ImportCitiesDTO[].class);

        for (ImportCitiesDTO city : citiesDTOS) {

            Optional<Country> country = this.countryRepository.findById(city.getCountryId());

            if (country.isEmpty()) {
                sb.append("Invalid city").append(System.lineSeparator());
                continue;
            }


            boolean empty = validator.validate(city).isEmpty();

            if (!empty) {
                this.cityRepository.findFirstByCityName(city.getCityName()).isPresent();
                sb.append("Invalid city").append(System.lineSeparator());
                continue;
            } else {
                City map = modelMapper.map(city, City.class);
                this.cityRepository.save(map);
                sb.append(String.format("Successfully imported city %s - %d", city.getCityName(), city.getPopulation())).append(System.lineSeparator());
            }

        }
        return sb.toString();





    }
}
