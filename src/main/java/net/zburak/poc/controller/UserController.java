package net.zburak.poc.controller;

import net.zburak.poc.domain.User;
import net.zburak.poc.service.UserService;
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

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = userService.findAll();

        if (users == null) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            user = userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody @Valid User user, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            userService.update(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        try {
            User user = userService.findById(id);
            if (user != null) {
                userService.delete(user);
            }
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }


}
