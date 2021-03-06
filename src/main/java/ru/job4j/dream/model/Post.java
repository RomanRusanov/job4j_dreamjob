package ru.job4j.dream.model;

import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class describe post model.
 */
public class Post {
    /**
     * The field contain id value.
     */
    private int id;
    /**
     * The field contain name value.
     */
    private String name;

    /**
     * The default constrictor.
     * @param id id value.
     * @param name name value.
     */
    public Post(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * The getter for field id.
     * @return int id.
     */
    public int getId() {
        return id;
    }

    /**
     * The setter for id field.
     * @param id int id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The getter for name field.
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for name field.
     * @param name String name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method override equals.
     * @param o Object to compare.
     * @return if fields equals return true, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
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
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}