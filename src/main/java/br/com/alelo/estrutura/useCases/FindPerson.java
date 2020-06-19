package br.com.alelo.estrutura.useCases;

import br.com.alelo.estrutura.vos.PersonVO;

public interface FindPerson {
    PersonVO findById(Long id);
}
