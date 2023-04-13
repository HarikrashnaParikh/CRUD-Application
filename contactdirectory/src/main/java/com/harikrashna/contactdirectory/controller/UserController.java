package com.harikrashna.contactdirectory.controller;

import com.harikrashna.contactdirectory.exception.ResourceNotFoundException;
import com.harikrashna.contactdirectory.model.User;
import com.harikrashna.contactdirectory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MobileController mobileController;

    //get all user
    @GetMapping("/users")
    public List<User> getAllUsers(){
//        long  id = 1;
//        System.out.println("user here");
//        System.out.println(userRepository.findById(id));
        System.out.println("Reached here");
        return userRepository.findAll();
    }

    
    // add/create user rest api
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //14. get user by id rest api
    //here we use @PathVariable annnotation to match {ID} to parameter ID.
    @GetMapping("/users/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable Long Id){
        User user =userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with Id :"+Id));

        //here we use to return a http status so that use here Response entity class
        return ResponseEntity.ok(user);
    }

    //15. Update User rest API
    //here we used @RequestBody annotation which was used for to get data in json format
    @PutMapping("/users/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable Long Id,@RequestBody User userDetails){
        User user =userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with Id :"+Id));
        user.setName(userDetails.getName());
        user.setOccupation(userDetails.getOccupation());
        user.setEmailId(userDetails.getEmailId());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    //19.Creting delete user rest api
    @DeleteMapping("/users/{Id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long Id){
        User user = userRepository.findById(Id)
                .orElseThrow(()->new ResourceNotFoundException("User not exist with Id :"+Id));

        userRepository.delete(user);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
