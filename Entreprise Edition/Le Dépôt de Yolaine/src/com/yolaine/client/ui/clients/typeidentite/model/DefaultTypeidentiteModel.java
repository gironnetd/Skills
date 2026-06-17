package com.yolaine.client.ui.clients.typeidentite.model;

import static com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteEventPropertyName.*;

import java.util.Date;
import java.util.Set;
import com.yolaine.client.ui.commun.adresse.model.AdresseModel;
import com.yolaine.client.ui.commun.adresse.model.DefaultAdresseModel;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public class DefaultTypeidentiteModel extends AbstractTypeidentiteModel {
    
    private static final long serialVersionUID = 6117458605996580214L;
    
    
    private TypeIdentite typeidentite;
    
    public DefaultTypeidentiteModel() {        
    	TypeIdentite typeidentite = new TypeIdentite();
        setTypeidentite(typeidentite);
    }
    
    public DefaultTypeidentiteModel(TypeIdentite typeidentite) {
    	setTypeidentite(typeidentite);
    }    
    
    public TypeIdentite getTypeidentite() {
		return typeidentite;
	}

	public Long getId() { return typeidentite.getId(); }

	public void setTypeidentite(TypeIdentite typeidentite) {
		if (typeidentite == null){
			throw new IllegalArgumentException("Le type d'identité ne doit pas être null" +
					"");
		}
			TypeIdentite oldValue = this.typeidentite;
			TypeIdentite newValue = typeidentite;
			
			this.typeidentite = typeidentite;
			
			if(oldValue != null){
			fireXSChanged(this, TYPEIDENTITE_CHANGED, oldValue.getTypeIdentite(), newValue
                    .getTypeIdentite());
		
			}
			}         
    
    public void reset() {    	
    	setTypeidentite(null);    	
    }

	
}