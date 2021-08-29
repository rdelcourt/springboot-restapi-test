package com.devtest.api.controller;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtest.api.exception.IntrouvableException;
import com.devtest.api.model.Message;
import com.devtest.api.model.Message.Channel;
import com.devtest.api.service.MessageService;

import io.swagger.annotations.Api;

@Api(description = "API pour les opérations de CRUD sur les messages")
@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	/**
	* Read - Get all messages
	* @return - An Iterable object of messages
	*/
	@GetMapping("/messages")
	public Iterable<Message> getMessages() {
	    return messageService.getMessage();
	}
	
	/**
	 * Read - Get one message
	 * @param id The id of the message to get
	 * @return The message object saved
	 */
	@GetMapping(value="/message/{id}")
    public Message getMessage(@PathVariable final Long id) {
    	Optional<Message> message = messageService.getMessage(id);
    	if(message.isPresent()) {
    		return message.get();
    	}
    	else {
    		throw new IntrouvableException("Le message avec l'id " + id + " est introuvable.");
    	}
    }
    
	/**
	 * Create a new message
	 * @param message The message object to create
	 * @return The HTTP response that indicate if the message has been created
	 */
    @PostMapping("/message")
	public ResponseEntity<Void> createMessage(@RequestBody Message message) {
    	Date now = new Date();
    	message.setDate(now);
    	
    	Message messageAdded = messageService.saveMessage(message);
    	if (messageAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(messageAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
	}
    
    /**
     * Update a message
     * @param id The id of the message to update
     * @param message The message object with his new content
     * @return The HTTP response that indicate if the message has been updated
     */
    @PutMapping("/message/{id}")
	public ResponseEntity<Void> updateMessage(@PathVariable final Long id, @RequestBody Message message) {
    	Optional<Message> m = messageService.getMessage(id);
    	
    	if(m.isPresent()) {
    		Message currentMessage = m.get();
    		
    		String author = message.getAuthor();
			if(author != null) {
				currentMessage.setAuthor(author);
			}
			
			String content = message.getContent();
			if(content != null) {
				currentMessage.setContent(content);
			}
			
			Channel channel = message.getChannel();
			if(channel != null) {
				currentMessage.setChannel(channel);
			}
			
			Date date = message.getDate();
			if(date != null) {
				currentMessage.setDate(date);
			}
			
			Message messageEdited = messageService.saveMessage(message);
	    	if (messageEdited == null)
	            return ResponseEntity.noContent().build();

	        return ResponseEntity.ok().build();
		} else {
			throw new IntrouvableException("Le message avec l'id " + id + " est introuvable.");
		}
	}
}
