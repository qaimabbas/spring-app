package springapp.com.learning.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springapp.com.learning.journalApp.entity.User;
import springapp.com.learning.journalApp.repository.UserRepo;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public void createUser(User user){
        userRepo.save(user);
    }

   public List<User> getAllUsers(){
    return userRepo.findAll();
   }

   public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
   }


}

