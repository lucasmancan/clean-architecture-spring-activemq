package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.entities.Person;
import br.com.alelo.estrutura.producers.Producer;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.SavePerson;
import br.com.alelo.estrutura.vos.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePersonImpl implements SavePerson {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter converter;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public PersonVO save(PersonVO vo) {
        final Person newPerson = personRepository.save(converter.toEntity(vo));

        return converter.toVO(newPerson);
    }
}
