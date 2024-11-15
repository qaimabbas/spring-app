package springapp.com.learning.journalApp.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springapp.com.learning.journalApp.entity.JournalEntry;
import springapp.com.learning.journalApp.entity.User;
import springapp.com.learning.journalApp.repository.JournalEntryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;


    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName){

          User user = userService.findByUserName(userName);
           JournalEntry saved= journalEntryRepo.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.createUser(user);
    }

    public void updateEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteEntry(ObjectId id, String userName ){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.createUser(user);
        journalEntryRepo.deleteById(id);
    }

    public List<JournalEntry> getEntry(){
        return journalEntryRepo.findAll();
    }


}
