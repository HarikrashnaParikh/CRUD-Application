package com.harikrashna.contactdirectory.controller;

import com.harikrashna.contactdirectory.model.Mobile;
import com.harikrashna.contactdirectory.model.User;
import com.harikrashna.contactdirectory.repository.MobileRepository;
import com.harikrashna.contactdirectory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private UserRepository userRepository;

    //get all mobiles   
    @GetMapping("/mobiles")
    public List<Mobile> getAllMobiles() {
        int id = 7;
//        System.out.println("mobiles here");
//        System.out.println("++++++++++++" + mobileRepository.findById(id));
        List<Mobile> mobiles = mobileRepository.findAll();
//        System.out.println("----------------------------------------" + mobiles);
         return mobiles;
    }

//    find by Id
    @GetMapping("mobiles/{id}")
    public ResponseEntity<Mobile> getMobileById(@PathVariable int id) {
        Optional<Mobile> mobile = mobileRepository.findById(id);
        if (mobile.isPresent()) {
            return ResponseEntity.ok(mobile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    //create/add mobile
//    @PostMapping
//    public ResponseEntity<Mobile> createMobile(@RequestBody Mobile mobile) {
//
//        User user = userRepository.findById(mobile.getUserId().getId()).get();
//        mobile.setUserId(user);
//        Mobile savedMobile = mobileRepository.save(mobile);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedMobile);
//    }

    @PutMapping("mobiles/{id}")
    public ResponseEntity<Mobile> updateMobile(@PathVariable int id, @RequestBody Mobile mobile) {
        Optional<Mobile> existingMobile = mobileRepository.findById(id);

        if (existingMobile.isPresent()) {
            User user = userRepository.findById(mobile.getUserId().getId()).get();
            mobile.setUserId(user);
            mobile.setId(id);
            Mobile savedMobile = mobileRepository.save(mobile);
            return ResponseEntity.ok(savedMobile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("mobiles/{id}")
    public ResponseEntity<Void> deleteMobile(@PathVariable int id) {
        Optional<Mobile> existingMobile = mobileRepository.findById(id);
        if (existingMobile.isPresent()) {

            mobileRepository.delete(existingMobile.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
