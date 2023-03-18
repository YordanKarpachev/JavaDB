package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // seedData();


        /* A02
        Write a program that prints the titles of the golden edition books, which have less than 5000 copies.

       List<String> titles = this.bookService.titleOFBooksWithEditionTypeLassThanCopies(EditionType.GOLD, 5000);
       titles.forEach(System.out::println); */


        /*A03     3. Books by Price
        //Write a program that prints the titles and prices of books with price lower than 5 and higher than 40.

        this.bookService.printBookNotBetween(5, 40)
                .forEach(a -> System.out.printf("%s - %.2f%n" ,a.getTitle(), a.getPrice() ));  */


        /*
            4. Not Released Books
        Write a program that prints the titles of all books that are NOT released in a given year.

        this.bookService.printBooksTitleNotInYear(2000)
                .stream().map(Book::getTitle).forEach(System.out::println); */


        /*
          A05 Write a program that prints the title, the edition type and the price of books, which are released before a given date. The date will be in the format dd-MM-yyyy.

                this.bookService.printBeforeYear("12-04-1992")
                .forEach(a -> System.out.printf("%s %s %.2f%n", a.getTitle(), a.getEditionType(), a.getPrice()));  */


    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
