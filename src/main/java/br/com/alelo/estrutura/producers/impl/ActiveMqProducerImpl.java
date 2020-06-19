package br.com.alelo.estrutura.producers.impl;

import br.com.alelo.estrutura.producers.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.Level;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.UUID;

/**
 *
 */

@Log4j2
@Service
public class ActiveMqProducerImpl implements Producer {

    private final JmsTemplate jmsTemplate;

    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final ObjectMapper mapper;

    public ActiveMqProducerImpl(JmsTemplate jmsTemplate, JmsMessagingTemplate jmsMessagingTemplate, ObjectMapper mapper) {
        this.jmsTemplate = jmsTemplate;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.mapper = mapper;
    }

    @Override
    public String send(Object o) {
        try {

            jmsTemplate.setReceiveTimeout(1000L);
            jmsMessagingTemplate.setJmsTemplate(jmsTemplate);

            Session session = jmsMessagingTemplate.getConnectionFactory().createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE);

            ObjectMessage objectMessage = session.createObjectMessage(mapper.writeValueAsString(o));
            objectMessage.setJMSCorrelationID(UUID.randomUUID().toString());
            objectMessage.setJMSReplyTo(new ActiveMQQueue("teste.response"));
            objectMessage.setJMSCorrelationID(UUID.randomUUID().toString());
            objectMessage.setJMSExpiration(1000L);
            objectMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

            return jmsMessagingTemplate.convertSendAndReceive(new ActiveMQQueue("teste.response"), objectMessage, String.class);
        } catch (JsonProcessingException | JMSException e) {
            log.log(Level.ERROR, "Erro produzir mensagem.", e);
            return null;
        }
    }
}
