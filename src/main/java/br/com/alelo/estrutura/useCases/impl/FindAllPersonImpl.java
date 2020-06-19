package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindAllPerson;
import br.com.alelo.estrutura.useCases.FindPerson;
import br.com.alelo.estrutura.vo.PersonVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAllPersonImpl implements FindAllPerson {

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonRepository personRepository;

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonConverter converter;

    // O CDI do Spring Injeta a implementação automaticamente, sem necessidade de colocar o @Autowired
    public FindAllPersonImpl(PersonRepository personRepository, PersonConverter converter) {
        this.personRepository = personRepository;
        this.converter = converter;
    }

    @Override
    public List<PersonVO> findAll() {
        return personRepository.findAll()
                .stream()
                .map(converter::toVO).collect(Collectors.toList());
    }
}
