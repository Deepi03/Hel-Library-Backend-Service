package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.author.Author;
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

    @PostMapping("/signup")
    public ResponseEntity<User> singUp(@RequestBody User user) {
        try{
            User createdUser = userService.singUp(user);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        try{
           String token =  userService.login(authRequest);
            return new ResponseEntity<>(token,HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
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
