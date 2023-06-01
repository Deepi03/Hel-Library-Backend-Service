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
     *
     * @param user
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
     *
     * @param authRequest
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
     * @param userId
     * @return user which matches given id
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> findByUserId(@PathVariable UUID userId) {
        try{
            User foundUser = userService.findOneById(userId);
            return foundUser != null ? new ResponseEntity<>(foundUser,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
