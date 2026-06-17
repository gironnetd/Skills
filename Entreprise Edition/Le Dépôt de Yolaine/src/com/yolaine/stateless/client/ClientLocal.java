package com.yolaine.stateless.client;

import java.util.List;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;

import javax.ejb.Local;

/**
 * @author Antonio Goncalves
 */
@Local
public interface ClientLocal {

    // ======================================
    // =          Methodes publiques        =
    // ======================================
   

    Client creerClient(Client client,Civilite civilite, Adresse Adresse, TypeIdentite typeidentite);

    Client trouverClient(String nom, String prenom);

    Client majClient(Client client,Civilite civilite, Adresse Adresse, TypeIdentite typeidentite);

    TypeIdentite creerTypeIdentite(TypeIdentite typeidentite) ;

    TypeIdentite trouverTypeIdentite(Long typeidentiteId) ;

    TypeIdentite majTypeIdentite(TypeIdentite typeidentite) ;    
    
    Civilite creerCivilite(Civilite civilite) ;

    Civilite trouverCivilite(Long civiliteId) ;

    Civilite majCivilite(Civilite civilite) ;
    
}