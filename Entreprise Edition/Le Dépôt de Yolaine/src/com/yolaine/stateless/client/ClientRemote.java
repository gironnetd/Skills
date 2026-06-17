package com.yolaine.stateless.client;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;

import javax.ejb.Remote;
import java.util.List;

/**
 * @author Antonio Goncalves
 */
@Remote
public interface ClientRemote {

    // ======================================
    // =             Méthodes publiques             =
    // ======================================
    Client creerClient(Client client,Civilite civilite, Adresse Adresse, TypeIdentite typeidentite) ;

    Client trouverClient(String nom, String prenom) ;

    void supprimerClient(Client client) ;

    Client majClient(Client client,Civilite civilite, Adresse Adresse, TypeIdentite typeidentite) ;

    List<Client> trouverClients(boolean deposant) ; 
    
    List<Client> trouverTousclients();
    
    TypeIdentite creerTypeIdentite(TypeIdentite typeidentite) ;

    TypeIdentite trouverTypeIdentite(Long typeidentiteId) ;

    void supprimerTypeIdentite(TypeIdentite typeidentite) ;

    TypeIdentite majTypeIdentite(TypeIdentite typeidentite) ;

    List<TypeIdentite> trouverTypeIdentites() ;
    
    Civilite creerCivilite(Civilite civilite) ;

    Civilite trouverCivilite(Long civiliteId) ;

    void supprimerCivilite(Civilite civilite) ;

    Civilite majCivilite(Civilite civilite) ;

    List<Civilite> trouverCivilites() ;
    
    Depot creerDepot(Depot depot) ;

    Depot trouverDepot(Long depotId) ;

    void supprimerDepot(Long depotId) ;

    Depot majDepot(Depot depot) ;

    List<Depot> trouverDepots(Long clientId) ;
    
    List<Depot> trouverTousDepots();
}