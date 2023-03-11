package com.example.exercisespringdataintro.services;


import com.example.exercisespringdataintro.entities.*;
import com.example.exercisespringdataintro.repositories.AuthorRepository;
import com.example.exercisespringdataintro.repositories.BookRepository;
import com.example.exercisespringdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private String RESOURCE_PATH = "src/main/resources/files/";
    private String AUTHOR_FILE_NAME = "authors.txt";

    private String CATEGORY_FILE_NAME = "categories.txt";
    private String BOOKS_FILE_NAME = "books.txt";


    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;


    private AuthorService authorService;

    private CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository, AuthorService authorService, CategoryService categoryService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedAuthor() throws IOException {


        Files.readAllLines(Path.of(RESOURCE_PATH + AUTHOR_FILE_NAME))
                .stream()
                .filter(a -> !a.isBlank())
                .map(s -> s.split(" "))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);

    }

    @Override
    public void seedBook() throws IOException {

        Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .stream()
                .filter(a -> !a.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);


    }

    private Book getBookObject(String s) {
        String[] booksPart = s.split(" ");

        int editionTypeIndex = Integer.parseInt(booksPart[0]);
        EditionTypes editionType = EditionTypes.values()[editionTypeIndex];

        LocalDate publishDate = LocalDate.parse(booksPart[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(booksPart[2]);

        BigDecimal price = new BigDecimal(booksPart[3]);

        int ageRestrictionIndex = Integer.parseInt(booksPart[4]);

        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String title = Arrays.stream(booksPart).skip(5).collect(Collectors.joining(" "));


        Author author = authorService.getRandomAuthor();


        return  new Book(title, copies, editionType, price, publishDate, ageRestrictionIndex, author);
    }


    @Override
    public void seedCategory() throws IOException {

        Files.readAllLines(Path.of(RESOURCE_PATH + CATEGORY_FILE_NAME))
                .stream()
                .filter(a -> !a.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);

    }
}
