package softuni.exam.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

// TODO: Implement all methods


public interface CityService {

    boolean areImported();

    String readCitiesFileContent() throws IOException;
	
	String importCities() throws IOException;
}
