package net.zburak.poc.controller;

import net.zburak.poc.domain.User;
import net.zburak.poc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * Created by buraq
 */
@Controller
@RequestMapping("/users")
public class UserController implements Serializable {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Lists all users
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = userService.findAll();

        if (users == null) {
            logger.info("Users are listing");
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /**
     * Adds the entered user
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        try {
            if (result.hasErrors()) {
                logger.error("User is invalid");
                return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            user = userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates the user with given id
     * @param id
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody @Valid User user, BindingResult result) {
        try {
            if (result.hasErrors()) {
                logger.error("User is invalid");
                return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            userService.update(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes the user with given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        try {
            User user = userService.findById(id);
            if (user != null) {
                userService.delete(user);
                logger.info("User is deleted");
            }
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }


}
