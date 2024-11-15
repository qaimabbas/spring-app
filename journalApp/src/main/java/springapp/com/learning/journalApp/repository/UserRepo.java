package springapp.com.learning.journalApp.repository;

import org.bson.types.ObjectId;
import springapp.com.learning.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends  MongoRepository<User, ObjectId> {

    User findByUserName(String username);

}
