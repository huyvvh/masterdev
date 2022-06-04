package com.huyvv20.restapi.service;

import com.huyvv20.restapi.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> getAllUsers();
    public User save(User user);
    public User addUser(User user);
    public User updateUser(long id, User user);
    public void deleteUser(long id);
    public Optional<User> findUserById(long id);
    public User getOneUser(long id);
}
