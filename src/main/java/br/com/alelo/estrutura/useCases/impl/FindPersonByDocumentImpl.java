package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.exceptions.NotFoundException;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindPersonByDocument;
import br.com.alelo.estrutura.vos.PersonVO;
import org.springframework.stereotype.Component;

@Component
public class FindPersonByDocumentImpl implements FindPersonByDocument {

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonRepository personRepository;

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonConverter converter;

    // O CDI do Spring Injeta a implementação automaticamente, sem necessidade de colocar o @Autowired
    public FindPersonByDocumentImpl(PersonRepository personRepository, PersonConverter converter) {
        this.personRepository = personRepository;
        this.converter = converter;
    }

    @Override
    public PersonVO findByDocument(String document) {
        return personRepository.findByDocument(document).map(converter::toVO).orElseThrow(NotFoundException::new);
    }
}
