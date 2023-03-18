package com.example.automappingobjects.services;

import com.example.automappingobjects.entities.Employee;
import com.example.automappingobjects.entities.dto.EmployeeSpringDTO;
import com.example.automappingobjects.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Optional<Employee> findOneById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        this.repository.save(employee);
    }

    @Override
    public List<EmployeeSpringDTO> findEmployeesBornBefore(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);


        return this.repository.findByBirthdayBeforeOrderBySalaryDesc(date);

    }

    @Override
    public List<Employee> findAll() {
        return this.repository.findAll();
    }
}
