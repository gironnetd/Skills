package com.yolaine.client.ui.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.yolaine.util.Constants;
 
/**
 * The YapsJmsListener class.
 *
 * Cette classe implémente l'interface javax.jms.MessageListener et agrége un YapsTableModel.
 * Elle s'attend à recevoir un message de type javax.jms.ObjectMessage.
 * A chaque réception d'un message l'objet reçu est ajouté au model.
 *
 * @author Alexis Midon
 * @see javax.jms.MessageListener
 * @see javax.jms.ObjectMessage
 * @see YapsTableModel#add(Object) 
 */
public class YolaineMessageListener implements MessageListener {

    private Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);
    private final String cname = this.getClass().getName();

    private YolaineTableModel tableModel;


    public YolaineMessageListener() {
    }

    public YolaineMessageListener(YolaineTableModel tableModel) {
        this.tableModel = tableModel;
    }


    public YolaineTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(YolaineTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @SuppressWarnings({"unchecked"})
    public void onMessage(Message message) {
        String mname = "onMessage";
        if (message instanceof ObjectMessage) {
            ObjectMessage objMsg = (ObjectMessage) message;
            try {
                if (logger.isLoggable(Level.FINE)) {
                    Enumeration names = objMsg.getPropertyNames();
                    logger.fine("Message Properties are:");
                    while (names.hasMoreElements()) {
                        String propName = (String) names.nextElement();
                        logger.fine(propName + ":" + objMsg.getObjectProperty(propName).toString());
                    }
                }
                tableModel.add(objMsg.getObject());
            } catch (JMSException e) {
                logger.throwing(cname, mname, e);
            }
        } else {
            logger.warning("Message of unexpected type received!");
        }
    }
}
