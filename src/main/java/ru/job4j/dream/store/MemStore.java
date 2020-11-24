package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.servlet.Validate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class Describe local storage in collection.
 */
public class MemStore implements Store, Validate {
    /**
     * The field contain instance of memStore(singleton).
     */
    private static final MemStore INST = new MemStore();
    /**
     * The collection store posts. Key id.
     */
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    /**
     * The collection store candidates. Key id.
     */
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    /**
     * The collection store users. Key id.
     */
    private Map<Integer, User> users = new ConcurrentHashMap<>();
    /**
     * The field contain atomic id counter for posts.
     */
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    /**
     * The field contain atomic id counter for posts.
     */
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    /**
     * The field contain atomic id counter for users.
     */
    private static AtomicInteger USER_ID = new AtomicInteger(1);

    /**
     * The default constructor.
     */
    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    /**
     * The static method return instance of MemStore.
     * @return MemStore.
     */
    public static MemStore instOf() {
        return INST;
    }

    /**
     * The method return collection with all posts.
     * @return ConcurrentHashMap.
     */
    public Collection<Post> findAllPosts() {
        return posts.values();
    }
    /**
     * The method return collection with all candidates.
     * @return ConcurrentHashMap.
     */
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    /**
     * The method return all users in app.
     *
     * @return Collection.
     */
    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    /**
     * The method save user in collection.
     *
     * @param user Instance of User.
     */
    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(POST_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    /**
     * The method save post to collection.
     * @param post Instance of Post.
     */
    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    /**
     * The method save post to collection.
     * @param can Instance of Candidate.
     */
    public void saveCandidate(Candidate can) {
        if (can.getId() == 0) {
            can.setId(CANDIDATE_ID.incrementAndGet());
        }
        this.candidates.put(can.getId(), can);
    }

    /**
     * The method find User by id.
     *
     * @param id int id.
     * @return User.
     */
    @Override
    public User findUserById(int id) {
        return this.users.get(id);
    }

    /**
     * The method find Post by id.
     * @param id int id.
     * @return Post.
     */
    public Post findPostById(int id) {
        return this.posts.get(id);
    }

    /**
     * The method find Candidate by id.
     * @param id int id.
     * @return Post.
     */
    public Candidate findCandidateById(int id) {
        return this.candidates.get(id);
    }

    /**
     * The method add model to storage.
     * Need for test with mock.
     * @param user Model
     * @return return model instance with real unique id from storage.
     */
    @Override
    public User addUser(User user) {
        this.saveUser(user);
        return this.findUserById(user.getId());
    }

    /**
     * The method get all existed models in storage.
     * Need for test with mock.
     * @return Collection User's instance.
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(this.findAllUsers());
    }

    /**
     * The method add model to storage.
     * Need for test with mock.
     * @param post Model
     * @return return model instance with real unique id from storage.
     */
    @Override
    public Post addPost(Post post) {
        this.savePost(post);
        return this.findPostById(post.getId());
    }

    /**
     * The method get all existed models in storage.
     * Need for test with mock.
     * @return Collection Posts instances.
     */
    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(this.findAllPosts());
    }
}