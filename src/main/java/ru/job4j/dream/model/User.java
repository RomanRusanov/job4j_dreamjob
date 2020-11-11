package ru.job4j.dream.model;

import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.11.2020
 * email roman9628@gmail.com
 * The class describe model user.
 */
public class User {
    /**
     * The user id.
     */
    private int id;
    /**
     * The user name.
     */
    private String name;
    /**
     * The user email address.
     */
    private String email;
    /**
     * The user password.
     */
    private String password;

    /**
     * The get for filed.
     * @return int value.
     */
    public int getId() {
        return id;
    }

    /**
     * The setter for field.
     * @param id int value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The get for filed.
     * @return int value.
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for field.
     * @param name String value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The get for filed.
     * @return int value.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The setter for field.
     * @param email String value.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The get for filed.
     * @return String value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The setter for field.
     * @param password String value.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The method override equals.
     * @param o Object to compare.
     * @return if fields equals return true, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    /**
     * The method override hashcode.
     * @return generate hash using fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * The override toString method.
     * @return String.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}