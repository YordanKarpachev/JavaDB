package com.example.exercisespringdataintro.services;

import org.springframework.stereotype.Service;

import java.io.IOException;


public interface SeedService {

    void seedAuthor() throws IOException;

    void seedBook() throws IOException;

    void seedCategory() throws IOException;

    default void seedAll() throws IOException {
        seedAuthor();
        seedBook();
        seedCategory();

    }
}
