package com.minsait.AppContacts.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minsait.AppContacts.models.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
