package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    void printAllTitleBookByAgeRestriction(String miNor);

    List<String> titleOFBooksWithEditionTypeLassThanCopies(EditionType gold, int i);


    List<Book> printBookNotBetween(int lower, int upper);

    List<Book> printBooksTitleNotInYear(int year);

    List<Book> printBeforeYear(String date);

    List<String> findTitlesWithContaining(String str);

    List<Book> findBookTitleWithAuthorStartWith(String str);

    int countOfBooksWithTitleLengthThen(int length);
}
