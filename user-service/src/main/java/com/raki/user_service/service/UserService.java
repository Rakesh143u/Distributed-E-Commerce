package com.raki.user_service.service;

import com.raki.user_service.model.User;
import com.raki.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = repo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(Integer id) {
        User user = repo.findById(id).get();
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    public ResponseEntity<String> createUser(User user) {
        repo.save(user);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUserById(Integer id) {
        User user = repo.findById(id).get();
        if(user==null){
            return new ResponseEntity<>("User Not Found",HttpStatus.OK);
        }
        repo.delete(user);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
