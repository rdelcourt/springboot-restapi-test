package com.devtest.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtest.api.model.CustomerFolder;
import com.devtest.api.model.Message;
import com.devtest.api.repository.CustomerFolderRepository;

@Service
public class CustomerFolderService {
	
	@Autowired
	private CustomerFolderRepository customerFolderRepository;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * Get a customer folder from the repository
	 * @param id The id of the customer folder to get
	 * @return The CustomerFolder saved
	 */
	public Optional<CustomerFolder> getCustomerFolder(final Long id) {
        return customerFolderRepository.findById(id);
    }

	/**
	 * Get all the customer folder from the repository
	 * @return The list of customer folder
	 */
    public Iterable<CustomerFolder> getCustomerFolder() {
        return customerFolderRepository.findAll();
    }
    
    /**
     * Create or update a customer folder to the repository
     * @param message The customer folder object to persist
     * @return The CustomerFolder object saved
     */
    public CustomerFolder saveCustomerFolder(CustomerFolder customerFolder) {
    	CustomerFolder savedCustomerFolder = customerFolderRepository.save(customerFolder);
    	
    	for (Message message : customerFolder.getMessages()) {
    		Optional<Message> m = messageService.getMessage(message.getId());
    		if(m.isPresent()) {
    			Message savedMessage = m.get();
    			savedMessage.setCustomerFolder(savedCustomerFolder);
    			messageService.saveMessage(savedMessage);
    		}
		}
    	
        return savedCustomerFolder;
    }


}
