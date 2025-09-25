package books;

import exceptions.WrongParamOfBook;

import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {

    private static final AtomicInteger count = new AtomicInteger();
    private int id;
    private String title;
    private String author;
    private int year;
    private int totalCopies;
    private int availableCopies;

    // минимальный конструктор.
    public Book(String title, String author) {
        this.id = count.incrementAndGet();
        this.title = title;
        this.author = author;
        // Считаем что есть 1 книга, но выдать ее мы не можем.
        this.totalCopies = 1;
        this.availableCopies = 0;
    }

    // конструктор полный.
    public Book(String title, String author, int year, int totalCopies, int availableCopies) {
        this.id = count.incrementAndGet();
        if(title.isBlank()) {
            throw new WrongParamOfBook("Пустое название!");
        }
        this.title = title;
        if(author.isBlank()) {
            throw new WrongParamOfBook("У книги обязательно должен быть автор!");
        }
        this.author = author;
        if(year < 1950 || year > Year.now().getValue() ){
            throw new WrongParamOfBook("Это библиотека а не музей!");
        }
        this.year = year;
        //мы библиотека кол-во книг больше 10 это странно
        if(totalCopies < 0 || totalCopies > 10) {
            throw new WrongParamOfBook("Переданно некорректное кол-во копий.");
        }
            this.totalCopies = totalCopies;
        if (totalCopies < availableCopies || availableCopies < 0){
            throw new WrongParamOfBook("Переданно некорректное кол-во доступных копий.");
        }
        this.availableCopies = availableCopies;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getTotalCopies() {
        return this.totalCopies;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public String bookInfo() {
        return this.title + " , " + this.author + ", " + this.year + "г.";
    }

    public String getBook(int copies) {
        if (this.availableCopies >= copies) {
            this.availableCopies -= copies;
        }
        return "Взяли книгу: " + bookInfo();
    }

    public String returnBook(int copies) {
        if (copies > 0 && (copies + this.availableCopies) <= this.totalCopies) {
            this.availableCopies += copies;
        } else {
            return "Книг больше чем было, лишнего не надо.";
        }
        return "Книги вернули, кол-во доступных книг: " + this.availableCopies;
    }
}
