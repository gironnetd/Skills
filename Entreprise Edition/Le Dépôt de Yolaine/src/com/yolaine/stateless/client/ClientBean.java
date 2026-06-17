package com.yolaine.stateless.client;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;

import com.yolaine.entity.client.TypeIdentite;
import com.yolaine.exception.ValidationException;
import com.yolaine.util.Constants;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class is a facade for all customer services.
 *
 * @author Antonio Goncalves
 */
@SuppressWarnings(value = "unchecked")
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Stateless(name = "CustomerSB", mappedName = "ejb/stateless/Client")
public class ClientBean implements ClientRemote, ClientLocal {

    // ======================================
    // =             Attributs              =
    // ======================================
    @PersistenceContext(unitName ="YolainePU")
    private EntityManager em;

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_STATELESS);

    // ========================================
    // =  Methodes publiques pour client      =
    // ========================================
  

    public Client creerClient(final Client client,final Civilite civilite, final Adresse adresse,final TypeIdentite typidentite) {
        final String mname = "creerClient";
        logger.entering(cname, mname, client);

        // On s'assure de la validit� des param�tres
        if (client == null)
            throw new ValidationException("L'objet client est nulle");
        
        client.setCivilite(civilite);
        client.setAdresse(adresse);
        client.setTypeidentite(typidentite);
        //INSERT INTO 'yolainedb'.'client' ('id', 'champnumerique1', 'champnumerique2', 'commentaire', 'datenaissance', 'deposante', 'email', 'login', 'montantdepose', 'montantdu', 'nom', 'numeroidentite', 'password', 'prenom', 'telephonefixe', 'telephoneportable', 'civilite', 'typeidentite', 'adresse_id') VALUES (NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
        //String query = "INSERT INTO 'yolainedb'.'client' ('id', 'champnumerique1', 'champnumerique2'," +
        //		" 'commentaire', 'datenaissance', 'deposante', 'email', 'login', 'montantdepose', 'montantdu', " +
        //		"'nom', 'numeroidentite', 'password', 'prenom', 'telephonefixe', 'telephoneportable', 'civilite'," +
        //		" 'typeidentite', 'adresse_id') VALUES ('" + client.getId() + "','" + client.getChampnumerique1() + "','" + client.getChampnumerique2() + "','" + client.getCommentaire() + "','" + client.getDatenaissance() + "'," +
        //		"" + client.isDeposante() + ",'" + client.getEmail() + "','" + client.getLogin() + "','" + client.getMontantdepose() + "','" + client.getMontantdu() + "','" + client.getNom() + "'," +
        //				"'" + client.getNumeroidentite() + "','" + client.getPassword() + "','" + client.getPrenom() + "','" + client.getTelephonefixe() + "','" + client.getTelephoneportable() + "','"
        //				+ client.getCivilite().getCivilite() + "','" + client.getTypeidentite().getTypeidentite() + "','" + client.getAdresse().getId() + "')";
        // em.createNativeQuery(query).executeUpdate();
        em.persist(client);
        logger.exiting(cname, mname, client);
        return client;
    }

    public Client trouverClient(final String nom , final String prenom) {
        final String mname = "findCustomer";
       // logger.entering(cname, mname, client);

        // On s'assure de la validit� des param�tres
        //if (client == null)
          //  throw new ValidationException("Invalid id");
        Query sql = em.createQuery
        ("SELECT c.id from Client c where c.nom='" + nom + "' and c.prenom='" + prenom + "' ");
        List<Long> clientIds = (List<Long>) sql.getResultList();
        if (clientIds.size()== 0){
        	throw new ValidationException("Ce client n'existe pas.");
        }
        Long id = clientIds.get(0);
        System.out.println("id = " + id);
        // On recherche l'objet � partir de son identifiant
       
        Client client ; 
        client = em.find(Client.class, id);
        System.out.println(" nom = " + client.getNom() + " prenom = " + client.getPrenom());
        System.out.println(" civilite = " + client.getCivilite().getCivilite());
        System.out.println(" type identite = " + client.getTypeidentite().getTypeIdentite());
        logger.exiting(cname, mname, client);
        return client;
    }

    public void supprimerClient(final Client client) {
        final String mname = "deleteCustomer";
        logger.entering(cname, mname, client);

        // On s'assure de la validit� des param�tres
        if (client == null)
            throw new ValidationException("Client object is null");

        // On supprime l'objet de la base de donn�es
        em.remove(em.merge(client));

        logger.exiting(cname, mname);
    }

    public Client majClient(final Client client,final Civilite civilite, final Adresse adresse, TypeIdentite typidentite) {
        final String mname = "updateCustomer";
        logger.entering(cname, mname, client);

        // On s'assure de la validit� des param�tres
        if (client == null)
            throw new ValidationException("Customer object is null");
        
        client.setCivilite(civilite);
        client.setAdresse(adresse);
        client.setTypeidentite(typidentite);

        // On modifie l'objet de la base de donn�es
        em.merge(client);

        logger.exiting(cname, mname, client);
        return client;
    }

    public List<Client> trouverTousclients() {
        final String mname = "findCustomers";
        logger.entering(cname, mname);

        Query query;
        List<Client> clients;

        // On modifie l'objet de la base de donn�es
        query = em.createQuery("SELECT c FROM Client ORDER BY c.id" );
        clients = query.getResultList();

        logger.exiting(cname, mname, clients.size());
        return clients;
    }
    
    public List<Client> trouverClients(boolean deposant) {
        final String mname = "findCustomers";
        logger.entering(cname, mname);

        System.out.println(deposant);

        Query query;
        List<Client> clients;

        // On modifie l'objet de la base de donn�es
        query = em.createQuery("SELECT c FROM Client c WHERE c.deposante="+ deposant +" ORDER BY c.id" );
        clients = query.getResultList();

        logger.exiting(cname, mname, clients.size());
        return clients;
    }
    
    // ===========================================
    // =  Methodes publiques pour type identite  =
    // ===========================================  

    public TypeIdentite creerTypeIdentite(final TypeIdentite typeidentite) {
        final String mname = "creerTypeIdentite";
        logger.entering(cname, mname, typeidentite);

        // On s'assure de la validit� des param�tres
        if (typeidentite == null)
            throw new ValidationException("L'objet TypeIdentite est nulle");

        em.persist(typeidentite);       

        logger.exiting(cname, mname, typeidentite);
        return typeidentite;
    }

    public TypeIdentite trouverTypeIdentite(final Long typeidentiteId) {
        final String mname = "trouverTypeIdentite";
        logger.entering(cname, mname, typeidentiteId);

        // On s'assure de la validit� des param�tres
        if (typeidentiteId == null)
            throw new ValidationException("Identifiant invalide ");

        TypeIdentite typeidentite;

        // On recherche l'objet � partir de son identifiant
        typeidentite = em.find(TypeIdentite.class, typeidentiteId);

        logger.exiting(cname, mname, typeidentite);
        return typeidentite;
    }

    public void supprimerTypeIdentite(final TypeIdentite typeidentite) {
        final String mname = "supprimerTypeIdentite";
        logger.entering(cname, mname, typeidentite);

        // On s'assure de la validit� des param�tres
        if (typeidentite == null)
            throw new ValidationException("L'objet TypeIdentite est nulle");

        // On supprime l'objet de la base de donn�es
        em.remove(em.merge(typeidentite));

        logger.exiting(cname, mname);
    }

    public TypeIdentite majTypeIdentite(final TypeIdentite typeidentite) {
        final String mname = "mettre_�_jour_TypeIdentite";
        logger.entering(cname, mname, typeidentite);

        // On s'assure de la validit� des param�tres
        if (typeidentite == null)
            throw new ValidationException("l'objet TypeIdentite n'existe pas");        

        // On modifie l'objet de la base de donn�es
        em.merge(typeidentite);

        logger.exiting(cname, mname, typeidentite);
        return typeidentite;
    }

    public List<TypeIdentite> trouverTypeIdentites() {
        final String mname = "trouverTypeIdentites";
        logger.entering(cname, mname);

        Query query;
        List<TypeIdentite> typeidentites;

        // On modifie l'objet de la base de donn�es
        query = em.createQuery("SELECT ti FROM TypeIdentite ti ORDER BY ti.id");
        typeidentites = query.getResultList();

        logger.exiting(cname, mname, typeidentites.size());
        return typeidentites;
    }

    // ===========================================
    // =  Methodes publiques pour civilite  =
    // ===========================================  

    public Civilite creerCivilite(final Civilite civilite) {
        final String mname = "creerTypeIdentite";
        logger.entering(cname, mname, civilite);

        // On s'assure de la validit� des param�tres
        if (civilite == null)
            throw new ValidationException("L'objet TypeIdentite est nulle");

        em.persist(civilite);       

        logger.exiting(cname, mname, civilite);
        return civilite;
    }

    public Civilite trouverCivilite(final Long civiliteId) {
        final String mname = "trouverTypeIdentite";
        logger.entering(cname, mname, civiliteId);

        // On s'assure de la validit� des param�tres
        if (civiliteId == null)
            throw new ValidationException("Identifiant invalide ");

        Civilite civilite;

        // On recherche l'objet � partir de son identifiant
        civilite = em.find(Civilite.class, civiliteId);

        logger.exiting(cname, mname, civilite);
        return civilite;
    }

    public void supprimerCivilite(final Civilite civilite) {
        final String mname = "supprimerTypeIdentite";
        logger.entering(cname, mname,civilite);

        // On s'assure de la validit� des param�tres
        if (civilite == null)
            throw new ValidationException("L'objet TypeIdentite est nulle");

        // On supprime l'objet de la base de donn�es
        em.remove(em.merge(civilite));

        logger.exiting(cname, mname);
    }

    public Civilite majCivilite(final Civilite civilite) {
        final String mname = "mettre_�_jour_TypeIdentite";
        logger.entering(cname, mname, civilite);

        // On s'assure de la validit� des param�tres
        if (civilite == null)
            throw new ValidationException("l'objet TypeIdentite n'existe pas");        

        // On modifie l'objet de la base de donn�es
        em.merge(civilite);

        logger.exiting(cname, mname, civilite);
        return civilite;
    }

    public List<Civilite> trouverCivilites() {
        final String mname = "trouverCivilites";
        logger.entering(cname, mname);

        Query query;
        List<Civilite> civilites;

        // On modifie l'objet de la base de donn�es
        query = em.createQuery("SELECT c FROM Civilite c ORDER BY c.id");
        civilites = query.getResultList();

        logger.exiting(cname, mname, civilites.size());
        return civilites;
    }
    
    // ===========================================
    // =  Methodes publiques pour depot  =
    // ===========================================  

    public Depot creerDepot(Depot depot) {
        final String mname = "creerTypeIdentite";
        logger.entering(cname, mname, depot);

        // On s'assure de la validit� des param�tres
        if (depot == null)
            throw new ValidationException("L'objet d�p�t est nulle");
        if (depot.getArticles() == null){
        	List<Article> articles = new ArrayList<Article>();
        	depot.setArticles(articles);
        }
        depot = em.merge(depot);
        em.persist(depot);       

        logger.exiting(cname, mname, depot);
        return depot;
    }

    public Depot trouverDepot(final Long depotId) {
        final String mname = "trouverTypeIdentite";
        logger.entering(cname, mname, depotId);

        // On s'assure de la validit� des param�tres
        if (depotId == null)
            throw new ValidationException("Identifiant invalide ");

        Depot depot;

        // On recherche l'objet � partir de son identifiant
        depot = em.find(Depot.class, depotId);

        logger.exiting(cname, mname, depot);
        return depot;
    }

    public void supprimerDepot(final Long depotId) {
        final String mname = "supprimerTypeIdentite";
        logger.entering(cname, mname,depotId);
        //String s = String.valueOf(depotId);
        //Long id = Long.valueOf(s);
        // On s'assure de la validit� des param�tres
        if (depotId == null)
            throw new ValidationException("L'objet Depot est nulle");
        Depot depot = new Depot();
        depot = em.find(Depot.class, depotId);
       
        em.remove(depot);        
        
        // On supprime l'objet de la base de donn�es        

        logger.exiting(cname, mname);
    }

    public Depot majDepot(final Depot depot) {
        final String mname = "mettre_�_jour_TypeIdentite";
        logger.entering(cname, mname, depot);

        // On s'assure de la validit� des param�tres
        if (depot == null)
            throw new ValidationException("l'objet Depot n'existe pas");        

        // On modifie l'objet de la base de donn�es
        em.merge(depot);

        logger.exiting(cname, mname, depot);
        return depot;
    }

    public List<Depot> trouverDepots(final Long clientId) {
        final String mname = "trouverCivilites";
        logger.entering(cname, mname);

        Query query;
        List<Depot> depots;

        // On modifie l'objet de la base de donn�es
        query = em.createQuery("SELECT d FROM Depot d WHERE d.client.id=" + clientId + " ORDER BY d.id");
        depots = query.getResultList();

        logger.exiting(cname, mname, depots.size());
        return depots;
    }
    
    public List<Depot> trouverTousDepots() {
        final String mname = "trouverDepots";
        logger.entering(cname, mname);

        Query query;
        List<Depot> depots;

        // On modifie l'objet de la base de donn�es
        query = em.createQuery("SELECT d FROM Depot d ORDER BY d.id");
        depots = query.getResultList();

        logger.exiting(cname, mname, depots.size());
        return depots;
    }
    
    // ======================================
    // =           Methodes Priv�es         =
    // ======================================
}
