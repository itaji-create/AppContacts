package com.minsait.AppContacts.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.AppContacts.controllers.dto.DirectMailDTO;
import com.minsait.AppContacts.controllers.dto.ResponseDTO;
import com.minsait.AppContacts.models.entities.Person;
import com.minsait.AppContacts.services.PersonService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "https://appcontacts-production.up.railway.app")
@RequestMapping(value = "/api/pessoas")
public class PersonController {
	private final PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@Operation(summary = "Create a new person")
	@PostMapping()
	public ResponseEntity<ResponseDTO<Person>> createPerson(@RequestBody Person person) {
		if (person.getName() == null) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<>("You must fill in the name field to create a new user.", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
		}
		Person newPerson = personService.insertPerson(person);
		ResponseDTO<Person> responseDTO = new ResponseDTO<>("Person created successfully!", newPerson);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
	@Operation(summary = "Update the person's data by ID")
	@PutMapping("/{personId}")
	public ResponseEntity<ResponseDTO<Person>> updatePerson(@PathVariable Long personId, @RequestBody Person person) {
		if (person.getName() == null) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<>("You must fill in the name field to update a user.", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
		}
	
		Optional<Person> optionalPerson = personService.updatePerson(personId, person);
		
		if (optionalPerson.isPresent()) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<>(
					"Person's data has been updated in the database!", optionalPerson.get());
			return ResponseEntity.ok(responseDTO);
		}
		
		ResponseDTO<Person> responseDTO = new ResponseDTO<>(
                String.format("The person was not found with ID %d", personId), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	}
	
	@Operation(summary = "Delete a person by ID")
	@DeleteMapping("/{personId}")
	public ResponseEntity<ResponseDTO<Person>> deletePerson(@PathVariable Long personId) {
		Optional<Person> optionalPerson = personService.removePersonById(personId);
		
		if (optionalPerson.isEmpty()) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<> (
					String.format("The person was not found with ID %d", personId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
		
		ResponseDTO<Person> responseDTO = new ResponseDTO<>("Person removed from database", null);
		return ResponseEntity.ok(responseDTO);
	}
	
	@Operation(summary = "Retrieve a person by ID")
	@GetMapping("/{personId}")
	public ResponseEntity<ResponseDTO<Person>> getPersonById(@PathVariable Long personId) {
		Optional<Person> optionalPerson = personService.getPersonById(personId);
		
		if (optionalPerson.isEmpty()) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<>(
					String.format("The person was not found with ID %d", personId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}

		ResponseDTO<Person> responseDTO = new ResponseDTO<>("Person found successfully!", optionalPerson.get());
	    return ResponseEntity.ok(responseDTO);
	}
	
	@Operation(summary = "Generate direct mail data for a person by ID")
	@GetMapping("/maladireta/{personId}")
	public ResponseEntity<ResponseDTO<DirectMailDTO>> directMail(@PathVariable Long personId) {
		Optional<Person> optionalPerson = personService.getPersonById(personId);

		if (optionalPerson.isEmpty()) {
			ResponseDTO<DirectMailDTO> responseDTO = new ResponseDTO<>(
					String.format("The person was not found with ID %d", personId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
		Person person = optionalPerson.get();

		String directMailField = person.getAddress() + " - " + "CEP: " + person.getCep() + " - " + person.getCity() + "/" + person.getUf();
		DirectMailDTO directMailDTO = new DirectMailDTO(person.getId(), person.getName(), directMailField);
		
		ResponseDTO<DirectMailDTO> responseDTO = new ResponseDTO<>("Person found successfully!", directMailDTO);
	    return ResponseEntity.ok(responseDTO);
	}
	
	@Operation(summary = "Retrieve a list of all people")
	@GetMapping()
	public ResponseEntity<List<Person>> getAllPeople() {
		List<Person> allPeopleList = personService.getAllPeople();
		
		if(allPeopleList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(allPeopleList);
	}
	
}
