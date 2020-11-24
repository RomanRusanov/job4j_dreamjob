package ru.job4j.dream.servlet;

import ru.job4j.dream.model.*;

import java.util.List;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 23.11.2020
 * email roman9628@gmail.com
 * The interface describe behavior for static instance.
 * MemStore, PsqlStore or another different store implementation.
 */
public interface Validate {
    /**
     * The method add model to storage.
     * @param user Model
     * @return return model instance with real unique id from storage.
     */
    User addUser(User user);

    /**
     * The method get all existed models in storage.
     * @return Collection Users instances.
     */
    List<User> getAllUsers();

    /**
     * The method add model to storage.
     * @param post Model
     * @return return model instance with real unique id from storage.
     */
    Post addPost(Post post);

    /**
     * The method get all existed models in storage.
     * @return Collection Posts instances.
     */
    List<Post> getAllPosts();
}
