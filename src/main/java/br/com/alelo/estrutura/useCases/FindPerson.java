package br.com.alelo.estrutura.useCases;

import br.com.alelo.estrutura.vo.PersonVO;

public interface FindPerson {
    PersonVO findById(Long id);
}
