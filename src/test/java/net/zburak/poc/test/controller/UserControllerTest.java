package net.zburak.poc.test.controller;

import net.zburak.poc.controller.UserController;
import net.zburak.poc.domain.User;
import net.zburak.poc.service.UserService;
import net.zburak.poc.tools.UserBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class UserControllerTest {


    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testFindAll() throws Exception{
        final User user1 = UserBuilder.getInstance().id("2").name("Clark").surname("Kent").username("Superman").phone("5556663322").build();

        final User user2 = UserBuilder.getInstance().id("2").name("Bruce").surname("Wayne").username("Batman").phone("99996549878").build();
        final List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name",
                        Matchers.hasItems(
                                Matchers.endsWith("Clark"),
                                Matchers.endsWith("Bruce"))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

