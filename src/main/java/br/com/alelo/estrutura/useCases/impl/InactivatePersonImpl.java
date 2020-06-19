package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.useCases.InactivatePerson;
import org.springframework.stereotype.Component;

@Component
public class InactivatePersonImpl implements InactivatePerson {

    //@Autowired Dessa forma impossibilita mockar os objetos no teste
    private final PersonRepository personRepository;

    // O CDI do Spring Injeta a implementação automaticamente, sem necessidade de colocar o @Autowired
    public InactivatePersonImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void inactivate(Long id) {
        personRepository.deleteById(id);
    }
}
