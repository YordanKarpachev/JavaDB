package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastImportDTO;
import softuni.exam.models.dto.ForecastWrapper;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DaysOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.XmlParser;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ForecastServiceImpl implements ForecastService {

    private ForecastRepository forecastRepository;

    private XmlParser xmlParser;


    private final CityRepository cityRepository;


    public ForecastServiceImpl(ForecastRepository forecastRepository, XmlParser xmlParser, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.xmlParser = xmlParser;
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;


    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/forecasts.xml"));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        StringBuilder sb = new StringBuilder();


        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(MappingContext<String, LocalTime> mappingContext) {
                return LocalTime.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        });





        List<ForecastImportDTO> forecastImportDTOList = xmlParser.fromFile("src/main/resources/files/xml/forecasts.xml", ForecastWrapper.class)
                .getForecastImportDTOList();

        for (ForecastImportDTO forecast : forecastImportDTOList) {

            boolean empty = validator.validate(forecast).isEmpty();

            Optional<City> city = this.cityRepository.findById(forecast.getCityId());

            if (city.isEmpty()) {
                empty = false;
                sb.append("Invalid forecast").append(System.lineSeparator());
                continue;
            }

            City city1 = city.get();
            Forecast forecast1 = forecastRepository.findAllByCityAndDayOfWeek(city1, forecast.getDayOfWeek());
            if (forecast1 != null) {
                empty = false;
            }
            sb
                    .append(empty
                            ? String.format("Successfully import forecast %s - %.2f",
                            forecast.getDayOfWeek().toString(), forecast.getMaxTemperature())
                            : "Invalid forecast")
                    .append(System.lineSeparator());
            Forecast forecastToExport = modelMapper.map(forecast, Forecast.class);

            City city2 = this.cityRepository.findById(forecast.getCityId()).get();
            forecastToExport.setCity(city2);
            if (empty) {
                this.forecastRepository.save(forecastToExport);
            }



        }




        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();

        Set<Forecast> allByDayOfWeek_sunday = forecastRepository.findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DaysOfWeek.SUNDAY, 150000);

        allByDayOfWeek_sunday
                .forEach(f -> {
                    sb.append(String.format("City: %s\n" +
                                            "-min temperature: %.2f\n" +
                                            "--max temperature: %.2f\n" +
                                            "---sunrise: %s\n" +
                                            "-----sunset: %s",
                                    f.getCity().getCityName(),
                                    f.getMinTemperature(),
                                    f.getMaxTemperature(),
                                    f.getSunrise(),
                                    f.getSunset()))
                            .append(System.lineSeparator());
                });

        return sb.toString().trim();


    }
}
