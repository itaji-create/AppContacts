package com.minsait.AppContacts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.AppContacts.models.entities.Contact;
import com.minsait.AppContacts.models.repositories.ContactRepository;
import com.minsait.AppContacts.services.interfaces.ContactServiceInterface;

@Service
public class ContactsService implements ContactServiceInterface{
	ContactRepository contactRepository;
	
	@Autowired
	public ContactsService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Optional<Contact> getContactById(Long id) {
		return contactRepository.findById(id);
	}

	@Override
	public Contact insertContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Optional<Contact> updateContact(Long id, Contact contact) {
		Optional<Contact> optionalContact = contactRepository.findById(id);
		
		if(optionalContact.isPresent()) {
			Contact contactFromDb = optionalContact.get();
			contactFromDb.setContact(contact.getContact());
			contactFromDb.setContactType(contact.getContactType());
			contactFromDb.setPerson(contact.getPerson());

			
			Contact updatedContact = contactRepository.save(contactFromDb);
			return Optional.of(updatedContact);
		}
		
		return optionalContact;
	}

	@Override
	public Optional<Contact> removeContactById(Long id) {
		Optional<Contact> contactOptional = contactRepository.findById(id);
		
		if(contactOptional.isPresent()) {
			contactRepository.deleteById(id);
		}
		
		return contactOptional;
	}

}
