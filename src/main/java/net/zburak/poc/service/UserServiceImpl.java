package net.zburak.poc.service;

import net.zburak.poc.dao.UserRepository;
import net.zburak.poc.domain.User;
import net.zburak.poc.tools.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by buraq
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private IdGenerator<String> idGenerator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity) {
        User user = userRepository.findByUsernameOrPhone(entity.getUsername(), entity.getPhone());
        if(user != null && StringUtils.isNotEmpty(user.getId())){
            throw new RuntimeException("Unique Constraint");
        }
        entity.setId(idGenerator.generate());
        return userRepository.save(entity);
    }

    @Override
     public User update(User entity) {
        if(userRepository.exists(entity.getId())){
            return userRepository.save(entity);
        } else{
            throw new RuntimeException("Record Not Found");
        }
    }

    @Override
    public void delete(User entity) {
        if(userRepository.exists(entity.getId())) {
            userRepository.delete(entity.getId());
        } else{
            throw new RuntimeException("Record Not Found");
        }
    }
}
