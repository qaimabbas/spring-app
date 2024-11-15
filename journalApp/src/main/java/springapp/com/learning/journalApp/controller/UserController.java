package springapp.com.learning.journalApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springapp.com.learning.journalApp.entity.User;
import springapp.com.learning.journalApp.services.UserService;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){

        List<User> allUsers = userService.getAllUsers();

        if(allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String  userName){

        // find the user by username
     User userInDb = userService.findByUserName(userName);
     // fi the user is in the DB is not null
     if(userInDb != null){
          // set the username
         userInDb.setUserName((user.getUserName()));
         // set the password
         userInDb.setPassword((user.getPassword()));
         // call the createUser method with new user details
         userService.createUser(userInDb);
     }
     // return http response of 200 means ok
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
