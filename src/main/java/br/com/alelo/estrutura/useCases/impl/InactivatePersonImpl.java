package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.InactivatePerson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InactivatePersonImpl implements InactivatePerson {

    private final PersonRepository personRepository;

    @Override
    public void inactivate(Long id) {
        personRepository.deleteById(id);
    }
}
