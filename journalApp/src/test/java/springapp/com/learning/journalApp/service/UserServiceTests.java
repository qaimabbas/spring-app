package springapp.com.learning.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springapp.com.learning.journalApp.repository.UserRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testFindByUserName(){

        assertEquals(4,2+2);
        assertNotNull(userRepo.findByUserName("wafa"));
    }

}
