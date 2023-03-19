package com.personal.todolist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "USER")
public class User {
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

    public void setId(Long id) {
        this.id = id;
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
    static public boolean validateUserData(User user){
        if (user.getLogin().length() >= 10) {
            System.out.println("test 1");
            return false;
        }
        return true;
    }
}