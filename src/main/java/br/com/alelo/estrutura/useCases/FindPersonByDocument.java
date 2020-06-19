package br.com.alelo.estrutura.useCases;

import br.com.alelo.estrutura.vo.PersonVO;

import java.util.List;

public interface FindPersonByDocument {
    PersonVO findByDocument(String document);
}
