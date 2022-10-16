package com.visionrent.service;

import com.visionrent.domain.ContactMessage;
import com.visionrent.repository.ContactMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    public void saveMessage(ContactMessage contactMessage){
        contactMessageRepository.save(contactMessage);
    }
}
