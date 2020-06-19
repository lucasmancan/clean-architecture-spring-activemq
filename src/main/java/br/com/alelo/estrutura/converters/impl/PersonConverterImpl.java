package br.com.alelo.estrutura.converters.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.domain.Person;
import br.com.alelo.estrutura.vo.PersonVO;
import org.springframework.stereotype.Component;

@Component
public class PersonConverterImpl implements PersonConverter {
    @Override
    public Person toEntity(PersonVO vo) {
        return Person.builder()
                .document(vo.getDocument())
                .age(vo.getAge())
                .name(vo.getName())
                .id(vo.getId())
                .build();
    }

    @Override
    public PersonVO toVO(Person entity) {
        return PersonVO.builder().age(entity.getAge()).name(entity.getAge()).document(entity.getDocument()).id(entity.getId()).build();
    }
}