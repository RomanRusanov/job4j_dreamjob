package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 05.11.2020
 * email roman9628@gmail.com
 * The class describe work with db.
 */
public class PsqlStore implements Store {
    /**
     * The instance provide connection to db what not
     * create new instance each time, reuse connection.
     * Pool. Each time call connection.close() connection
     * return to pool.
     */
    private final BasicDataSource pool = new BasicDataSource();

    /**
     * The default constructor.
     */
    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    /**
     * The inner class guarantees that only one instance is initialized.
     */
    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    /**
     * The method create and get instance PsqlStore.
     * @return PsqlStore.
     */
    public static Store instOf() {
        return Lazy.INST;
    }

    /**
     * The method return collection with all posts.
     * @return Collection.
     */
    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    /**
     * The method return collection with all Candidates.
     * @return Collection.
     */
    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * The method save post to DB.
     * @param post Instance of Post.
     */
    @Override
    public void savePost(Post post) {
        if (post.getId() == 0) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

    /**
     * The method create record in db.
     * @param post Post.
     * @return Post with real id.
     */
    private Post createPost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO post(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    /**
     * The method update record in table post column name.
     * @param post Post to update.
     */
    private void updatePost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "UPDATE post set name=(?) where id=(?)")
        ) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method save post to collection.
     *
     * @param candidate Instance of Candidate.
     */
    @Override
    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            createCandidate(candidate);
        } else {
            updateCandidate(candidate);
        }
    }

    /**
     * The method create record in db.
     * @param candidate Candidate.
     * @return Candidate with real id.
     */
    private Candidate createCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO candidate(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    /**
     * The method update record in table post column name.
     * @param candidate Candidate to update.
     */
    private void updateCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "UPDATE candidate set name=(?) where id=(?)")
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method find Post by id.
     *
     * @param id int id.
     * @return Post.
     */
    @Override
    public Post findPostById(int id) {
        Post result = new Post(0, "");
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select * from post where id=(?)")
        ) {
            ps.setInt(1, id);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result.setId(record.getInt(1));
                    result.setName(record.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method find Candidate by id.
     *
     * @param id int id.
     * @return Post.
     */
    @Override
    public Candidate findCandidateById(int id) {
        Candidate result = new Candidate(0, "");
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select * from candidate where id=(?)")
        ) {
            ps.setInt(1, id);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result.setId(record.getInt(1));
                    result.setName(record.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}