package com.yolaine.client.ui.util;


import static com.yolaine.util.ExceptionUtils.*;


import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.yolaine.util.Constants;


public class YolaineInternalFrame extends JInternalFrame {
    
    private static final long serialVersionUID = -7476477854043392884L;
    
    
    // ======================================
    // = Constructeurs =
    // ======================================
    public YolaineInternalFrame() {
        super("", true, true, true, true);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    
    protected void setInnerPane(JPanel innerPane) {
        getContentPane().add(innerPane);
    }
    
    
    // ======================================
    // = Attributs =
    // ======================================
    protected final transient String className = getClass().getName();
    protected Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);
    
    
    // ======================================
    // = Methodes Protégées =
    // ======================================
    protected void displayException(String sourceClass, String sourceMethod,
            Throwable throwable) {
        Throwable cause = getRootCause(throwable);
        if (isApplicationException(cause)) {
            displayWarning(cause.getMessage());
        } else {
            displayError(throwable.getMessage());
            logger.throwing(sourceClass, sourceMethod, throwable);
        }
    }
    
    protected void displayWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Attention",
                JOptionPane.WARNING_MESSAGE);
    }
    
    protected void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur",
                JOptionPane.ERROR_MESSAGE);
    }
    
}