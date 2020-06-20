package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.InactivatePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactivatePersonImpl implements InactivatePerson {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void inactivate(Long id) {
        personRepository.deleteById(id);
    }
}
