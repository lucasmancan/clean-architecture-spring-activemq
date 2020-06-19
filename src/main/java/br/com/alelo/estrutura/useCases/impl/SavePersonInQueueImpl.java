package br.com.alelo.estrutura.useCases.impl;

import br.com.alelo.estrutura.exception.CustomException;
import br.com.alelo.estrutura.producers.Producer;
import br.com.alelo.estrutura.useCases.SavePersonInQueue;
import br.com.alelo.estrutura.vos.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SavePersonInQueueImpl implements SavePersonInQueue {

    private final Producer producer;

    private final ObjectMapper mapper;

    public SavePersonInQueueImpl(Producer producer, ObjectMapper mapper) {
        this.producer = producer;
        this.mapper = mapper;
    }

    @Override
    public PersonVO saveInQueue(PersonVO personVO) {
        final String mensagem = producer.send(personVO);

        try {
            return mapper.readValue(mensagem, PersonVO.class);
        } catch (JsonProcessingException e) {
            throw new CustomException("An internal error occurred");
        }
    }
}
