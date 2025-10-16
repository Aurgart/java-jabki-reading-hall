package model;

import exceptions.BookNotFoundException;

import java.util.*;

public class BookList {
    Map<Integer, Book> bookList;

    public BookList() {
        bookList = new HashMap<>();
    }


    public String addBookToList(String title, String author, int year, int totalCopies, int availableCopies) {
        Book newBook = new Book(title, author, year, totalCopies, availableCopies);
        return addBookToList(newBook);
    }

    // Добавление книги
    public String addBookToList(Book newbook) {
        this.bookList.put(newbook.getId(), newbook);
        return "Внесли книгу в реестр.";
    }

    public List<Book> getBookByName(String name) {
        List<Book> tmp = this.bookList.values().stream().filter(Book -> Book.getTitle().toUpperCase().contains(name.toUpperCase())).toList();
        if (tmp.isEmpty()) {
            throw new BookNotFoundException("Не найдено книги с таким названием.");
        }
        return tmp;
    }

    public List<Book> getBookByAuthor(String author) {
        List<Book> tmp = this.bookList.values().stream().filter(Book -> Book.getAuthor().toUpperCase().contains(author.toUpperCase())).toList();
        if (tmp.isEmpty()) {
            throw new BookNotFoundException("Не найдено книги с таким автором.");
        }
        return tmp;
    }

    public List<Book> getBookByYear(int year) {
        List<Book> tmp = this.bookList.values().stream().filter(Book -> Book.getYear() == year).toList();
        if (tmp.isEmpty()) {
            throw new BookNotFoundException("Не найдено книги года издания: " + year);
        }
        return tmp;
    }

    public void allBookInfo() {
        for (var entry : this.bookList.entrySet()) {
            System.out.println("Id: " + entry.getKey() + " " + entry.getValue().bookInfo());
        }
    }

    public Book getBookByID(int id) {
        Book retBook = this.bookList.get(id);
        if (retBook == null) {
            throw new BookNotFoundException("Не найдено книги с ид: " + id);
        }
        return retBook;
    }


}
