package com.yolaine.client.ui.clients.deposant.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.clients.client.event.ClientEventPropertyName;
import com.yolaine.client.ui.clients.client.event.ClientListener;
import com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName;
import com.yolaine.client.ui.clients.deposant.event.DeposantListener;
import com.yolaine.client.ui.commun.adresse.model.AdresseModel;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public interface DeposantModel extends
        XSModel<DeposantListener, DeposantEventPropertyName> {

	public Long getId();

    public Client getClient();
    
    public void setClient(Client client);    
	
    public Long getIdentifierToFind();
    
    public void setIdentifierToFind(Long identifier);
    
    public Long getIdentifier();
    
	public boolean isDeposante();

	public void setDeposante(boolean deposante);	
	
	public Civilite getCivilite();
	
	public void setCivilite(Civilite civilite);

	public String getNom();

	public void setNom(String nom);

	public String getPrenom();

	public void setPrenom(String prenom);
	
	public Client getNomCombo();

	public void setNomCombo(Client nomCombo);

	public Client getPrenomCombo();

	public void setPrenomCombo(Client prenomCombo);	
	
	public String getLogin();

	public void setLogin(String login);

	public String getPassword();
	
	public void setPassword(String password);
	
	public String getTelephonefixe();

	public void setTelephonefixe(String telephonefixe);

	public String getTelephoneportable();

	public void setTelephoneportable(String telephoneportable);
	
	public String getEmail();

	public void setEmail(String email);
	
	public TypeIdentite getTypeidentite();

	public void setTypeidentite(TypeIdentite typeidentite);

	public String getNumeroidentite();

	public void setNumeroidentite(String numeroidentite);
	
	public String getDatenaissance();

	public void setDatenaissance(String datenaissance);
	
	public String getCommentaire();

	public void setCommentaire(String commentaire);

	public String getChampnumerique1();

	public void setChampnumerique1(String champnumerique1);

	public String getChampnumerique2();

	public void setChampnumerique2(String champnumerique2);
	
	public String getMontantdepose();

	public void setMontantdepose(String montantdepose);
	
	public void resetMontantdepose();

	public String getMontantdu();

	public void setMontantdu(String montantdu);	
	
    public AdresseModel getAddressModel();    
}