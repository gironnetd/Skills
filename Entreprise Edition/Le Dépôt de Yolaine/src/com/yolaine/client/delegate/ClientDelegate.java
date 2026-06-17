package com.yolaine.client.delegate;

import com.yolaine.client.locator.ServiceLocator;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;
import com.yolaine.stateless.client.ClientRemote;

import java.util.List;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CustomerBean class. Each method delegates the call to the
 * CustomerBean class
 */
public final class ClientDelegate {

    // ======================================
    // =         Client  Business methods         =
    // ======================================

    public static Client createClient(Client client,Civilite civilite, Adresse adresse,TypeIdentite typeidentite) {
        return getClientRemote().creerClient(client,civilite, adresse, typeidentite);
    }   
    
    public static Client trouverClient(String nom,String prenom) {
        return getClientRemote().trouverClient(nom, prenom);
    }

    public static void supprimerClient(Client client) {
        getClientRemote().supprimerClient(client);
    }

    public static Client majClient(Client client,Civilite civilite, Adresse adresse,TypeIdentite typeidentite) {
        return getClientRemote().majClient(client,civilite, adresse, typeidentite);
    }

    public static List<Client> trouverTousclients() {
        return getClientRemote().trouverTousclients();
    }
    
    public static List<Client> trouverClients(boolean deposant) {
        return getClientRemote().trouverClients(deposant);
    }

    // ======================================
    // =        TypeIdentite   Business methods         =
    // ======================================

    public static TypeIdentite creerTypeIdentite(TypeIdentite typeidentite) {
        return getClientRemote().creerTypeIdentite(typeidentite);
    }

    public static TypeIdentite trouverTypeIdentite(Long typeidentiteId) {
        return getClientRemote().trouverTypeIdentite(typeidentiteId);
    }

    public static void supprimerTypeIdentite(TypeIdentite typeidentite) {
        getClientRemote().supprimerTypeIdentite(typeidentite);
    }

    public static TypeIdentite majTypeIdentite(TypeIdentite typeidentite) {
        return getClientRemote().majTypeIdentite(typeidentite);
    }

    public static List<TypeIdentite> trouverTypeIdentites() {
        return getClientRemote().trouverTypeIdentites();
    }

    // ======================================
    // =        Civilite   Business methods         =
    // ======================================

    public static Civilite creerCivilite(Civilite civilite) {
        return getClientRemote().creerCivilite(civilite);
    }

    public static Civilite trouverCivilite(Long civiliteId) {
        return getClientRemote().trouverCivilite(civiliteId);
    }

    public static void supprimerCivilite(Civilite civilite) {
        getClientRemote().supprimerCivilite(civilite);
    }

    public static Civilite majCivilite(Civilite civilite) {
        return getClientRemote().majCivilite(civilite);
    }

    public static List<Civilite> trouverCivilites() {
        return getClientRemote().trouverCivilites();
    }

 // ======================================
    // =        Civilite   Business methods         =
    // ======================================

    public static Depot creerDepot(Depot depot) {
        return getClientRemote().creerDepot(depot);
    }

    public static Depot trouverDepot(Long depotId) {
        return getClientRemote().trouverDepot(depotId);
    }

    public static void supprimerDepot(Long depotId) {
        getClientRemote().supprimerDepot(depotId);
    }

    public static Depot majDepot(Depot depot) {
        return getClientRemote().majDepot(depot);
    }

    public static List<Depot> trouverTousDepots() {
        return getClientRemote().trouverTousDepots();
    }

    public static List<Depot> trouverDepots(Long clientId) {
        return getClientRemote().trouverDepots(clientId);
    }
    
    // ======================================
    // =            Private methods         =
    // ======================================
    private static ClientRemote getClientRemote() {
        ClientRemote clientRemote;
        clientRemote = (ClientRemote) ServiceLocator.getInstance().getRemoteInterface("ejb/stateless/Client");
        return clientRemote;
    }
}
