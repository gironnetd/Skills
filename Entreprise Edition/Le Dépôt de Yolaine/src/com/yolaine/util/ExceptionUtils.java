package com.yolaine.util;

import com.yolaine.exception.CreditCardException;
import com.yolaine.exception.ValidationException;

import javax.ejb.EJBException;

/**
 * @author Antonio Goncalves
 */
public class ExceptionUtils {

    // ======================================
    // =             Attributs              = 
    // ======================================

    // ======================================
    // =             Constantes             =
    // ======================================

    // ======================================
    // =            Constructeurs           =
    // ======================================

    // ======================================
    // =          Methodes publiques        = 
    // ======================================

    public static Throwable getRootCause(Throwable throwable) {
        Throwable cause;

        if (throwable instanceof EJBException) {
            cause = ((EJBException)throwable).getCausedByException();
        } else {
            cause = throwable.getCause();
        }

        if (cause != null) {
            throwable = cause;
            while ((throwable = throwable.getCause()) != null) {
                cause = throwable;
            }
        }
        return cause;
    }

    public static boolean isApplicationException(Throwable throwable) {
        if (throwable instanceof ValidationException) {
            return true;
        } else if (throwable instanceof CreditCardException) {
            return true;
        } else {
            return false;
        }
    }

    // ======================================
    // =          Methodes Protégées        = 
    // ======================================

    // ======================================
    // =             Accesseurs             = 
    // ======================================

    // ======================================
    // =           Methodes Privées         =
    // ======================================

    // ======================================
    // =   Methodes hash, equals, toString  =
    // ======================================
}