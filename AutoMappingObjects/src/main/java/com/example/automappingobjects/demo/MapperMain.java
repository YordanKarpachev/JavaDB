package com.example.automappingobjects.demo;

import com.example.automappingobjects.demo.dto.EmployeeDTO;
import com.example.automappingobjects.demo.entities.Address;
import com.example.automappingobjects.demo.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {


        /*     1. Simple Mapping
Create class Employee that has properties first name, last name, salary, birthday and address.
Create EmployeeDto class that will keep synthesized information about instances of the Employee class
 (only first name, last name and salary).
Create an instance of employee object and use the ModelMapper to map the newly created Employee to object of type EmployeeDto.*/

        ModelMapper mapper = new ModelMapper();

        Address address = new Address("Steindamm", 10 , "Hamburg", "DE");

        Employee source = new Employee("Peter", "Klaus", BigDecimal.TEN, LocalDate.now(), address);

        EmployeeDTO dto = mapper.map(source, EmployeeDTO.class);

        System.out.println(dto);

    }
}
