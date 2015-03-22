package net.zburak.poc.test.dao;

import net.zburak.poc.dao.UserRepository;
import net.zburak.poc.domain.User;
import net.zburak.poc.test.core.AbstractSpringJunit4Test;
import net.zburak.poc.tools.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by buraq
 */
public class UserRepositoryTest extends AbstractSpringJunit4Test {

    @Autowired
    private UserRepository userRepository;

    private static User user;

    @Before
    public void initialize() {
        if(user != null){
            return;
        }
        user = UserBuilder.getInstance().username("behzatc").name("Behzat").surname("Ç.").phone("3123161661").build();
        user = userRepository.save(user);
    }


    @Test
    public void testFindByUsername() {
        User user = userRepository.findByUsername("behzatc");
        Assert.assertNotNull(user);
    }

    @Test
    public void testNotFindByUsername() {
        User user = userRepository.findByUsername("ercumentc");
        Assert.assertNull(user);
    }

    @Test
    public void testFindByName() {
        List<User> userList = userRepository.findByName("Behzat");
        Assert.assertNotNull(userList);
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void testNotFindByName() {
        List<User> userList = userRepository.findByName("Ercüment");
        Assert.assertTrue(userList.size() == 0);
    }

    @Test
    public void testFindBySurname() {
        List<User> userList = userRepository.findBySurname("Ç.");
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void testNotFindBySurname() {
        List<User> userList = userRepository.findByName("Çözer");
        Assert.assertTrue(userList.size() == 0);
    }
    @Test
    public void testFindByPhone() {
        User user = userRepository.findByUsername("3123161661");
        Assert.assertNull(user);
    }

    @Test
    public void testNotFindByPhone() {
        User user = userRepository.findByUsername("21255509863");
        Assert.assertNull(user);
    }


}
