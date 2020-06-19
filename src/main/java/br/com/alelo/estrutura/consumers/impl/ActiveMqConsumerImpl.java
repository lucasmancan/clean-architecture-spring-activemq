package br.com.alelo.estrutura.consumers.impl;

import br.com.alelo.estrutura.consumers.Consumer;
import br.com.alelo.estrutura.entities.Person;
import br.com.alelo.estrutura.useCases.SavePerson;
import br.com.alelo.estrutura.vos.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.logging.log4j.Level;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

@Log4j2
@Service
public class ActiveMqConsumerImpl implements Consumer {

    private final ObjectMapper mapper;
    private final SavePerson savePerson;

    public ActiveMqConsumerImpl(ObjectMapper mapper, SavePerson savePerson) {
        this.mapper = mapper;
        this.savePerson = savePerson;
    }


    /**
     * O controlador ou batch chama o producer, no momento somente a operação de salvar um registro é efetuada
     */

    @Override
    @JmsListener(destination = "teste.response", containerFactory = "jmsFactory")
    public void consume(Message message, Session session) {
        try {

            String value = (String) ((ActiveMQObjectMessage) message).getObject();

            PersonVO person = mapper.readValue(value, PersonVO.class);

            person = savePerson.save(person);

            log.log(Level.INFO, person.toString());

            // Monta a mensagem de resposta e envia ao producer
            final ObjectMessage responseMessage = createObjectMessage(message, person);

            final MessageProducer producer = session.createProducer(message.getJMSReplyTo());

            producer.send(responseMessage);

        } catch (JsonProcessingException | JMSException e) {
            log.log(Level.INFO, "Erro ao enviar mensagem para fila", e);
        }
    }

    private ObjectMessage createObjectMessage(Message message, Serializable anyObject) throws JMSException, JsonProcessingException {
        final ObjectMessage responseMessage = new ActiveMQObjectMessage();
        responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
        responseMessage.setObject(mapper.writeValueAsString(anyObject));

        return responseMessage;
    }

}
