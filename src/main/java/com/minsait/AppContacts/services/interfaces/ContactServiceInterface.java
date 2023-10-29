package com.minsait.AppContacts.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.minsait.AppContacts.models.entities.Contact;


public interface ContactServiceInterface {
	List<Contact> getAllContacts();
	Optional<Contact> getContactById(Long id);
	Contact insertContact(Contact contact);
	Optional<Contact> updateContact(Long id, Contact contact);
	Optional<Contact> removeContactById(Long id);
}
