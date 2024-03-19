package com.rootincode.twitterapi.Service;

import com.rootincode.twitterapi.Model.Entity.User;
import com.rootincode.twitterapi.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public User addUser(User data) {
        return userRepository.save(data);
    }

    public User updateById(Integer id, User data) {

        data.setId(id);
        return userRepository.save(data);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
