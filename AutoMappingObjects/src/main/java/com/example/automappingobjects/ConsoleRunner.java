package com.example.automappingobjects;

import com.example.automappingobjects.entities.Employee;
import com.example.automappingobjects.entities.dto.CustomDTO;
import com.example.automappingobjects.entities.dto.EmployeeSpringDTO;
import com.example.automappingobjects.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        //this.persist();

        this.employeeService.findEmployeesBornBefore(1990)
                .forEach(System.out::println);

        List<Employee> all = this.employeeService.findAll();


        ModelMapper mapper = new ModelMapper();
        TypeMap<Employee, CustomDTO> employeeToCustom = mapper.createTypeMap(Employee.class, CustomDTO.class);




       // employeeToCustom.<Integer>addMapping(source -> source.getManager().getLastName().length(),
            //    (destination, value) -> destination.setManagerLastNameLength(value));

         employeeToCustom.addMapping(source -> source.getManager() == null ? 0 : source.getManager().getLastName().length(),
                 CustomDTO::setManagerLastNameLength);

    }

    private void persist() {

        Employee manager = new Employee("Mr", "Manager", BigDecimal.ZERO, LocalDate.now(), null);

        Employee firstEmployee = new Employee("Mr", "Peter", BigDecimal.TEN, LocalDate.now(), manager);

        Employee secondEmployee = new Employee("Mr", "secondEmployee", BigDecimal.TEN, LocalDate.now(), manager);

        this.employeeService.save(firstEmployee);
        // this.employeeService.save(secondEmployee);

    }
}
