package com.rootincode.twitterapi.Service;

import com.rootincode.twitterapi.Model.Entity.Message;
import com.rootincode.twitterapi.Model.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Iterable<Message> getAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> getById(Integer id) {
        return messageRepository.findById(id);
    }

    public Message addMsg(Message data) {
        return messageRepository.save(data);
    }

    public Message updateByIdMsg(Integer id, Message data) {
        data.setId(id);
        return messageRepository.save(data);
    }

    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }

}
