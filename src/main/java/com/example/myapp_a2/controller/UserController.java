package com.example.myapp_a2.controller;
import com.example.myapp_a2.models.User;
import java.util.*;
import java.util.Random;
import org.springframework.web.bind.annotation.*;
@RestController
public class UserController {
    static List<User> users = new ArrayList<User>();

    static {
        users.add(new User(123, "khomkevin", "123", "Kevin", "Khomiakov", "STUDENT"));
        users.add(new User(234, "khomkost", "234", "Kostya", "Tribunal", "FACULTY"));
        users.add(new User(345, "khomvlad", "345", "Vlad", "Kor", "FACULTY"));
        users.add(new User(456, "khompet", "456", "Petuh", "Craig", "STUDENT"));
    }

    @PostMapping("/api/users")
    public void createUser(@RequestBody User user) {
        long generatedLong = new Random().nextInt();
        user.setId(generatedLong);
        users.add(user);

    }

    // GET - Reading
    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return users;
    }


    @GetMapping("/api/users/{userId}")
    public User findUserById(@PathVariable("userId") long id) {

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    // DELETE - Deleting
    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable("userId") @RequestBody long id) {
        users.removeIf(x -> x.getId() == id);
    }

    // UPDATE - Updating
    @PutMapping("/api/users/{userId}")
    public void updateUser(@PathVariable("userId") long id, @RequestBody User user) {

        for (User currentUser : users) {

            if (currentUser.getId() == id) {
                currentUser.setUsername(user.getUsername());
                currentUser.setRole(user.getRole());
                currentUser.setFirstName(user.getFirstName());
                currentUser.setLastName(user.getLastName());
                currentUser.setPassword(user.getPassword());
            }
        }
    }

    @PostMapping("/api/users/search")
    public List<User> searchUser(@RequestBody User user) {
        List<User> selectedUsers = new ArrayList<>();
        selectedUsers.addAll(users);
        if (!user.getFirstName().isEmpty()) {
            selectedUsers.removeIf(x -> !x.getFirstName().equals(user.getFirstName()));
        }
        if (!user.getLastName().isEmpty()) {
            selectedUsers.removeIf(x -> !x.getLastName().equals(user.getLastName()));
        }

        if (!user.getRole().isEmpty()) {
            selectedUsers.removeIf(x -> !x.getRole().equals(user.getRole()));
        }
        if (!user.getUsername().isEmpty()) {
            selectedUsers.removeIf(x -> !x.getUsername().equals(user.getUsername()));
        }
        return selectedUsers;
    }

}
