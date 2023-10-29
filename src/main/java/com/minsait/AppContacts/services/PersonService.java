package com.minsait.AppContacts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.AppContacts.models.entities.Person;
import com.minsait.AppContacts.models.repositories.PersonRepository;
import com.minsait.AppContacts.services.interfaces.PersonServiceInterface;

@Service
public class PersonService implements PersonServiceInterface {
	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public Person insertPerson(Person person) {
		return personRepository.save(person);
	}
	
	@Override
	public Optional<Person> updatePerson(Long id, Person person) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		if(optionalPerson.isPresent()) {
			Person personFromDb = optionalPerson.get();
			personFromDb.setName(person.getName());
			personFromDb.setAddress(person.getAddress());
			personFromDb.setCEP(person.getCep());
			personFromDb.setCity(person.getCity());
			personFromDb.setUf(person.getUf());
			
			Person updatedPerson = personRepository.save(personFromDb);
			return Optional.of(updatedPerson);
		}
		
		return optionalPerson;
	}
	
	@Override
	public Optional<Person> removePersonById(Long id) {
		Optional<Person> personOptional = personRepository.findById(id);
		
		if(personOptional.isPresent()) {
			personRepository.deleteById(id);
		}
		
		return personOptional;
	}
	
	@Override
	public Optional<Person> getPersonById(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public List<Person> getAllPeople() {
		return personRepository.findAll();
	}
}
