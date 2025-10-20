package tests;

import com.sun.tools.javac.Main;
import library.LibraryUi;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Tests {
    @Test
    void addBook() {
        Book testBook = new Book("Бесы", "Достоевский", 2020, 5, 5);
        Assertions.assertEquals("Бесы", testBook.getTitle());
        Assertions.assertEquals("Достоевский", testBook.getAuthor());
        Assertions.assertEquals(2020, testBook.getYear());
        Assertions.assertEquals(5, testBook.getTotalCopies());

        IllegalArgumentException e_name = assertThrows(IllegalArgumentException.class, () -> new Book("", "Достоевский", 2020, 5, 5));
        Assertions.assertEquals("Пустое название!", e_name.getMessage());
        IllegalArgumentException e_author = assertThrows(IllegalArgumentException.class, () -> new Book("Бесы", "", 2020, 5, 5));
        Assertions.assertEquals("У книги обязательно должен быть автор!", e_author.getMessage());
    }

    @Test
    void addUser() {
        User testUser = new User("Джонни", "test@mail.ru");
        Assertions.assertEquals("Джонни", testUser.getName());
        Assertions.assertEquals("test@mail.ru", testUser.getEmail());

        IllegalArgumentException e_name = assertThrows(IllegalArgumentException.class, () -> new User("", "test@mail.ru"));
        Assertions.assertEquals("Имя не может быть пустым!", e_name.getMessage());
        IllegalArgumentException e_email = assertThrows(IllegalArgumentException.class, () -> new User("Джонни", ""));
        Assertions.assertEquals("Почта не может быть пустой!", e_email.getMessage());
    }

    @Test
    void getBook() {
        BookList libraryList = new BookList();
        libraryList.addBookToList("Война и Мир", "Лев Толстой", 1975, 4, 3);
        libraryList.addBookToList("Капитал", "Карл Маркс", 1984, 2, 2);
        libraryList.addBookToList("Дyxless. Повесть о ненастоящем человеке", "Сергей Минаев", 2006, 7, 7);
        List<Book> results = libraryList.getBookByYear(1984);
        Assertions.assertEquals(1, results.size());
        RuntimeException excp = assertThrows(RuntimeException.class, () -> libraryList.getBookByName("Авиатор"));
        Assertions.assertEquals("Не найдено книги с таким названием.", excp.getMessage());
        excp = assertThrows(RuntimeException.class, () -> libraryList.getBookByAuthor("Чехов"));
        Assertions.assertEquals("Не найдено книги с таким автором.", excp.getMessage());

    }

    @Test
    void getUser() {
        UserList userList = new UserList();
        userList.addUser("Петр", "petr1@mail.ru");
        userList.addUser("Юленька", "yulia_perova@bk.ru");
        userList.addUser("Каратель 3000", "mamin_pirozhok@gmail.com");
        userList.addUser("Каратель-нагибатор", "pizhon@ya.ru");
        List<User> users = userList.getUserByName("Каратель");
        Assertions.assertNotEquals(1, users.size());
        RuntimeException excp = assertThrows(RuntimeException.class, () -> userList.getUserByName("Тар"));
        Assertions.assertEquals("Не найден пользователь.", excp.getMessage());
        excp = assertThrows(RuntimeException.class, () -> userList.getUserByID(6));
        Assertions.assertEquals("Не найден пользователь.", excp.getMessage());
    }

    @Test
    void loanBookTest() {
        Library bibl = new Library();
        IllegalArgumentException excp = assertThrows(IllegalArgumentException.class, () -> bibl.loanBook(1, 7));
        Assertions.assertEquals("Некорректно указаны данные пользователя или книги!", excp.getMessage());
        bibl.loanBook(1, 1);
        excp = assertThrows(IllegalArgumentException.class, () -> bibl.loanBook(1, 1));
        Assertions.assertEquals("Такую книгу уже выдавали!", excp.getMessage());
        bibl.loanBook(1, 5);
        excp = assertThrows(IllegalArgumentException.class, () -> bibl.loanBook(1, 5));
        Assertions.assertEquals("Копий не хватает для выдачи.", excp.getMessage());
        bibl.loanBook(1, 3);
        excp = assertThrows(IllegalArgumentException.class, () -> bibl.loanBook(1, 4));
        Assertions.assertEquals("Больше 3 в одни руки нельзя!", excp.getMessage());
    }

    @Test
    void returnBookTest(){
        Library bibl = new Library();
        IllegalArgumentException excp = assertThrows(IllegalArgumentException.class, () -> bibl.returnBook(1, 7));
        Assertions.assertEquals("Некорректно указаны данные пользователя или книги!", excp.getMessage());
        excp = assertThrows(IllegalArgumentException.class, () -> bibl.returnBook(1, 1));
        Assertions.assertEquals("Такую книгу не выдавали!", excp.getMessage());
        bibl.loanBook(1, 1);
        assertDoesNotThrow(() -> bibl.returnBook(1, 1));
    }
}