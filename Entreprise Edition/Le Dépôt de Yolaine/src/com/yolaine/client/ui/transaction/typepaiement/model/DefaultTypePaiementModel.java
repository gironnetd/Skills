package com.yolaine.client.ui.transaction.typepaiement.model;


import static com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementEventPropertyName.*;

import com.yolaine.entity.transaction.TypePaiement;

public class DefaultTypePaiementModel extends AbstractTypePaiementModel {
    
    private static final long serialVersionUID = 1602655452741754011L;
    

    private TypePaiement typePaiement;
    private Long identifierToFind;

    public DefaultTypePaiementModel() {
      setTypePaiement(new TypePaiement());
    }
    
    public DefaultTypePaiementModel(TypePaiement typePaiement) {       
        setTypePaiement(typePaiement);
    }  
    
    public TypePaiement getTypePaiement() {
		return typePaiement;
	}

    public Long getId() { return typePaiement.getId(); }

    public void setTypePaiement(TypePaiement typePaiement) {
        if (typePaiement == null) {
            throw new IllegalArgumentException("category must be non null");
        }
        
        TypePaiement oldValue = this.typePaiement;
        TypePaiement newValue = typePaiement;
        
        this.typePaiement = typePaiement;
        
        if (oldValue != null) {           
            fireXSChanged(this, TYPEPAIEMENT_CHANGED, oldValue.getTypePaiement(), newValue
                    .getTypePaiement());
            
        }
    }  
    
    public void reset() {
       setTypePaiement(null);
    }
    
}