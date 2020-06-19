package br.com.alelo.estrutura.useCases;

import br.com.alelo.estrutura.vos.PersonVO;

import java.util.List;

public interface FindAllPerson {
    List<PersonVO> findAll();
}
