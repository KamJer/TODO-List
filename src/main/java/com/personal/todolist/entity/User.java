package com.personal.todolist.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "USER")
public class User {

    public User(long id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="LOGIN")
    private String login;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if data in a user is correct (login has to be shorter than 10)
     * @param user - user data to check
     * @return true if data checks out, false if not
     */
//    static public boolean validateUserData(User user){
//        if (user.getLogin().length() >= 10) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public int hashCode() {
        return  (id.intValue()) * name.hashCode() * password.hashCode() * login.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        String user = getLogin() + " : " + getPassword() + " : " + getName();
        return user;
    }
}
