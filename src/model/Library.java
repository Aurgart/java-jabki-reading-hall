package model;

import exceptions.BookNotFound;
import exceptions.UserNotFound;
import exceptions.WrongParamOfBook;
import exceptions.WrongParamOfUser;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private BookList libraryList;
    private UserList userList;

    public Library() {
        this.libraryList = new BookList();
        this.libraryList.addBookToList("Война и Мир", "Лев Толстой", 1975, 4, 3);
        this.libraryList.addBookToList("Капитал", "Карл Маркс", 1984, 2, 2);
        this.libraryList.addBookToList("Дyxless. Повесть о ненастоящем человеке", "Сергей Минаев", 2006, 7, 7);
        this.userList = new UserList();
        this.userList.addUser("Петр", "petr1@mail.ru");
        this.userList.addUser("Юленька", "yulia_perova@bk.ru");
        this.userList.addUser("Каратель 3000", "mamin_pirozhok@gmail.com");
    }

    public void libraryBookList() {
        this.libraryList.allBookInfo();
    }

    public void userListPrint() {
        this.userList.listAllUsers();
    }

    public int addBook(String title, String author, int year, int totalCopies, int availableCopies) {
        try {
            this.libraryList.addBookToList(title, author, year, totalCopies, availableCopies);
            return 1;
        }catch (WrongParamOfBook e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int addUser(String name, String email) {
        try {
            this.userList.addUser(name, email);
            return 1;
        }catch (WrongParamOfUser e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void getBookByName(String name) {
        List<Book> bl = new ArrayList<>();
        try {
            bl = this.libraryList.getBookByName(name);
        } catch (BookNotFound e) {
            System.out.println(e.getMessage());
        }
        for (Book bok : bl) {
            System.out.println(bok.bookInfo());
        }
    }

    public void getBookByAuthor(String author) {
        List<Book> bl = new ArrayList<>();
        try {
            bl = this.libraryList.getBookByAuthor(author);
        } catch (BookNotFound e) {
            System.out.println(e.getMessage());
        }
        for (Book bok : bl) {
            System.out.println(bok.bookInfo());
        }
    }

    public void getBookByYear(int year) {
        List<Book> bl = new ArrayList<>();
        try {
            bl = this.libraryList.getBookByYear(year);
        } catch (BookNotFound e) {
            System.out.println(e.getMessage());
        }
        for (Book bok : bl) {
            System.out.println(bok.bookInfo());
        }
    }

    public void getUserById(int id) {
        try {
            this.userList.getUserByID(id).userInfo();
        } catch (UserNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserByName(String name) {
        List<User> usr = new ArrayList<>();
        try {
            usr = this.userList.getUserByName(name);
        } catch (UserNotFound e) {
            System.out.println(e.getMessage());
        }
        for (User tmp : usr) {
            tmp.userInfo();
        }
    }

    public void getUserByEmail(String email) {
        List<User> usr = new ArrayList<>();
        try {
            usr = this.userList.getUserByEmail(email);
        } catch (UserNotFound e) {
            System.out.println(e.getMessage());
        }
        for (User tmp : usr) {
            tmp.userInfo();
        }
    }
}
