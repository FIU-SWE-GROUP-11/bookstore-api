package com.team11.bookstore.service;

import com.team11.bookstore.model.M_CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.bookstore.repository.UserRepository;
import com.team11.bookstore.repository.CreditCardRepository;
import com.team11.bookstore.model.M_User;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<M_User> getAllUsers() {
        return userRepository.findAll();
    }
    public boolean userExists(Integer userId) {
        return userRepository.existsById(userId);
    }

    public boolean userExistsByUsername(String username, boolean caseSensitive) {
        if (caseSensitive) {
            return userRepository.existsByUsername(username);
        }
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public M_User getUser(String username, boolean caseSensitive) {
        if (caseSensitive) {
            return userRepository.findByUsername(username);
        }
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public void saveUser(M_User user) {
        userRepository.save(user);
    }

    public void saveCreditCard(M_CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

}
