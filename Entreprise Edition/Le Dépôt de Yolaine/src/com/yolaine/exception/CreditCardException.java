package com.yolaine.exception;

import javax.ejb.ApplicationException;

/**
 * Cette exception est lancée lorsque la carte bancaire est invalide
 *
 * @author Antonio Goncalves
 */
@ApplicationException(rollback = true)
public class CreditCardException extends RuntimeException {

    // ======================================
    // =            Constructeurs           =
    // ======================================

    public CreditCardException(String message) {
        super(message);
    }
}