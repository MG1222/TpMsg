package com.rootincode.twitterapi.Controller;

import com.rootincode.twitterapi.Model.Entity.Message;
import com.rootincode.twitterapi.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @GetMapping
    public Iterable<Message> showAll() {
        return this.messageService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> showById(@PathVariable Integer id) {
        Optional<Message> messageOptional = this.messageService.getById(id);
        return messageOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Message> createMsg(@RequestBody Message message) {
        Message newMsg = this.messageService.addMsg(message);
        return new ResponseEntity<>(newMsg, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMsg(@PathVariable Integer id, @RequestBody Message message) {
        Optional<Message> existingMessageOptional = this.messageService.getById(id);
        if (existingMessageOptional.isPresent()) {
            Message updateMessage = this.messageService.updateByIdMsg(id, message);
            return  new ResponseEntity<>(updateMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMsg(@PathVariable Integer id) {
        Optional<Message> existingMsgOptional = this.messageService.getById(id);
        if (existingMsgOptional.isPresent()) {
            this.messageService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
