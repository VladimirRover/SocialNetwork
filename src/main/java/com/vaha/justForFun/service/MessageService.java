package com.vaha.justForFun.service;

import com.vaha.justForFun.domain.User;
import com.vaha.justForFun.domain.dto.MessageDto;
import com.vaha.justForFun.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user){

        if (filter != null && !filter.isEmpty()) {
            return messageRepository.findByTag(filter, pageable, user);
        } else {
            return messageRepository.findAll(pageable, user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User author, User currentUser) {

        return messageRepository.findByUser(pageable, author, currentUser);
    }
}
