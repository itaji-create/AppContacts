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

@RestController
@RequestMapping(value = "/api")
public class ContactController {
	private final ContactsService contactsService;
	
	@Autowired
	public ContactController(ContactsService contactsService) {
		this.contactsService = contactsService;
	}
	
	@PostMapping("/pessoas/{personId}/contatos")
	public ResponseEntity<ResponseDTO<Contact>> createContact(@PathVariable Long personId, @RequestBody Contact contact) {
		Contact newContact = contactsService.insertContact(personId, contact);
		if(newContact == null) {
			return ResponseEntity.notFound().build();			
		}
		ResponseDTO<Contact> responseDTO = new ResponseDTO<>("Contato criado com sucesso!", newContact);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
	@PutMapping("/contatos/{contactId}")
	public ResponseEntity<ResponseDTO<Contact>> updateContact(@PathVariable Long contactId, @RequestBody Contact contact) {
		Optional<Contact> optionalContact = contactsService.updateContact(contactId, contact);
		
		if (optionalContact.isPresent()) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<>(
					"Dados de contato foram atualizados no banco de dados!", optionalContact.get());
			return ResponseEntity.ok(responseDTO);
		}
		
		ResponseDTO<Contact> responseDTO = new ResponseDTO<>(
                String.format("Não foi encontrado o contato com ID %d", contactId), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	}
	
	@DeleteMapping("/contatos/{contactId}")
	public ResponseEntity<ResponseDTO<Contact>> deleteContact(@PathVariable Long contactId) {
		Optional<Contact> optionalContact = contactsService.removeContactById(contactId);
		
		if (optionalContact.isEmpty()) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<> (
					String.format("Não foi encontrado a pessoa com ID %d", contactId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
		
		ResponseDTO<Contact> responseDTO = new ResponseDTO<>("Contato removido do banco de dados", null);
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping("/contatos/{contactId}")
	public ResponseEntity<ResponseDTO<Contact>> getContactById(@PathVariable Long contactId) {
		Optional<Contact> optionalContact = contactsService.getContactById(contactId);
		
		if (optionalContact.isEmpty()) {
			ResponseDTO<Contact> responseDTO = new ResponseDTO<>(
					String.format("Não foi encontrado um contato com ID %d", contactId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}

		ResponseDTO<Contact> responseDTO = new ResponseDTO<>("Contato encontrada com sucesso!", optionalContact.get());
	    return ResponseEntity.ok(responseDTO);
	}
	

	@GetMapping("/pessoas/{personId}/contatos")
	public ResponseEntity<List<Contact>> getAllContactsByPersonId(@PathVariable Long personId) {
		List<Contact> allContactsList = contactsService.getAllContactsByPersonId(personId);
		
		if(allContactsList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(allContactsList);
	}
}
