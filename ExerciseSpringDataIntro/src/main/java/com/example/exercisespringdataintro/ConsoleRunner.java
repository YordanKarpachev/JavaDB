package com.example.exercisespringdataintro;

import com.example.exercisespringdataintro.entities.Book;
import com.example.exercisespringdataintro.repositories.BookRepository;
import com.example.exercisespringdataintro.services.SeedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final SeedServiceImpl seedService;

    public ConsoleRunner(BookRepository bookRepository, SeedServiceImpl seedService) {
        this.bookRepository = bookRepository;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {

        printAllBooksAfter2000();

    }

    private void printAllBooksAfter2000() {

        LocalDate date = LocalDate.of(1999, 12, 31);
        List<Book> books = this.bookRepository.findByReleaseDateAfter(date);
        books.forEach(a -> System.out.println(a.getTitle()));
    }
}
