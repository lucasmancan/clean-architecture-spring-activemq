package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.entities.Person;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.SavePerson;
import br.com.alelo.estrutura.vos.PersonVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SavePersonImpl implements SavePerson {

    private final PersonRepository personRepository;
    private final PersonConverter converter;

    @Override
    public PersonVO save(PersonVO vo) {
        final Person newPerson = personRepository.save(converter.toEntity(vo));

        return converter.toVO(newPerson);
    }
}
