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

import com.minsait.AppContacts.controllers.dto.DirectMailDTO;
import com.minsait.AppContacts.controllers.dto.ResponseDTO;
import com.minsait.AppContacts.models.entities.Person;
import com.minsait.AppContacts.services.PersonService;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PersonController {
	private final PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping()
	public ResponseEntity<ResponseDTO<Person>> createPerson(@RequestBody Person person) {
		Person newPerson = personService.insertPerson(person);
		ResponseDTO<Person> responseDTO = new ResponseDTO<>("Pessoa criada com sucesso!", newPerson);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
	@PutMapping("/{personId}")
	public ResponseEntity<ResponseDTO<Person>> updatePerson(@PathVariable Long personId, @RequestBody Person person) {
		Optional<Person> optionalPerson = personService.updatePerson(personId, person);
		
		if (optionalPerson.isPresent()) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<>(
					"Dados da pessoa foram atualizados no banco de dados!", optionalPerson.get());
			return ResponseEntity.ok(responseDTO);
		}
		
		ResponseDTO<Person> responseDTO = new ResponseDTO<>(
                String.format("Não foi encontrado a pessoa com ID %d", personId), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	}
	
	@DeleteMapping("/{personId}")
	public ResponseEntity<ResponseDTO<Person>> deletePerson(@PathVariable Long personId) {
		Optional<Person> optionalPerson = personService.removePersonById(personId);
		
		if (optionalPerson.isEmpty()) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<> (
					String.format("Não foi encontrado a pessoa com ID %d", personId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
		
		ResponseDTO<Person> responseDTO = new ResponseDTO<>("Pessoa removida do banco de dados", null);
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping("/{personId}")
	public ResponseEntity<ResponseDTO<Person>> getPersonById(@PathVariable Long personId) {
		Optional<Person> optionalPerson = personService.getPersonById(personId);
		
		if (optionalPerson.isEmpty()) {
			ResponseDTO<Person> responseDTO = new ResponseDTO<>(
					String.format("Não foi encontrado uma pessoa com ID %d", personId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}

		ResponseDTO<Person> responseDTO = new ResponseDTO<>("Pessoa encontrada com sucesso!", optionalPerson.get());
	    return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping("/maladireta/{personId}")
	public ResponseEntity<ResponseDTO<DirectMailDTO>> directMail(@PathVariable Long personId) {
		Optional<Person> optionalPerson = personService.getPersonById(personId);

		if (optionalPerson.isEmpty()) {
			ResponseDTO<DirectMailDTO> responseDTO = new ResponseDTO<>(
					String.format("Não foi encontrado uma pessoa com ID %d", personId), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
		Person person = optionalPerson.get();

		String directMailField = person.getAddress() + " - " + person.getCep() + " - " + person.getCity() + "/" + person.getUf();
		DirectMailDTO directMailDTO = new DirectMailDTO(person.getId(), person.getName(), directMailField);
		
		ResponseDTO<DirectMailDTO> responseDTO = new ResponseDTO<>("Pessoa encontrada com sucesso!", directMailDTO);
	    return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping()
	public ResponseEntity<List<Person>> getAllPeople() {
		List<Person> allPeopleList = personService.getAllPeople();
		
		if(allPeopleList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(allPeopleList);
	}
	
}
