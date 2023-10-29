package com.minsait.AppContacts.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.AppContacts.controllers.dto.ResponseDTO;
import com.minsait.AppContacts.models.entities.Contact;
import com.minsait.AppContacts.services.ContactsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/api")
public class ContactController {
	private final ContactsService contactsService;
	
	@Autowired
	public ContactController(ContactsService contactsService) {
		this.contactsService = contactsService;
	}
	
	@Operation(summary = "Create a new contact for a person by person ID")
	@PostMapping("/pessoas/{personId}/contatos")
	public ResponseEntity<ResponseDTO<Contact>> createContact(@PathVariable Long personId, @RequestBody Contact contact) {
		Contact newContact = contactsService.insertContact(personId, contact);
		if(newContact == null) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<>("The person this contact is associating with was not found", null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);			
		}
		ResponseDTO<Contact> responseDTO = new ResponseDTO<>("Contact created successfully!", newContact);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
	@Operation(summary = "Retrieve all contacts for a person by person ID")
	@GetMapping("/pessoas/{personId}/contatos")
	public ResponseEntity<List<Contact>> getAllContactsByPersonId(@PathVariable Long personId) {
		List<Contact> allContactsList = contactsService.getAllContactsByPersonId(personId);
		
		if(allContactsList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(allContactsList);
	}
	
	@Operation(summary = "Update contact information by contact ID")
	@PutMapping("/contatos/{contactId}")
	public ResponseEntity<ResponseDTO<Contact>> updateContact(@PathVariable Long contactId, @RequestBody Contact contact) {
		Optional<Contact> optionalContact = contactsService.updateContact(contactId, contact);
		
		if (optionalContact.isPresent()) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<>(
					"Contact details have been updated in the database!", optionalContact.get());
			return ResponseEntity.ok(responseDTO);
		}
		
		ResponseDTO<Contact> responseDTO = new ResponseDTO<>(
                String.format("The contact was not found with ID %d", contactId), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	}
	
	@Operation(summary = "Delete a contact by contact ID")
	@DeleteMapping("/contatos/{contactId}")
	public ResponseEntity<ResponseDTO<Contact>> deleteContact(@PathVariable Long contactId) {
		Optional<Contact> optionalContact = contactsService.removeContactById(contactId);
		
		if (optionalContact.isEmpty()) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<> (
					String.format("The contact was not found with ID %d", contactId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
		
		ResponseDTO<Contact> responseDTO = new ResponseDTO<>("Contact removed from database", null);
		return ResponseEntity.ok(responseDTO);
	}
	
	@Operation(summary = "Retrieve a contact by contact ID")
	@GetMapping("/contatos/{contactId}")
	public ResponseEntity<ResponseDTO<Contact>> getContactById(@PathVariable Long contactId) {
		Optional<Contact> optionalContact = contactsService.getContactById(contactId);
		
		if (optionalContact.isEmpty()) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<>(
					String.format("The contact was not found with ID %d", contactId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}

		ResponseDTO<Contact> responseDTO = new ResponseDTO<>("Contact found successfully!", optionalContact.get());
	    return ResponseEntity.ok(responseDTO);
	}

}
