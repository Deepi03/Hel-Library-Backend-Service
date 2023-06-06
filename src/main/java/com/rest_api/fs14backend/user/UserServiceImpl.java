package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.User.UserNotFoundException;
import com.rest_api.fs14backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;


    /**
     *
     * @param userId
     * @return user which matches given id
     */

    @Override
    public User findOneById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    /**
     *
     * @return list of all users
     */
    @Override
    public List<User> findAll() {
            return userRepository.findAll();

    }

    /**
     *
     * @param authRequest
     * @return token
     */

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword())
        );
        User user = userRepository.findByUsername(authRequest.getUsername());
        return new AuthResponse(jwtUtils.generateToken(user)) ;
    }

    /**
     *
     * @param user
     * @return response string
     */

    @Override
    public String singUp(User user) {
        User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), User.Role.USER);
            userRepository.save(newUser);
            return "User Created Successfully";
    }
}
