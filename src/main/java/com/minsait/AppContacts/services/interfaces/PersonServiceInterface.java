package com.minsait.AppContacts.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.minsait.AppContacts.models.entities.Person;


public interface PersonServiceInterface {
	public interface ProductServiceInterface {
		List<Person> getAllPeople();
		Optional<Person> getPersonById(Long id);
		Person insertPerson(Person person);
		Optional<Person> updatePerson(Long id, Person person);
		Optional<Person> removePersonById(Long id);
	}

}
