package com.yolaine.client.util.jms;

import javax.jms.*;
import java.util.logging.Logger;
import com.yolaine.client.locator.ServiceLocator;
import com.yolaine.util.Constants;

/**
 * The JmsListenerBootstrap class.
 * <p/>
 * Cette classe permet de masquer toutes les étapes nécessaires à l'ajout d'un listener sur une queue ou un topic JMS.
 *
 * @author Alexis Midon
 */
public class JmsListenerBootstrap {

    private Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);
    private final String cname = this.getClass().getName();

    private String conFactoryName;
    private String destinationName;
    private String messageSelector;
    private MessageListener messageListener;

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    /**
     * @param conFactoryName,  le nom jndi de la javax.jms.ConnectionFactory - obligatoire
     * @param destinationName, le nom jndi de la javax.jms.Destination - obligatoire
     * @param messageSelector, un message selector - optionel
     * @param messageListener, le listener qui sera notifié - obligatoire
     */
    public JmsListenerBootstrap(String conFactoryName, String destinationName, String messageSelector, MessageListener messageListener) {
        this.conFactoryName = conFactoryName;
        this.destinationName = destinationName;
        this.messageSelector = messageSelector;
        this.messageListener = messageListener;
    }

    public JmsListenerBootstrap(String conFactoryName, String destinationName, MessageListener messageListener) {
        this(conFactoryName, destinationName, null, messageListener);
    }

    /**
     * Crée toutes les ressources JMS nécessaires à la réception des messages
     */
    public void initJMS() throws JMSException {
        ConnectionFactory connectionFactory = ServiceLocator.getInstance().getConnectionFactory(conFactoryName);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = ServiceLocator.getInstance().getDestination(destinationName);
        consumer = session.createConsumer(destination, messageSelector);
        consumer.setMessageListener(messageListener);
    }

    /**
     * Libère toutes les ressources JMS utilisées.
     */
    public void shutdownJMS() {
        String mname = "shutdownJMS";
        try {
            stopListening();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
        try {
            consumer.close();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
        try {
            session.close();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
        try {
            connection.close();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
    }

    /**
     * Démarre la réception des messages.
     *
     * @throws JMSException
     * @see javax.jms.Connection#start()
     */
    public void startListening() throws JMSException {
        connection.start();
    }

    /**
     * Arrête la réception des messages.
     *
     * @throws JMSException
     * @see javax.jms.Connection#stop()
     */
    public void stopListening() throws JMSException {
        connection.stop();
    }

}