package net.zburak.poc.dao;

import net.zburak.poc.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by buraq
 */
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(final String username);
    public User findByUsernameOrPhone(final String username, String phone);
    public List<User> findByName(final String name);
    public List<User> findBySurname(final String surname);
    public List<User> findByNameAndSurname(final String name, final String surname);
    public User findByPhone(final String phone);
}
