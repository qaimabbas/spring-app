package springapp.com.learning.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import springapp.com.learning.journalApp.entity.JournalEntry;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}