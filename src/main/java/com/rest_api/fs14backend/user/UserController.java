package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.ResponseEnt;
import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.exceptions.User.LoginCredentialsNotMatchException;
import com.rest_api.fs14backend.exceptions.User.UserBadInputRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * This method allows user to register by providing username and password
     * @param user User object from request body
     * @return response string
     */
    @PostMapping("/signup")
    public ResponseEnt singUp(@RequestBody User user) {
        try{
            String createdUserMessage = userService.singUp(user);
            return new ResponseEnt(200,"User created Successfully");
        } catch (DataIntegrityViolationException e){
            throw new UserBadInputRequestException();
        }
    }

    /**
     * This method allows user to sign in / login by providing matching username and password
     * @param authRequest Auth request object from request body
     * @return token
     */

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        try{
           AuthResponse response =  userService.login(authRequest);
            return  new ResponseEntity<>(response,HttpStatus.OK);
        } catch(Exception e){
            throw new LoginCredentialsNotMatchException();
        }

    }

    /**
     *
     *  This method allows to get single user provided user id and token
     * @param userId  User id from path
     * @return user which matches given id
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> findByUserId(@PathVariable UUID userId) {
            User foundUser = userService.findOneById(userId);
            return new ResponseEntity<>(foundUser,HttpStatus.OK);
    }
}
