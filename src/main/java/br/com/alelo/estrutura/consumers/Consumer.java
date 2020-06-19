package br.com.alelo.estrutura.consumers;

import org.apache.activemq.Message;

import javax.jms.Session;

public interface Consumer {
    void consume(Message message, Session session) ;
}
