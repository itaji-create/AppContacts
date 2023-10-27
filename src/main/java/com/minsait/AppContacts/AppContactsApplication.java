package com.minsait.AppContacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.minsait.AppContacts.models.entities")
@EnableJpaRepositories("com.minsait.AppContacts.models.repositories")
@ComponentScan("com.minsait.AppContacts")
public class AppContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppContactsApplication.class, args);
	}

}
