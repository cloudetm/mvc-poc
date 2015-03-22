package net.zburak.poc.tools;

import net.zburak.poc.domain.User;

/**
 * Created by buraq
 */
public class UserBuilder {

    private static final UserBuilder instance = new UserBuilder();

    private String username;
    private String name;
    private String surname;
    private String phone;
    private String id;

    private UserBuilder() {
    }

    public static UserBuilder getInstance(){
        return instance;
    }

    public UserBuilder id(String id){
        this.id = id;
        return this;
    }

    public UserBuilder username(String username){
        this.username = username;
        return this;
    }

    public UserBuilder name(String name){
        this.name = name;
        return this;
    }

    public UserBuilder surname(String surname){
        this.surname = surname;
        return this;
    }

    public UserBuilder phone(String phone){
        this.phone = phone;
        return this;
    }

    public User build(){
        return new User(username, name, surname, phone);
    }
}
