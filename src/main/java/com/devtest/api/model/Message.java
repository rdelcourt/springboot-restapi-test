package com.devtest.api.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	private String author;
	
	private String content;

    private Channel channel;  
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_folder_id")
	@JsonBackReference
	private CustomerFolder customerFolder;
	
	/**
	 * The enum listing the message's channel possibilities
	 * @author rdelcourt
	 *
	 */
	public enum Channel {
       MAIL, SMS, FACEBOOK, TWITTER;
    };
	
	/**
	 * @return the message's channel
	 */
	public Channel getChannel(){
	       return channel; 
	    }

	/**
	 * 
	 * @param channel The channel to set
	 */
    public void setChannel(Channel channel){
        this.channel = channel;
    }

	/**
	 * @return the customerFolder
	 */
	public CustomerFolder getCustomerFolder() {
		return customerFolder;
	}

	/**
	 * @param customerFolder the customerFolder to set
	 */
	public void setCustomerFolder(CustomerFolder customerFolder) {
		this.customerFolder = customerFolder;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
