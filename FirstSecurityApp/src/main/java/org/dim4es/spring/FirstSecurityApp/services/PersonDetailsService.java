package org.dim4es.spring.FirstSecurityApp.services;

import org.dim4es.spring.FirstSecurityApp.models.Person;
import org.dim4es.spring.FirstSecurityApp.repositories.PersonRepository;
import org.dim4es.spring.FirstSecurityApp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);

        if(person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
