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
import com.devtest.api.model.CustomerFolder;
import com.devtest.api.service.CustomerFolderService;

import io.swagger.annotations.Api;

@Api(description = "API pour les opérations de CRUD sur les dossiers clients")
@RestController
public class CustomerFolderController {

	@Autowired
	private CustomerFolderService customerFolderService;
	
	/**
	* Read - Get all customer folders
	* @return - An Iterable object of customer folders
	*/
	@GetMapping("/customer-folders")
	public Iterable<CustomerFolder> getCustomerFolders() {
	    return customerFolderService.getCustomerFolder();
	}
	
	/**
	 * Read - Get one customer folder
	 * @param id The id of the customer folder to get
	 * @return The customer folder object saved
	 */
    @GetMapping(value="/customer-folder/{id}")
    public CustomerFolder getCustomerFolder(@PathVariable final Long id) {
    	Optional<CustomerFolder> customerFolder = customerFolderService.getCustomerFolder(id);
    	if(customerFolder.isPresent()) {
    		return customerFolder.get();
    	}
    	else {
    		throw new IntrouvableException("Le dossier client avec l'id " + id + " est introuvable.");
    	}
    }
    
    /**
	 * Create a new customer folder
	 * @param customerFolder The customer folder object to create
	 * @return The HTTP response that indicate if the customer folder has been created
	 */
    @PostMapping("/customer-folder")
	public ResponseEntity<Void> createCustomerFolder(@RequestBody CustomerFolder customerFolder) {
    	Date now = new Date();
    	customerFolder.setOpenDate(now);
    	
    	CustomerFolder customerFolderAdded = customerFolderService.saveCustomerFolder(customerFolder);
    	if (customerFolderAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerFolderAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
	}
    
    /**
     * Update a customer folder
     * @param id The id of the customer folder to update
     * @param customerFolder The customer folder object with his new content
     * @return The HTTP response that indicate if the customer folder has been updated
     */
    @PutMapping("/customer-folder/{id}")
	public ResponseEntity<Void> updateCustomerFolder(@PathVariable final Long id, @RequestBody CustomerFolder customerFolder) {
    	Optional<CustomerFolder> cF = customerFolderService.getCustomerFolder(id);
    	
    	if(cF.isPresent()) {
    		CustomerFolder currentCustomerFolder = cF.get();
    		
    		String customerName = customerFolder.getCustomerName();
			if(customerName != null) {
				currentCustomerFolder.setCustomerName(customerName);
			}
			
			String reference = customerFolder.getReference();
			if(reference != null) {
				currentCustomerFolder.setReference(reference);
			}
			
			Date openDate = customerFolder.getOpenDate();
			if(openDate != null) {
				currentCustomerFolder.setOpenDate(openDate);
			}
						
			CustomerFolder customerFolderEdited = customerFolderService.saveCustomerFolder(currentCustomerFolder);
	    	if (customerFolderEdited == null)
	            return ResponseEntity.noContent().build();

	        return ResponseEntity.ok().build();
		} else {
			throw new IntrouvableException("Le dossier client avec l'id " + id + " est introuvable.");
		}
	}
  
}
