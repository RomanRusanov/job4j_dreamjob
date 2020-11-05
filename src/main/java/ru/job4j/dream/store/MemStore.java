package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class Describe local storage in collection.
 */
public class MemStore implements Store {
    /**
     * The field contain instance of memStore(singleton).
     */
    private static final MemStore INST = new MemStore();
    /**
     * The collection store posts. Key id.
     */
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    /**
     * The collection store candidates. Key is.
     */
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    /**
     * The field contain atomic id counter for posts.
     */
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    /**
     * The field contain atomic id counter for posts.
     */
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);

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
}