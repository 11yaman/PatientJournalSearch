package com.example.mapping;


import com.example.dto.MessageDto;
import com.example.dto.UserDto;
import com.example.model.Message;
import com.example.model.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MessageMapper implements StrategyMapper<Message, MessageDto> {
    @Override
    public MessageDto map(Message source) {
        User sender = source.getSender();
        UserDto senderDto = new UserDto(sender.getId(), sender.getUsername(),
                sender.getFirstName(), sender.getLastName(), sender.getBirthDate(), sender.getRole().name());
        return new MessageDto(source.getId(), source.getContent(), source.getDateTime(),
                senderDto, source.getStatus());
    }

    @Override
    public List<MessageDto> mapAll(List<Message> source) {
        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message m:
                source) {
            messageDtos.add(map(m));
        }
        return messageDtos;
    }

}
