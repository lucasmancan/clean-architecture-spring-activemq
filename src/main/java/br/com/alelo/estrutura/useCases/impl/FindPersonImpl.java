package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.exception.NotFoundException;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindPerson;
import br.com.alelo.estrutura.vo.PersonVO;
import org.springframework.stereotype.Component;

@Component
public class FindPersonImpl implements FindPerson {

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private PersonRepository personRepository;

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private PersonConverter converter;

    // O CDI do Spring Injeta a implementação automaticamente, sem necessidade de colocar o @Autowired
    public FindPersonImpl(PersonRepository personRepository, PersonConverter converter) {
        this.personRepository = personRepository;
        this.converter = converter;
    }

    @Override
    public PersonVO findById(Long id) {
        return personRepository.findById(id).map(person -> converter.toVO(person)).orElseThrow(NotFoundException::new);
    }
}