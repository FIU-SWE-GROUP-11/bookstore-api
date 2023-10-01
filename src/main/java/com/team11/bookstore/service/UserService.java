package com.team11.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.bookstore.repository.UserRepository;
import com.team11.bookstore.model.M_User;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<M_User> getAllUsers() {
        return userRepository.findAll();
    }

}
