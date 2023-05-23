package com.rest_api.fs14backend.user;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    User findOneById(UUID id);

    String singUp(User user);

    AuthResponse login(AuthRequest authRequest);

}
