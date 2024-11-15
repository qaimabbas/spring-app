package springapp.com.learning.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springapp.com.learning.journalApp.entity.JournalEntry;
import springapp.com.learning.journalApp.entity.User;
import springapp.com.learning.journalApp.services.JournalEntryService;
import springapp.com.learning.journalApp.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesByUser(@PathVariable String userName){

        User user = userService.findByUserName(userName);
        List<JournalEntry> allEntriesOfUser= user.getJournalEntries();

        if(allEntriesOfUser != null && !allEntriesOfUser.isEmpty()){
            return new ResponseEntity<>(allEntriesOfUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     @PostMapping("{userName}")
    private ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName){

         try {
             journalEntryService.saveJournalEntry(journalEntry, userName);
             return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
         }catch (Exception e ){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
     }

     @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteEntry(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }


     @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?>  updateEntry(@PathVariable ObjectId myId,
                                          @RequestBody JournalEntry newJournalEntry,
                                          @PathVariable String userName) {
         JournalEntry oldJournalEntry = journalEntryService.getEntryById(myId).orElse(null);
         if (oldJournalEntry != null) {
             oldJournalEntry.setTitle(newJournalEntry.getTitle() != null ? newJournalEntry.getTitle() : oldJournalEntry.getTitle());
             oldJournalEntry.setContent(newJournalEntry.getContent() != null ? newJournalEntry.getContent() : oldJournalEntry.getContent());
             journalEntryService.updateEntry(oldJournalEntry);
             return new ResponseEntity<>(oldJournalEntry,HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

     };

     };



