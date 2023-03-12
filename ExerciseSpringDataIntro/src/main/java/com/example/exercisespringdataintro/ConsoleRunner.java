package com.example.exercisespringdataintro;

import com.example.exercisespringdataintro.entities.Author;
import com.example.exercisespringdataintro.entities.Book;
import com.example.exercisespringdataintro.repositories.AuthorRepository;
import com.example.exercisespringdataintro.repositories.BookRepository;
import com.example.exercisespringdataintro.services.SeedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final SeedServiceImpl seedService;
    @Autowired
    private final AuthorRepository authorRepository;

    public ConsoleRunner(BookRepository bookRepository, SeedServiceImpl seedService, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.seedService = seedService;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        getAllAuthorsOrderByBooksCountDesc();

    }

    private void getAllAuthorsOrderByBooksCountDesc() {
      List<Author> authors = this.authorRepository.findAll();
       authors.stream()
               .sorted((a,b) -> b.getBooks().size() - a.getBooks().size())
               .forEach(a -> System.out.printf("%s %s -> %d%n", a.getFirstName(), a.getLastName(), a.getBooks().size()));
    }

    private void printAuthorWithBookBefore1990() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(date);
        authors.forEach(a -> System.out.printf(a.getFirstName() + " " + a.getLastName()));
    }

    private void printAllBooksAfter2000() {

        LocalDate date = LocalDate.of(1999, 12, 31);
        List<Book> books = this.bookRepository.findByReleaseDateAfter(date);
        books.forEach(a -> System.out.println(a.getTitle()));
    }
}
