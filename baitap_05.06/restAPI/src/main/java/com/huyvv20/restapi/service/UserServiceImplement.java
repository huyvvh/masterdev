package com.huyvv20.restapi.service;

import com.huyvv20.restapi.exception.NotFoundException;
import com.huyvv20.restapi.model.User;
import com.huyvv20.restapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplement implements IUserService{
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User addUser(User user) {
        if(user == null){
            throw new NullPointerException("User is null");
        }
        this.save(user);
        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        if(user != null){
            User user1 = userRepository.getById(id);
            if(user1 != null){
                user1.setEmail(user.getEmail());
                user1.setPassword(user.getPassword());

                this.save(user1);
            }
        }

        return null;
    }

    @Override
    public void deleteUser(long id) {
        if(id<1){
            throw new NotFoundException("ID not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getOneUser(long id) {
        return this.save(userRepository.getById(id));
    }
}
