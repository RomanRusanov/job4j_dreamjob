package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.Collection;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 5.11.2020
 * email roman9628@gmail.com
 * The interface describe storage candidate and post.
 */
public interface Store {
    /**
     * The method return collection with all posts.
     * @return Collection.
     */
    Collection<Post> findAllPosts();

    /**
     * The method return collection with all Candidates.
     * @return Collection.
     */
    Collection<Candidate> findAllCandidates();

    /**
     * The method save post to collection.
     * @param post Instance of Post.
     */
    void savePost(Post post);

    /**
     * The method save post to collection.
     * @param candidate Instance of Candidate.
     */
    void saveCandidate(Candidate candidate);

    /**
     * The method find Post by id.
     * @param id int id.
     * @return Post.
     */
    Post findPostById(int id);

    /**
     * The method find Candidate by id.
     * @param id int id.
     * @return Post.
     */
    Candidate findCandidateById(int id);
}