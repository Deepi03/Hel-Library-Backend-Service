package com.rest_api.fs14backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findOneById(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }
    public User singUp(User user){
        return userRepository.save(user);
    }
}
