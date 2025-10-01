package library;

import model.Library;

import java.time.Year;
import java.util.Scanner;

public class LibraryUi {
    private static Library lib = new Library();
    private final Scanner scanner = new Scanner(System.in);

    public LibraryUi() {
        System.out.println("Запускаем библиотеку!");
    }

    /**
     * Запуск работы.
     */
    public void libraryStart() {
        printMenu();
        while (true) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "0" -> System.exit(0);
                case "1" -> this.addBook();
                case "2" -> lib.libraryBookList();
                case "3" -> searchBook(1);
                case "4" -> searchBook(2);
                case "5" -> searchBook(3);
                case "6" -> this.addUser();
                case "7" -> lib.userListPrint();
                case "8" -> getUser(1);
                case "9" -> getUser(2);
                case "10" -> getUser(3);
                default -> nextMove();
            }
            nextMove();
        }
    }

    /**
     * меню.
     */
    private void printMenu() {
        System.out.println("Выбери интересующую тебя функцию:");
        System.out.println("1. Добавить новую книгу");
        System.out.println("2. Просмотреть все книги");
        System.out.println("3. Найти книгу по названию");
        System.out.println("4. Найти книгу по автору");
        System.out.println("5. Найти книгу по году");
        System.out.println("6. Добавить нового пользователя");
        System.out.println("7. Просмотреть всех пользователей");
        System.out.println("8. Найти пользователя по id");
        System.out.println("9. Найти пользователя по имени");
        System.out.println("10. Найти пользователя по почте");
        System.out.println("0. Выйти");
    }

    /**
     * Добавляет книгу
     */
    private void addBook() {
        String name;
        String author;
        int year = Year.now().getValue();
        int cnt = 0;
        System.out.println("Введите название:");
        name = scanner.nextLine().trim();
        System.out.println("Введите автора:");
        author = scanner.nextLine().trim();
        System.out.println("Введите год издания:");
        try {
            year = Integer.parseInt(scanner.nextLine().trim());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Введите кол-во копий:");
        try {
            cnt = Integer.parseInt(scanner.nextLine().trim());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        int res = this.lib.addBook(name, author, year, cnt, cnt);
        if (res == 1) {
            System.out.println("Книга добавлена.");
        } else {
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Добавляет пользователя
     */
    private void addUser() {
        String name;
        String email;
        System.out.println("Введите логин:");
        name = scanner.nextLine().trim();
        System.out.println("Введите почту:");
        email = scanner.nextLine().trim();
        int res = this.lib.addUser(name, email);
        if (res == 1) {
            System.out.println("Книга добавлена.");
        } else {
            System.out.println("Произошла ошибка.");
        }
        System.out.println("Пользователь добавлен.");
    }

    /**
     * Поиск книги
     *
     * @param mode - 1 названние 2 - автор 3 - год
     */
    private void searchBook(int mode) {
        String input;
        int year;
        if (mode == 1) {
            System.out.println("Введите название:");
            input = scanner.nextLine().trim();
            this.lib.getBookByName(input);
        } else if (mode == 2) {
            System.out.println("Введите автора:");
            input = scanner.nextLine().trim();
            this.lib.getBookByAuthor(input);
        } else if (mode == 3) {
            System.out.println("Введите год:");
            try {
                year = Integer.parseInt(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                nextMove();
                return;
            }
            this.lib.getBookByYear(year);
        }
    }

    /**
     * Следующий шаг
     */
    private void nextMove() {
        System.out.println("Нажмите любую клавишу чтобы продолжить.");
        String t = scanner.nextLine().trim();
        System.out.println("Выберите пункт из меню.");
        printMenu();
    }

    /**
     * Получение пользователя
     *
     * @param mode 1 - по ид 2 - по имени 3 - по почте.
     */
    private void getUser(int mode) {
        String input;
        if (mode == 1) {
            int id;
            System.out.println("Введите ид:");
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                nextMove();
                return;
            }
            this.lib.getUserById(id);
        } else if (mode == 2) {
            System.out.println("Введите имя:");
            input = scanner.nextLine().trim();
            this.lib.getUserByName(input);
        } else if (mode == 3) {
            System.out.println("Введите почту:");
            input = scanner.nextLine().trim();
            this.lib.getUserByEmail(input);
        }
    }
}
