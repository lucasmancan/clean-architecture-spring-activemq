package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.exceptions.NotFoundException;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindPersonByDocument;
import br.com.alelo.estrutura.vos.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindPersonByDocumentImpl implements FindPersonByDocument {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter converter;

    @Override
    public PersonVO findByDocument(String document) {
        return personRepository.findByDocument(document).map(converter::toVO).orElseThrow(NotFoundException::new);
    }
}
