package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.converters.PersonConverter;
import br.com.alelo.estrutura.converters.impl.PersonConverterImpl;
import br.com.alelo.estrutura.entities.Person;
import br.com.alelo.estrutura.repositories.PersonRepository;
import br.com.alelo.estrutura.vos.PersonVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FindAllPersonImpl.class})
public class FindAllPersonImplTest {

    @Autowired
    private FindAllPersonImpl findAllPerson;

    @MockBean
    private PersonConverterImpl personConverter;

    @MockBean
    private PersonRepository repository;

    @Test
    public void shouldReturnAListOfSavedPeople() {

        Person person = Person.builder().name("TESTE").age(23).document("11111111111").build();
        PersonVO personVO = PersonVO.builder().name("TESTE").age(23).document("11111111111").build();

        when(personConverter.toVO(any(Person.class))).thenReturn(personVO);

        when(repository.findAll()).thenReturn(Arrays.asList(person, person, person));

        List<PersonVO> findAllPersonReturn = findAllPerson.findAll();

        assertEquals(findAllPersonReturn, Arrays.asList(personVO, personVO, personVO));
    }
}