package com.huyvv20.restapi.service;
import com.huyvv20.restapi.exception.DuplicateRecException;
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
        List<User> checkUser = this.userRepository.findAll();
        if(user != null){
            for(int i=0; i<checkUser.size(); i++){
                if(user.getEmail().equals(checkUser.get(i).getEmail())){
                    throw new DuplicateRecException("Email is already exit!!!");
                }
            }
        } else {
            throw new NullPointerException("User is null!!! ");
        }
        this.save(user);
        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        if(id<1){
            throw new NotFoundException("ID not found!!! ");
        }
        if(user != null){
            User user1 = userRepository.getById(id);
            if(user1 != null){
                user1.setEmail(user.getEmail());
                user1.setPassword(user.getPassword());

                this.save(user1);
            }
        } else{
            throw new NullPointerException("User is null!!! ");
        }

        return null;
    }

    @Override
    public void deleteUser(long id) {
        if(id<1){
            throw new NotFoundException("ID not found!!! ");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getOneUser(long id) {
        if(id<1){
            throw new NotFoundException("ID not found!!! ");
        }
        return this.save(userRepository.getById(id));
    }
}
