package com.minsait.AppContacts.controllers.dto;

import com.minsait.AppContacts.models.entities.Person;

public record PersonDTO(Long id, String name, String address, String cep, String city, String uf) {
	public Person toPerson() {
		return new Person(id, name, address, cep, city, uf);
	}
}
