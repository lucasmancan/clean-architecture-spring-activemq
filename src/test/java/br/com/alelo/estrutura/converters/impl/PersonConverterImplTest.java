package br.com.alelo.estrutura.converters.impl;

import br.com.alelo.estrutura.entities.Person;
import br.com.alelo.estrutura.vos.PersonVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersonConverterImpl.class})
public class PersonConverterImplTest {

    @Autowired
    private PersonConverterImpl personConverter;


    @Test
    public void shouldConvertEntityToVoCorretly() {

        Person person = Person.builder().id(34L).name("TESTE").document("12312312312").age(12).build();

        PersonVO personVO = personConverter.toVO(person);


        assertEquals(person.getId(), personVO.getId());
        assertEquals(person.getName(), personVO.getName());
        assertEquals(person.getDocument(), personVO.getDocument());
        assertEquals(person.getAge(), personVO.getAge());
    }

    @Test
    public void shouldConvertVOToEntityCorretly() {
        PersonVO vo = PersonVO.builder().id(34L).name("TESTE").document("12312312312").age(12).build();

        Person person = personConverter.toEntity(vo);


        assertEquals(vo.getId(), person.getId());
        assertEquals(vo.getName(), person.getName());
        assertEquals(vo.getDocument(), person.getDocument());
        assertEquals(vo.getAge(), person.getAge());

    }
}