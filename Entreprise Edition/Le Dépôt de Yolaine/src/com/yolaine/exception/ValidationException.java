package com.yolaine.exception;

import javax.ejb.ApplicationException;

/**
 * Cette exception est lancée lorsque la validation d'une donnée ne peut se faire
 *
 * @author Antonio Goncalves
 */
@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException {

    // ======================================
    // =            Constructeurs           =
    // ======================================

    public ValidationException(String message) {
        super(message);
    }
}