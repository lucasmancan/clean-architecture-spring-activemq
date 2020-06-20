package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindAllPerson;
import br.com.alelo.estrutura.vos.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAllPersonImpl implements FindAllPerson {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter converter;

    @Override
    public List<PersonVO> findAll() {
        return personRepository.findAll()
                .stream()
                .map(converter::toVO).collect(Collectors.toList());
    }
}
