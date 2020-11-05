package com.bsu;

import com.sun.media.sound.InvalidFormatException;

import java.util.Objects;

public class User {
    private final String name;
    private final String login;
    private final String email;
    private final String password;
    private final String role;

    public User(String[] args) throws CustomException {
        try {
            if (args.length != 5) throw new InvalidFormatException("Invalid number of arguments");
            else {
                name = args[0];
                login = args[1];
                email = args[2];
                password = args[3];
                role = args[4];
            }
        } catch (Exception ex) {
            throw new CustomException("Reading input file is failed :: " + ex);
        }
    }
    public User(String[] args, String role) throws CustomException {
        try {
            if (args.length != 4) throw new InvalidFormatException("Invalid number of arguments");
            else {
                name = args[0];
                login = args[1];
                email = args[2];
                password = args[3];
                this.role = role;
            }
        } catch (Exception ex) {
            throw new CustomException("Reading input file is failed :: " + ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email, password, role);
    }

    @Override
    public String toString() {
        return  '\n' + name + ' ' + login + ' ' +
                email + ' ' + password + ' ' +
                role ;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
