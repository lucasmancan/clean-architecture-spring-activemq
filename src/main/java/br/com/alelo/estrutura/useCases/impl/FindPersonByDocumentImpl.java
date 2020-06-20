package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.exceptions.NotFoundException;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.FindPersonByDocument;
import br.com.alelo.estrutura.vos.PersonVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindPersonByDocumentImpl implements FindPersonByDocument {

    private final PersonRepository personRepository;
    private final PersonConverter converter;

    @Override
    public PersonVO findByDocument(String document) {
        return personRepository.findByDocument(document).map(converter::toVO).orElseThrow(NotFoundException::new);
    }
}
