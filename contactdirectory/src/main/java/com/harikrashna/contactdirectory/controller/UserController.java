package com.harikrashna.contactdirectory.controller;

import com.harikrashna.contactdirectory.exception.ResourceNotFoundException;
import com.harikrashna.contactdirectory.model.Mobile;
import com.harikrashna.contactdirectory.model.User;
import com.harikrashna.contactdirectory.repository.MobileRepository;
import com.harikrashna.contactdirectory.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MobileRepository mobileRepository;

    //get all user
    @GetMapping("/users")
    public List<User> getAllUsers() {
//        long  id = 1;
//        System.out.println("user here");
//        System.out.println(userRepository.findById(id));
        System.out.println("Reached here");
        return userRepository.findAll();
    }


    // add/create user rest api
    @Transactional
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        for (Mobile mobile : user.getMobiles()) {
            mobile.setUserId(user);
        }
        return userRepository.save(user);
//        for(Mobile mobile : user.getMobiles()){
//            mobileRepository.save(mobile);
//        }
    }

    //14. get user by id rest api
    //here we use @PathVariable annnotation to match {ID} to parameter ID.
    @GetMapping("/users/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with Id :" + Id));

        //here we use to return a http status so that use here Response entity class
        return ResponseEntity.ok(user);
    }

    //15. Update User rest API
    //here we used @RequestBody annotation which was used for to get data in json format
    @PutMapping("/users/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable Long Id, @RequestBody User userDetails) {
        User user = userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with Id :" + Id));
        User user1 = null;
//        if (user.isPresent()) {
            long i = user.getId();

            System.out.println(i + "++++++++++++++4565464");
//            user1 = user.get();
            user.setName(userDetails.getName());
            user.setOccupation(userDetails.getOccupation());


                user.setEmailId(userDetails.getEmailId());
                System.out.println(userDetails.getMobiles());
                user.setMobiles(userDetails.getMobiles());
                System.out.println(user.getId());
                System.out.println(user + "+++++++++++++++++++++++++++++++++++++++");
                User updatedUser = userRepository.save(user);
                return ResponseEntity.ok(updatedUser);
            }

        //19.Creting delete user rest api
        @DeleteMapping("/users/{Id}")
        public ResponseEntity<HashSet<String, Boolean>> deleteUser (@PathVariable Long Id){
            User user = userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User not exist with Id :" + Id));

            userRepository.delete(user);

            Set<String, Boolean> response = new HashSet<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
}