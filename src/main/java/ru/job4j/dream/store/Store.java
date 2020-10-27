package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class .
 */
public class Store {

    private static final Store INST = new Store();

    private Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job",
                "description Junior",
                new GregorianCalendar(2020, 0, 27)));
        posts.put(2, new Post(2, "Middle Java Job",
                "description Middle",
                new GregorianCalendar(2020, 1, 15)));
        posts.put(3, new Post(3, "Senior Java Job",
                "description Senior",
                new GregorianCalendar(2020, 5, 18)));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}