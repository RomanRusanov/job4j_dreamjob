package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.servlet.Validate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 05.11.2020
 * email roman9628@gmail.com
 * The class describe work with db.
 */
public class PsqlStore implements Store, Validate {
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
     * The method add model to storage.
     * Need for test with mock.
     * @param user Model
     * @return return model instance with real unique id from storage.
     */
    @Override
    public User addUser(User user) {
        return this.createUser(user);
    }

    /**
     * The method get all existed models in storage.
     * Need for test with mock.
     * @return Collection User's instance.
     */
    @Override
    public List<User> getAllUsers() {
        return (ArrayList<User>) this.findAllUsers();
    }

    /**
     * The method add model to storage.
     * Need for test with mock.
     * @param post Model
     * @return return model instance with real unique id from storage.
     */
    @Override
    public Post addPost(Post post) {
        return this.createPost(post);
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
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getInt("city_id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * The method return all users in app.
     *
     * @return Collection.
     */
    @Override
    public Collection<User> findAllUsers() {
        List<User> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM userapp")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new User(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("email"),
                            it.getString("password")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * The method save user to DB.
     *
     * @param user Instance of User.
     */
    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    /**
     * The method create record in db.
     * @param user User.
     * @return Post with real id.
     */
    private User createUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO userapp(name, email, password) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * The method update record in table userapp columns name, email, password.
     * @param user User to update.
     */
    private void updateUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "UPDATE userapp set name=(?), email=(?), password=(?)  where id=(?)")
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                     "INSERT INTO candidate(name, city_id) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getCityId());

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
                     "UPDATE candidate set name=(?), city_id=(?) where id=(?)")
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getCityId());
            ps.setInt(3, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method find Post by id.
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
     * @param id int id.
     * @return Post.
     */
    @Override
    public Candidate findCandidateById(int id) {
        Candidate result = new Candidate(0, "", 0);
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select * from candidate where id=(?)")
        ) {
            ps.setInt(1, id);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result.setId(record.getInt(1));
                    result.setName(record.getString(2));
                    result.setCityId(record.getInt(4));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method find User by id in DB.
     * @param id int id.
     * @return User.
     */
    @Override
    public User findUserById(int id) {
        User result = new User(0, "", "", "");
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select * from userapp where id=(?)")
        ) {
            ps.setInt(1, id);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result.setId(record.getInt(1));
                    result.setName(record.getString(2));
                    result.setEmail(record.getString(3));
                    result.setPassword(record.getString(4));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method find User by email in DB.
     * @param email int id.
     * @return User.
     */
    public User findUserByEmail(String email) {
        User result = new User(0, "", "", "");
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select * from userapp where email=(?)")
        ) {
            ps.setString(1, email);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result.setId(record.getInt(1));
                    result.setName(record.getString(2));
                    result.setEmail(record.getString(3));
                    result.setPassword(record.getString(4));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method add record to photo table and update candidate table.
     * In photo table insert full path where photo store. In candidate table
     * update photo_id column, this id link to photo table to id primary key.
     * @param idCandidate int candidate id.
     * @param pathPhoto String full photo path with file name and extension.
     */
    public void addCandidatePhoto(int idCandidate, String pathPhoto) {
        int idPhoto = -1;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO photo(path) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, pathPhoto);
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    idPhoto = id.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "UPDATE candidate set photo_id=(?) where id=(?)")
        ) {
            ps.setInt(1, idPhoto);
            ps.setInt(2, idCandidate);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method get path file with photo candidate from table photo.
     * Use join for make request use candidate id.
     * @param candidateId int candidate id.
     * @return String with full path from photo table.
     */
    public String getPhotoPath(int candidateId) {
        String result = "";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select p.path from candidate c left join photo p "
                             + "on c.photo_id = p.id where c.id=(?);")
        ) {
            ps.setInt(1, candidateId);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result = record.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method delete candidate and candidate photo from table
     * candidate, photo.
     * @param id int candidate id.
     */
    public void deleteCandidate(int id) {
        int photoId = -1;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select photo_id from candidate where id=(?);")
        ) {
            ps.setInt(1, id);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    photoId = record.getInt(1);
                }
            }
            try (PreparedStatement psDelCandidate =  cn.prepareStatement(
                    "delete from candidate where id=(?);")
            ) {
                psDelCandidate.setInt(1, id);
                psDelCandidate.executeUpdate();
            }
            try (PreparedStatement psDelPhoto =  cn.prepareStatement(
                    "delete from photo where id=(?);")
            ) {
                psDelPhoto.setInt(1, photoId);
                psDelPhoto.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method delete user from table userapp.
     * @param id int user id.
     */
    public void deleteUser(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "delete from userapp where id=(?);")
        ) {
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method return all exist city
     * @return Collection with names.
     */
    public Map<Integer, String> getAllCity() {
        HashMap<Integer, String> result = new HashMap<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select * from city;")
        ) {
            try (ResultSet record = ps.executeQuery()) {
                while (record.next()) {
                    int id = record.getInt(1);
                    String cityName = record.getString(2);
                    result.put(id, cityName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method return all exist city
     * @return Collection with names.
     */
    public String getCityNameById(int id) {
        String result = "";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "select name from city where id=(?);")
        ) {
            ps.setInt(1, id);
            try (ResultSet record = ps.executeQuery()) {
                if (record.next()) {
                    result = record.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}