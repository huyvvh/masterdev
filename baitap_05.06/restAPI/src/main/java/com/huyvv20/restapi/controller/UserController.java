package com.huyvv20.restapi.controller;

import com.huyvv20.restapi.model.User;
import com.huyvv20.restapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    //Done test
    @GetMapping("/getone/{id}")
    public User getOneUser(@PathVariable("id") long id) {
        return iUserService.getOneUser(id);
    }

    //Done test
    @GetMapping("/getall")
    public List<User> getAllUsers() {
        return this.iUserService.getAllUsers();
    }

    //Done test
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return this.iUserService.addUser(user);
    }

    //Done test
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user){
        return this.iUserService.updateUser(id, user);
    }

    //Done test
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        this.iUserService.deleteUser(id);
    }


}
