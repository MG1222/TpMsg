package com.rootincode.twitterapi.Controller;

import com.rootincode.twitterapi.Model.DTO.MessageDTO;
import com.rootincode.twitterapi.Model.Entity.Message;
import com.rootincode.twitterapi.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @GetMapping
    public List<MessageDTO> showAll() {
        return this.messageService.getAllMessageDTOs();
    }

    @GetMapping("/{id}")
    public MessageDTO showById(@PathVariable Integer id) {
        return this.messageService.getMessageDTOById(id);
    }

    @PostMapping
    public MessageDTO addMessageDTO(@RequestBody MessageDTO messageDTO) {
        return this.messageService.createMessageDTO(messageDTO);
    }

    @PutMapping("/{id}")
    public MessageDTO updateMessageDTO(@PathVariable Integer id, @RequestBody MessageDTO messageDTO) {
        return this.messageService.updateMessageDTO(messageDTO, id);
    }


    @DeleteMapping("/{id}")
    public String deleteMessageDTO(@PathVariable Integer id) {
         this.messageService.deleteMessageDTOById(id);
         return "Deleted ";
    }
}
