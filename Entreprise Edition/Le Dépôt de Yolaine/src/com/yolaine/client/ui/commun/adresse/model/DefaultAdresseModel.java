package com.yolaine.client.ui.commun.adresse.model;


import static com.yolaine.client.ui.commun.adresse.event.AdresseEventPropertyName.*;

import com.yolaine.entity.Adresse;


public class DefaultAdresseModel extends AbstractAdresseModel {
    
    private static final long serialVersionUID = 4412344911551538665L;
    

    private Adresse adresse;

    public DefaultAdresseModel() {
        this(new Adresse());
    }
    
    public DefaultAdresseModel(Adresse adresse) {
        setAdresse(adresse);
    }
    
    
    
    public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		if (adresse == null) {
           throw new IllegalArgumentException("L'adresse ne doît pas être nulle");
       }
        
        Adresse oldValue = this.adresse;
        Adresse newValue = adresse;
        
        this.adresse = adresse;
        
        if (oldValue != null) {
            fireXSChanged(this, ADRESSE_1_CHANGED, oldValue.getAdresse1(),
                    newValue.getAdresse1());
            fireXSChanged(this, ADRESSE_2_CHANGED, oldValue.getAdresse2(),
                    newValue.getAdresse2());
            fireXSChanged(this, CODEPOSTAL_CHANGED, oldValue.getCodePostal(), newValue
                    .getCodePostal());
            fireXSChanged(this, VILLE_CHANGED, oldValue.getVille(),
                    newValue.getVille());            
        }
	}

    public Long getId() { return adresse.getId(); }

    public String getAdresse1() {
        return adresse.getAdresse1();
    }
    
    public void setAdresse1(String adresse1) {
        Object oldValue = adresse.getAdresse1();
        Object newValue = adresse1;
        
        adresse.setAdresse1(adresse1);
        
        fireXSChanged(this, ADRESSE_1_CHANGED, oldValue, newValue);
    }
    
    public String getAdresse2() {
        return adresse.getAdresse2();
    }
    
    public void setAdresse2(String adresse2) {
        Object oldValue = adresse.getAdresse2();
        Object newValue = adresse2;
        
        adresse.setAdresse2(adresse2);
        
        fireXSChanged(this, ADRESSE_2_CHANGED, oldValue, newValue);
    }
    
    public String getCodepostal() {
        return adresse.getCodePostal();
    }
    
    public void setCodepostal(String codepostal) {
        Object oldValue = adresse.getCodePostal();
        Object newValue = codepostal;
        
        adresse.setCodePostal(codepostal);
        
        fireXSChanged(this, CODEPOSTAL_CHANGED, oldValue, newValue);
    }  
    
    public String getVille() {
        return adresse.getVille();
    }
    
    public void setVille(String ville) {
        Object oldValue = adresse.getVille();
        Object newValue = ville;
        
        adresse.setVille(ville);
        
        fireXSChanged(this, VILLE_CHANGED, oldValue, newValue);
    }      
    
    public void reset() {
        setAdresse1(null);
        setAdresse2(null);
        setCodepostal(null);
        setVille(null);        
                
    }
    
}