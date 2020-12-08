package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.savePost(new Post(0, "Java Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println(store.findPostById(1));
        store.savePost(new Post(1, "Java Job +"));
        System.out.println(store.findPostById(1));

        store.saveCandidate(new Candidate(0, "Java Middle",1));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
        System.out.println(store.findCandidateById(1));
        store.saveCandidate(new Candidate(1, "Java Middle +", 2));
        System.out.println(store.findCandidateById(1));
    }
}