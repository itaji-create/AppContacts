package com.minsait.AppContacts.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minsait.AppContacts.models.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findByPersonId(Long pessoaId);
}
