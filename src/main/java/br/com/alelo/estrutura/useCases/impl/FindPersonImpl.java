package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.exceptions.NotFoundException;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindPerson;
import br.com.alelo.estrutura.vos.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindPersonImpl implements FindPerson {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter converter;

    @Override
    public PersonVO findById(Long id) {
        return personRepository.findById(id).map(person -> converter.toVO(person)).orElseThrow(NotFoundException::new);
    }
}
