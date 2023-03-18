package com.example.automappingobjects.repositories;

import com.example.automappingobjects.entities.Employee;
import com.example.automappingobjects.entities.dto.EmployeeSpringDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<EmployeeSpringDTO> findByBirthdayBeforeOrderBySalaryDesc(LocalDate date);
}
