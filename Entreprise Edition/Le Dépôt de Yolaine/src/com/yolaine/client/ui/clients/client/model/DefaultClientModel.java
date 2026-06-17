package com.yolaine.client.ui.clients.client.model;

import static com.yolaine.client.ui.clients.client.event.ClientEventPropertyName.*;

import java.util.Date;
import java.util.Set;
import com.yolaine.client.ui.commun.adresse.model.AdresseModel;
import com.yolaine.client.ui.commun.adresse.model.DefaultAdresseModel;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public class DefaultClientModel extends AbstractClientModel {
    
    private static final long serialVersionUID = 6117458605996580214L;
    

    private Client client;
    private Long identifierToFind;
    
    private AdresseModel addressModel;
    
    
    public DefaultClientModel() {        
    	Client client = new Client();
    	client.setCivilite(new Civilite());
        client.setAdresse(new Adresse());        
        client.setTypeidentite(new TypeIdentite());       
        setClient(client);
    }
    
    public DefaultClientModel(Client client) {
        setClient(client);
    }

    public Long getId() { return client.getId(); }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Le client ne doît pas être nulle");
        }
     
      
        
        Client oldValue = this.client;
        Client newValue = client;
        
        this.client = client;
        
        if (oldValue != null) {        
            
        	fireXSChanged(this, IDENTIFIANT_CHANGED, oldValue.getId(), newValue
                     .getId());
            fireXSChanged(this, CIVILITE_CHANGED, oldValue.getCivilite(), newValue
                    .getCivilite());
            fireXSChanged(this, NOM_CHANGED, oldValue.getNom(),
                    newValue.getNom());            
            fireXSChanged(this, PRENOM_CHANGED, oldValue.getPrenom(),
                    newValue.getPrenom());
            fireXSChanged(this, DEPOSANT_CHANGED, oldValue.isDeposante(), newValue
                    .isDeposante());
            fireXSChanged(this, LOGIN_CHANGED, oldValue.getLogin(),
                    newValue.getLogin());
            fireXSChanged(this, PASSWORD_CHANGED, oldValue.getPassword(),
                    newValue.getPassword());
            fireXSChanged(this, TELEPHONE_FIXE_CHANGED, oldValue.getTelephonefixe(),
                    newValue.getTelephonefixe());
            fireXSChanged(this, TELEPHONE_PORTABLE_CHANGED, oldValue.getTelephoneportable(),
                    newValue.getTelephoneportable());
            fireXSChanged(this, EMAIL_CHANGED, oldValue.getEmail(), newValue
                    .getEmail());
            fireXSChanged(this, TYPEIDENTITE_CHANGED, oldValue.getTypeidentite(), newValue
                    .getTypeidentite());
            fireXSChanged(this, NUMEROIDENTITE_CHANGED, oldValue.getNumeroidentite(), newValue
                    .getNumeroidentite());            
            fireXSChanged(this, DATE_DE_NAISSANCE_CHANGED, oldValue.getDatenaissance(), newValue
                    .getDatenaissance());    
            fireXSChanged(this, COMMENTAIRE_CHANGED, oldValue.getCommentaire(), newValue
                    .getCommentaire());
            fireXSChanged(this, CHAMP_NUMERIQUE_1_CHANGED, oldValue.getChampnumerique1(), newValue
                    .getChampnumerique1());
            fireXSChanged(this, CHAMP_NUMERIQUE_2_CHANGED, oldValue.getChampnumerique2(), newValue
                    .getChampnumerique2());            
        }
        
        if (addressModel == null) {
            addressModel = new DefaultAdresseModel(client.getAdresse());
        } else {
            addressModel.setAdresse(client.getAdresse());
        }
    }
    
    public Long getIdentifierToFind() {
        return identifierToFind;
    }
    
    public void setIdentifierToFind(Long identifierToFind) {
        Object oldValue = this.identifierToFind;
        Object newValue = identifierToFind;
        
        this.identifierToFind = identifierToFind;
        
        fireXSChanged(this, IDENTIFIANT_CHANGED, oldValue, newValue);
    }
    
    public Long getIdentifier() {
        return client.getId();
    }

	public Civilite getCivilite() {		
		return client.getCivilite();
	}
	
	public void setCivilite(Civilite civilite) {
		Object oldValue = client.getCivilite();
        Object newValue = civilite;
        
        client.setCivilite(civilite);
        
        fireXSChanged(this, CIVILITE_CHANGED, oldValue, newValue);		
	}
	
	public boolean isDeposante() {		
		return client.isDeposante();
	}
	
	public void setDeposante(boolean deposante) {
		Object oldValue = client.isDeposante();
        Object newValue = deposante;        
        client.setDeposante(deposante);
        
        fireXSChanged(this, DEPOSANT_CHANGED, oldValue, newValue);
	}	
    
	
    public String getNom() {
        return client.getNom();
    }
    
    public void setNom(String nom) {
        Object oldValue = client.getNom();
        Object newValue = nom;
        
        client.setNom(nom);
        
        fireXSChanged(this, NOM_CHANGED, oldValue, newValue);
    }
    
    public String getPrenom() {
        return client.getPrenom();
    }
    
    public void setPrenom(String prenom) {
        Object oldValue = client.getPrenom();
        Object newValue = prenom;
        
        client.setPrenom(prenom);
        
        fireXSChanged(this, PRENOM_CHANGED, oldValue, newValue);
    }
    
    
    public String getLogin() {
        return client.getLogin();
    }
    
    public void setLogin(String login) {
        Object oldValue = client.getLogin();
        Object newValue = login;
        
        client.setLogin(login);
        
        fireXSChanged(this, LOGIN_CHANGED, oldValue, newValue);
    }
    
    public String getPassword() {
        return client.getPassword();
    }
    
    public void setPassword(String password) {
        Object oldValue = client.getPassword();
        Object newValue = password;
        
        client.setPassword(password);
        
        fireXSChanged(this, PASSWORD_CHANGED, oldValue, newValue);
    }
    
    
    public String getTelephonefixe() {
        return client.getTelephonefixe();
    }
    
    public void setTelephonefixe(String telephonefixe) {
        Object oldValue = client.getTelephonefixe();
        Object newValue = telephonefixe;
        
        client.setTelephonefixe(telephonefixe);
        
        fireXSChanged(this, TELEPHONE_FIXE_CHANGED, oldValue, newValue);
    }
    
    public String getTelephoneportable() {
        return client.getTelephoneportable();
    }
    
    public void setTelephoneportable(String telephoneportable) {
        Object oldValue = client.getTelephoneportable();
        Object newValue = telephoneportable;
        
        client.setTelephoneportable(telephoneportable);
        
        fireXSChanged(this, TELEPHONE_PORTABLE_CHANGED, oldValue, newValue);
    }
    
    public String getEmail() {
        return client.getEmail();
    }
    
    public void setEmail(String email) {
        Object oldValue = client.getEmail();
        Object newValue = email;
        
        client.setEmail(email);
        
        fireXSChanged(this, EMAIL_CHANGED, oldValue, newValue);
    }
    
    public TypeIdentite getTypeidentite() {		
		return client.getTypeidentite();
	}
	
	public void setTypeidentite(TypeIdentite typeidentite) {
		Object oldValue = client.getTypeidentite();
        Object newValue = typeidentite;
        
        client.setTypeidentite(typeidentite);
        
        fireXSChanged(this, TYPEIDENTITE_CHANGED, oldValue, newValue);
	}
	
	public String getNumeroidentite() {		
		return client.getNumeroidentite();
	}
	
	public void setNumeroidentite(String numeroidentite) {
		Object oldValue = client.getNumeroidentite();
        Object newValue = numeroidentite;
        
        client.setNumeroidentite(numeroidentite);
        
        fireXSChanged(this, NUMEROIDENTITE_CHANGED, oldValue, newValue);
	}
    
    public String getDatenaissance() {
        return client.getDatenaissance();
    }
    
    public void setDatenaissance(String datenaissance) {
        Object oldValue = client.getDatenaissance();
        Object newValue = datenaissance;
        
        client.setDatenaissance(datenaissance);
        
        fireXSChanged(this, DATE_DE_NAISSANCE_CHANGED, oldValue, newValue);               
    }       
    
    public String getCommentaire() {
        return client.getCommentaire();
    }
    
    public void setCommentaire(String commentaire) {
        Object oldValue = client.getCommentaire();
        Object newValue = commentaire;
        
        client.setCommentaire(commentaire);
        
        fireXSChanged(this, COMMENTAIRE_CHANGED, oldValue, newValue);
    }
    
    public String getChampnumerique1() {
        return client.getChampnumerique1();
    }
    
    public void setChampnumerique1(String champnumerique1) {
        Object oldValue = client.getChampnumerique1();
        Object newValue = champnumerique1;
        
        client.setChampnumerique1(champnumerique1);
        
        fireXSChanged(this, CHAMP_NUMERIQUE_1_CHANGED, oldValue, newValue);
    }    

    public String getChampnumerique2() {
        return client.getChampnumerique2();
    }
    
    public void setChampnumerique2(String champnumerique2) {
        Object oldValue = client.getChampnumerique2();
        Object newValue = champnumerique2;
        
        client.setChampnumerique2(champnumerique2);
        
        fireXSChanged(this, CHAMP_NUMERIQUE_2_CHANGED, oldValue, newValue);
    }  
    
    public AdresseModel getAddressModel() {
        return addressModel;
    }    
    
    public void reset() {    	
    	setDeposante(false);
    	setCivilite(null);
    	setNom(null);
        setPrenom(null);
        addressModel.reset();
        setTelephonefixe(null);
        setTelephoneportable(null);
        setEmail(null);
    	setTypeidentite(null);
    	setNumeroidentite(null);        
        setDatenaissance(null);        
        setCommentaire(null);
        setChampnumerique1(null);
        setChampnumerique2(null); 
        setLogin(null);
        setPassword(null);
    }	
}