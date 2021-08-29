package com.devtest.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtest.api.model.Message;
import com.devtest.api.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	/**
	 * Get a message from the repository
	 * @param id The id of the message to get
	 * @return the message saved
	 */
	public Optional<Message> getMessage(final Long id) {
        return messageRepository.findById(id);
    }

	/**
	 * Get all the message from the repository
	 * @return The list of message
	 */
    public Iterable<Message> getMessage() {
        return messageRepository.findAll();
    }

    /**
     * Create or update a message to the repository
     * @param message The message object to persist
     * @return The Message object saved
     */
    public Message saveMessage(Message message) {
    	Message savedMessage = messageRepository.save(message);
        return savedMessage;
    }

}
