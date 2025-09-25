package users;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger();
    private int id;
    private String name;
    private String email;

    public User(String name, String email){
        this.id = count.incrementAndGet();
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
    public void userInfo(){
        System.out.println("Id: " + this.id + " имя: " +  this.name + " email: " + this.email);
    }
}
