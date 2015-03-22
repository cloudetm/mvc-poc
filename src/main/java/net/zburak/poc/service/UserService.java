package net.zburak.poc.service;

import net.zburak.poc.domain.User;

import java.util.List;

/**
 * Created by buraq
 *
 */
public interface UserService extends BaseService<User>{

    public User findByUsername(String username);
    public List<User> findByName(String name);
    public List<User> findBySurname(String surname);
    public List<User> findByNameAndSurname(String name, String surname);
    public User findByPhone(String phone);
    public User findById(String Id);
    public List<User> findAll();

}
