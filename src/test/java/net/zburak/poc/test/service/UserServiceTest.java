package net.zburak.poc.test.service;

import net.zburak.poc.dao.UserRepository;
import net.zburak.poc.domain.User;
import net.zburak.poc.service.UserService;
import net.zburak.poc.service.UserServiceImpl;
import net.zburak.poc.test.core.AbstractSpringJunit4Test;
import net.zburak.poc.tools.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class UserServiceTest{

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testFindByUsername(){
        String username = "behzatc";

        User mockUser = UserBuilder.getInstance().username(username).name("Behzat").surname("Ç.").phone("3123161661").id("1").build();

        Mockito.when(userRepository.findByUsername(username)).thenReturn(mockUser);
        User user = userService.findByUsername(username);
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindAll(){
        String username = "behzatc";
        User mockUser = UserBuilder.getInstance().username(username).name("Behzat").surname("Ç.").phone("3123161661").id("1").build();
        List<User> mockList = new LinkedList<>();
        mockList.add(mockUser);

        Mockito.when(userRepository.findAll()).thenReturn(mockList);
         List<User> userList = userService.findAll();
        Assert.assertTrue(userList.size() > 0);
    }

}
