package model;

import exceptions.UserNotFoundException;

import java.util.HashMap;
import java.util.List;

public class UserList {
    HashMap<Integer, User> userList;

    public UserList() {
        userList = new HashMap<>();
    }

    public void addUser(String name, String email) {
        User newUser = new User(name, email);
        userList.put(newUser.getId(), newUser);
    }

    public void addUser(User newUser) {
        userList.put(newUser.getId(), newUser);
    }

    public List<User> getUserByName(String name) {
        List<User> tmp = this.userList.values().stream().filter(User -> User.getName().toUpperCase().contains(name.toUpperCase())).toList();
        if (tmp.isEmpty()) {
            throw new UserNotFoundException("Не найден пользователь.");
        }
        return tmp;
    }

    public List<User> getUserByEmail(String email) {
        List<User> tmp = this.userList.values().stream().filter(User -> User.getEmail().toUpperCase().contains(email.toUpperCase())).toList();
        if (tmp.isEmpty()) {
            throw new UserNotFoundException("Не найден пользователь.");
        }
        return tmp;
    }

    public User getUserByID(int id) {
        User retUser = this.userList.get(id);
        if (retUser == null) {
            throw new UserNotFoundException("Не найден пользователь.");
        }
        return retUser;
    }

    public void listAllUsers() {
        for (var entry : this.userList.entrySet()) {
            System.out.println("Id: " + entry.getKey() + " login: " + entry.getValue().getName() + " email: " + entry.getValue().getEmail());
        }
    }
}
