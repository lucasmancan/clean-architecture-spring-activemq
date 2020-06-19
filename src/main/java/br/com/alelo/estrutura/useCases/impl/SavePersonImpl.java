package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.entities.Person;
import br.com.alelo.estrutura.producers.Producer;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.SavePerson;
import br.com.alelo.estrutura.vos.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SavePersonImpl implements SavePerson {

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonRepository personRepository;

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonConverter converter;

    private final ObjectMapper mapper;


    // O CDI do Spring Injeta a implementação automaticamente, sem necessidade de colocar o @Autowired
    public SavePersonImpl(PersonRepository personRepository, PersonConverter converter, ObjectMapper mapper) {
        this.personRepository = personRepository;
        this.converter = converter;
        this.mapper = mapper;
    }

    @Override
    public PersonVO save(PersonVO vo) {
        final Person newPerson = personRepository.save(converter.toEntity(vo));

        return converter.toVO(newPerson);
    }
}
