package com.example.automappingobjects.demo;

import com.example.automappingobjects.demo.dto.EmployeeDTO;
import com.example.automappingobjects.demo.dto.ManagerDTO;
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
Create an instance of employee object and use the ModelMapper to map the newly created Employee to object of type EmployeeDto.

        ModelMapper mapper = new ModelMapper();

        Address address = new Address("Steindamm", 10 , "Hamburg", "DE");

        Employee source = new Employee("Peter", "Klaus", BigDecimal.TEN, LocalDate.now(), address);

        EmployeeDTO dto = mapper.map(source, EmployeeDTO.class);

        System.out.println(dto);

*/




        /*    2. Advanced Mapping
Create class Employee with properties first name, last name, birthday, salary, information about whether the employee is on holiday, address, manager (another employee) and list of employees that he/she is in charge of.
Create 2 types of data transfer objects – employee data transfer object and manager data transfer object.
    • EmployeeDto – first name, last name, salary
    • ManagerDto – first name, last name, list of EmployeeDtos that he/she is in charge of and their count
Create a list of several employees, then transform it to list of ManagerDtos and print it on the console in the format provided below:
{ManagerFirstName} {ManagerLastName} | Employees: {EmployeesCount}
    - {EmployeeFirstName} {EmployeeLastName} {EmployeeSalary}*/
        ModelMapper mapper = new ModelMapper();

        Address address = new Address("Steindamm", 10 , "Hamburg", "DE");

        Employee manager = new Employee("Peter", "Klaus", BigDecimal.TEN, LocalDate.now(), address, true);

        Employee first = new Employee("FirstEmployee", "Müller", BigDecimal.ONE, LocalDate.now(), address, true);

        Employee second = new Employee("SecondEmployee", "Georg", BigDecimal.ZERO, LocalDate.now(), address, true);

       manager.addEmployee(first);
       manager.addEmployee(second);

        ManagerDTO managerDTO = mapper.map(manager, ManagerDTO.class);

        System.out.println(managerDTO);




    }
}
