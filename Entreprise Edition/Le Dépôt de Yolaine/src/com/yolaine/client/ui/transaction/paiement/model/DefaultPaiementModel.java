package com.yolaine.client.ui.transaction.paiement.model;

import static com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName.DEPOT_CHANGED;
import static com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName.*;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.TypeIdentite;

public class DefaultPaiementModel extends AbstractPaiementModel {
    
    private static final long serialVersionUID = 1602655452741754011L;
    

    private Paiement paiement;
	
    
    public DefaultPaiementModel() {
    	this(new Paiement());
    	//Paiement paiement = new Paiement();
    	//TypePaiement typePaiement = new TypePaiement();
    	//Banque banque = new Banque();
    	//TypeIdentite typeIdentite = new TypeIdentite();
    	//paiement.setTypepaiement(typePaiement);
    	//paiement.setBanquePaiement(banque);
    	//paiement.setTypeidentite(typeIdentite);
    	//setPaiement(paiement);
    }
    
    public DefaultPaiementModel(Paiement paiement) {      	
		setPaiement(paiement);
    }   



    public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {

        if (paiement == null) {
            throw new IllegalArgumentException("La banque ne doît pas être nul.");
        }
        
        Paiement oldValue = this.paiement;
        Paiement newValue = paiement;

		this.paiement = paiement;
        
        if (oldValue != null) {           
        	fireXSChanged(this, DATEVENTE_CHANGED, oldValue.getDatevente(), newValue
					.getDatevente());
        	fireXSChanged(this, TYPEPAIEMENT_CHANGED, oldValue.getTypepaiement(), newValue
					.getTypepaiement());        	
			fireXSChanged(this, BANQUEPAIEMENT_CHANGED, oldValue.getBanquePaiement(),
					newValue.getBanquePaiement());
			fireXSChanged(this, BANQUETYPEIDENTITE_CHANGED, oldValue.getTypeidentite(),
					newValue.getTypeidentite());
			fireXSChanged(this, NUMEROBANQUE_CHANGED, oldValue.getNumeroBanque(),
					newValue.getNumeroBanque());
			fireXSChanged(this, NUMEROCHEQUEPAIEMENT_CHANGED, oldValue.getNumeroChequePaiement(),
					newValue.getNumeroChequePaiement());
            
        }
    
	}

	public Long getId() { return paiement.getId(); }

	public TypeIdentite getTypeidentite() {
		return paiement.getTypeidentite();
	}

	public void setTypeidentite(TypeIdentite typeidentite) {
		Object oldValue = paiement.getTypeidentite();
		Object newValue = typeidentite;

		paiement.setTypeidentite(typeidentite);

		fireXSChanged(this, BANQUETYPEIDENTITE_CHANGED, oldValue, newValue);
	}
    
	public String getNumeroChequePaiement() {
		return paiement.getNumeroChequePaiement();
	}
	
    public void setNumeroChequePaiement(String numeroChequePaiement) {
		Object oldValue = paiement.getNumeroChequePaiement();
		Object newValue = numeroChequePaiement;

		paiement.setNumeroChequePaiement(numeroChequePaiement);

		fireXSChanged(this, NUMEROCHEQUEPAIEMENT_CHANGED, oldValue, newValue);
	}
	

	public String getNumeroBanque() {
		return paiement.getNumeroBanque();
	}

	public void setNumeroBanque(String numeroBanque) {
		Object oldValue = paiement.getNumeroBanque();
		Object newValue = numeroBanque;

		paiement.setNumeroBanque(numeroBanque);

		fireXSChanged(this, NUMEROBANQUE_CHANGED, oldValue, newValue);
	}	
	
	public Banque getBanquePaiement() {		
		return paiement.getBanquePaiement();
	}
	
	public void setBanquePaiement(Banque banquePaiement) {	
		Object oldValue = paiement.getBanquePaiement();
		Object newValue = banquePaiement;

		paiement.setBanquePaiement(banquePaiement);

		fireXSChanged(this, BANQUEPAIEMENT_CHANGED, oldValue, newValue);
	}
	
	public TypePaiement getTypePaiement() {
		return paiement.getTypepaiement();
	}
	
	public void setTypePaiement(TypePaiement typepaiement) {	
		Object oldValue = paiement.getTypepaiement();
		Object newValue = typepaiement;

		paiement.setTypepaiement(typepaiement);

		fireXSChanged(this, TYPEPAIEMENT_CHANGED, oldValue, newValue);		
	}
	
	public String getDatevente() {
		return paiement.getDatevente();
	}


	public void setDatevente(String datevente) {
		Object oldValue = paiement.getDatevente();
		Object newValue = datevente;

		paiement.setDatevente(datevente);

		fireXSChanged(this, DATEVENTE_CHANGED, oldValue, newValue);		
	}

	public String getPrixVenteReel() {
		return paiement.getPrixVenteReel();
	}

	public void setPrixVenteReel(String prixVenteReel) {
		Object oldValue = paiement.getPrixVenteReel();
		Object newValue = prixVenteReel;

		paiement.setPrixVenteReel(prixVenteReel);

		fireXSChanged(this, PRIXVENTEREEL_CHANGED, oldValue, newValue);	
	}
    
    public void reset() {
    	setDatevente(null);
    	setTypePaiement(null);
    	setBanquePaiement(null);
    	setNumeroBanque(null);
    	setNumeroChequePaiement(null);
    	setPrixVenteReel(null);
    	setTypeidentite(null);
    }
    
}