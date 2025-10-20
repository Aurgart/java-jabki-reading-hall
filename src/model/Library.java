package model;

import exceptions.BookQuantityException;
import exceptions.BookNotFoundException;
import exceptions.WrongParamOfUserException;
import exceptions.UserNotFoundException;
import exceptions.WrongLoanParamsException;
import exceptions.WrongParamOfBookException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private BookList libraryList;
    private UserList userList;
    private List<Loan> loanList;

    public Library() {
        this.libraryList = new BookList();
        this.libraryList.addBookToList("Война и Мир", "Лев Толстой", 1975, 4, 3);
        this.libraryList.addBookToList("Капитал", "Карл Маркс", 1984, 2, 2);
        this.libraryList.addBookToList("Дyxless. Повесть о ненастоящем человеке", "Сергей Минаев", 2006, 7, 7);
        this.libraryList.addBookToList("Преступление и наказание", "Федор Достаевский", 2001, 5, 4);
        this.libraryList.addBookToList("Безумная звезда", "Терри Пратчет", 1997, 1, 1);
        this.userList = new UserList();
        this.userList.addUser("Петр", "petr1@mail.ru");
        this.userList.addUser("Юленька", "yulia_perova@bk.ru");
        this.userList.addUser("Каратель 3000", "mamin_pirozhok@gmail.com");
        loanList = new ArrayList<>();
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
        } catch (WrongParamOfBookException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int addUser(String name, String email) {
        try {
            this.userList.addUser(name, email);
            return 1;
        } catch (WrongParamOfUserException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void getBookByName(String name) {
        List<Book> bl = new ArrayList<>();
        try {
            bl = this.libraryList.getBookByName(name);
        } catch (BookNotFoundException e) {
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
        } catch (BookNotFoundException e) {
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
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (Book bok : bl) {
            System.out.println(bok.bookInfo());
        }
    }

    public void getUserById(int id) {
        try {
            this.userList.getUserByID(id).userInfo();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserByName(String name) {
        List<User> usr = new ArrayList<>();
        try {
            usr = this.userList.getUserByName(name);
        } catch (UserNotFoundException e) {
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
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (User tmp : usr) {
            tmp.userInfo();
        }
    }

    public void printAllLoanInfo() {
        for (Loan bookLoan : loanList) {
            getUserById(bookLoan.getUserId());
            System.out.println("Взял книгу: " + this.libraryList.getBookByID(bookLoan.getBookId()).bookInfo());
            System.out.println(" дата взятия: " + bookLoan.getLoanDate() + " вернул: " + bookLoan.getReturnDate());
        }
    }

    private boolean checkUser(int userId) throws BookQuantityException {
        User tmpUser;
        try {
            tmpUser = this.userList.getUserByID(userId);
        } catch (UserNotFoundException e) {
            return false;
        }
        if (tmpUser.activeLoansCnt() >= 3) {
            throw new BookQuantityException("Больше 3 в одни руки нельзя!");
        }
        return true;
    }

    private boolean checkBook(int bookId) throws BookQuantityException {
        Book chBook;
        try {
            chBook = this.libraryList.getBookByID(bookId);
        } catch (BookNotFoundException e) {
            return false;
        }
        if (chBook.getAvailableCopies() < 1) {
            throw new BookQuantityException("Копий не хватает для выдачи.");
        }
        return true;
    }

    public void loanBook(int userId, int bookId) throws WrongLoanParamsException, BookQuantityException {
        if (checkBook(bookId) && checkUser(userId)) {
            User tmpUser = this.userList.getUserByID(userId);
            Loan currentLoan = tmpUser.getUserLoans().stream().filter(l -> l.getBookId() == bookId && l.getUserId() == userId).findFirst().orElse(null);
            if (currentLoan != null) {
                throw new BookQuantityException("Такую книгу уже выдавали!");
            }
            Loan tmpLoan = new Loan(userId, bookId, LocalDate.now());
            tmpUser.getUserLoans().add(tmpLoan);
            this.loanList.add(tmpLoan);
            this.libraryList.getBookByID(bookId).getBook(1);
        } else {
            throw new WrongLoanParamsException("Некорректно указаны данные пользователя или книги!");
        }
    }

    public void returnBook(int userId, int bookId) throws WrongLoanParamsException, BookQuantityException {
        if (checkBook(bookId) && checkUser(userId)) {
            User tmpUser = this.userList.getUserByID(userId);
            Loan currentLoan = tmpUser.getUserLoans().stream().filter(l -> l.getBookId() == bookId && l.getUserId() == userId).findFirst().orElse(null);
            if (currentLoan == null) {
                throw new BookQuantityException("Такую книгу не выдавали!");
            }
            currentLoan.setReturnDate(LocalDate.now());
            currentLoan = this.loanList.stream().filter(l -> l.getBookId() == bookId && l.getUserId() == userId).findFirst().orElse(null);
            currentLoan.setReturnDate(LocalDate.now());
            this.libraryList.getBookByID(bookId).returnBook();
        } else {
            throw new WrongLoanParamsException("Некорректно указаны данные пользователя или книги!");
        }
    }

    /**
     * Просроченные выдачи.
     */
    public void printLateLoanInfo() {
        for (Loan bookLoan : loanList) {
            if (ChronoUnit.DAYS.between(bookLoan.getLoanDate(), LocalDate.now()) > 30) {
                getUserById(bookLoan.getUserId());
                System.out.println("Взял книгу: " + this.libraryList.getBookByID(bookLoan.getBookId()).bookInfo());
                System.out.println(" дата взятия: " + bookLoan.getLoanDate() + " вернул: " + bookLoan.getReturnDate());
            }
        }
    }

    /**
     * Выдачи по ид
     *
     * @param mode - 0 юзер / 1 книга
     * @param id   - ид обьекта
     */
    public void getLoansByID(int mode, int id) {
        if (mode == 0) {
            for (Loan bookLoan : this.loanList.stream().filter(l -> l.getUserId() == id).toList()) {
                getUserById(bookLoan.getUserId());
                System.out.println("Взял книгу: " + this.libraryList.getBookByID(bookLoan.getBookId()).bookInfo());
                System.out.println(" дата взятия: " + bookLoan.getLoanDate() + " вернул: " + bookLoan.getReturnDate());
            }
        } else if (mode == 1) {
            for (Loan bookLoan : this.loanList.stream().filter(l -> l.getBookId() == id).toList()) {
                getUserById(bookLoan.getUserId());
                System.out.println("Взял книгу: " + this.libraryList.getBookByID(bookLoan.getBookId()).bookInfo());
                System.out.println(" дата взятия: " + bookLoan.getLoanDate() + " вернул: " + bookLoan.getReturnDate());
            }
        }
    }
}
