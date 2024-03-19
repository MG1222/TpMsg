package com.rootincode.twitterapi.Service;

import com.rootincode.twitterapi.Model.DTO.MessageDTO;
import com.rootincode.twitterapi.Model.Entity.Message;
import com.rootincode.twitterapi.Model.Repository.MessageRepository;
import com.rootincode.twitterapi.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    // for DTO
    private UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // -- DTO --


    // convert message to messageDTO

    public MessageDTO convertMessageToMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message.getMessage());
        messageDTO.setId(message.getId());
        messageDTO.setSender_id(message.getSender().getId());
        messageDTO.setReceiver_id(message.getReceiver().getId());
        return messageDTO;
    }

    public Message convertMessageDTOToMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setMessage(messageDTO.getMessage());
        message.setSender(userService.getById(messageDTO.getSender_id()).get());
        message.setReceiver(userService.getById(messageDTO.getReceiver_id()).get());
        return message;
    }

    public MessageDTO createMessageDTO(MessageDTO messageDTO) {

        var messageRep = messageRepository.save(this.convertMessageDTOToMessage(messageDTO));
        return this.convertMessageToMessageDTO(messageRep);
    }

    public MessageDTO updateMessageDTO(MessageDTO data, Integer id) {
      MessageDTO oldMsgDTO = this.convertMessageToMessageDTO(messageRepository.findById(id).get());
      oldMsgDTO.setMessage(data.getMessage());
      oldMsgDTO.setSender_id(data.getSender_id());
      oldMsgDTO.setReceiver_id(data.getReceiver_id());
      return this.createMessageDTO(oldMsgDTO);
    }

    public MessageDTO getMessageDTOById(Integer id) {
        // Bool
        if (messageRepository.existsById(id)) {
            Message message = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Message not found with id: " + id));
            return convertMessageToMessageDTO(message);
        } else {
            throw new IllegalArgumentException("Message not found with id: " + id);
        }
    }

    public List<MessageDTO> getAllMessageDTOs() {
        Iterable<Message> messages = messageRepository.findAll();
        return StreamSupport.stream(messages.spliterator(), false)
                .map(this::convertMessageToMessageDTO)
                .collect(Collectors.toList());
    }

    public void deleteMessageDTOById(Integer id) {
        messageRepository.deleteById(id);
    }







//    public Iterable<Message> getAll() {
//        return messageRepository.findAll();
//    }
//
//    public Optional<Message> getById(Integer id) {
//        return messageRepository.findById(id);
//    }
//
//    public Message addMsg(Message data) {
//        return messageRepository.save(data);
//    }
//
//    public Message updateByIdMsg(Integer id, Message data) {
//        data.setId(id);
//        return messageRepository.save(data);
//    }
//
//    public void deleteById(Integer id) {
//        messageRepository.deleteById(id);
//    }





}
