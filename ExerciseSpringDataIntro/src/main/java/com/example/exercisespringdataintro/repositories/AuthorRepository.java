package com.example.exercisespringdataintro.repositories;

import com.example.exercisespringdataintro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate date);
}
