package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    public User findOneById(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }
    @Override
    public String login(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword())
        );
        User user = userRepository.findByUsername(authRequest.getUsername());
        return jwtUtils.generateToken(user);
    }

    @Override
    public  User singUp(User user){
        User newUser = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),User.Role.USER);
        return userRepository.save(newUser);
    }
    }
