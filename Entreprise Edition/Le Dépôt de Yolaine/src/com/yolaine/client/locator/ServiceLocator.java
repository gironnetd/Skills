package com.yolaine.client.locator;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.yolaine.util.Constants;

/**
 * This class enables Swing client to look for resources (like home, remote interfaces...).
 * It follows the singleton pattern
 */
public class ServiceLocator {

    // ======================================
    // =             Attributes             =
    // ======================================
    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);

    private Context initalContext;
    private Map<String, Object> cache;

    // ======================================
    // =             Singleton              =
    // ======================================
    private static final ServiceLocator instance = new ServiceLocator();

    public static ServiceLocator getInstance() {
        return instance;
    }

    private ServiceLocator() throws ServiceLocatorException {
        try {
            initalContext = new InitialContext();
            cache = new HashMap<String, Object>();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * will get the ejb Local home factory. If this ejb home factory has already been
     * clients need to cast to the type of EJBHome they desire
     *
     * @param jndiName JNDI name of the EJB that we are looking for
     * @return the EJB Home corresponding to the homeName
     * @throws ServiceLocatorException thrown if lookup problems
     */
    public Object getRemoteInterface(String jndiName) throws ServiceLocatorException {
        String methodName = "getRemoteInterface";
        logger.entering(cname, methodName, jndiName);
        Object remoteInterface = getRemoteObject(jndiName);
        return remoteInterface;
    }

    /**
     * @param connFactoryName Name of the connection factory that we are looking for
     * @return the factory for the factory to get queue connections from
     * @throws ServiceLocatorException thrown if lookup problems
     */
    public ConnectionFactory getConnectionFactory(String connFactoryName) throws ServiceLocatorException {
        String methodName = "getConnectionFactory";
        logger.entering(cname, methodName, connFactoryName);
        ConnectionFactory factory = (ConnectionFactory) getRemoteObject(connFactoryName);
        return factory;
    }

    public Destination getDestination(String destinationName) {
        String methodName = "getDestination";
        logger.entering(cname, methodName, destinationName);
        Destination destination = (Destination) getRemoteObject(destinationName);
        return destination;
    }

    // ======================================
    // =            Private methods         =
    // ======================================
    private synchronized Object getRemoteObject(String jndiName) throws ServiceLocatorException {
        String methodName = "getObject";
        logger.entering(cname, methodName, jndiName);

        Object remoteObject = cache.get(jndiName);
        if (remoteObject == null) {
            try {
                remoteObject = initalContext.lookup(jndiName);
                cache.put(jndiName, remoteObject);
            } catch (Exception e) {
                throw new ServiceLocatorException(e);
            }
        }
        return remoteObject;
    }

}
