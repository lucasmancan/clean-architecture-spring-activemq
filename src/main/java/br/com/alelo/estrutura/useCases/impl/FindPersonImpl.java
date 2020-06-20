package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.exceptions.NotFoundException;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindPerson;
import br.com.alelo.estrutura.vos.PersonVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindPersonImpl implements FindPerson {

    private final PersonRepository personRepository;
    private final PersonConverter converter;

    @Override
    public PersonVO findById(Long id) {
        return personRepository.findById(id)
                .map(converter::toVO)
                .orElseThrow(NotFoundException::new);
    }
}
