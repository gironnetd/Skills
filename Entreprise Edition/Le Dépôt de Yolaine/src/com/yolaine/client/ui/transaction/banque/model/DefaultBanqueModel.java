package com.yolaine.client.ui.transaction.banque.model;

import static com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName.*;
import com.yolaine.entity.transaction.Banque;

public class DefaultBanqueModel extends AbstractBanqueModel {
    
    private static final long serialVersionUID = 1602655452741754011L;
    

    private Banque banque;
    private Long identifierToFind;
    
    
    public DefaultBanqueModel() {
      setBanque(new Banque());
    }
    
    public DefaultBanqueModel(Banque banque) {
       setBanque(banque);
    }
    
    
    public Banque getBanque() {
        return banque;
    }

    public Long getId() { return banque.getId(); }

    public void setBanque(Banque banque) {
        if (banque == null) {
            throw new IllegalArgumentException("La banque ne doît pas être nul.");
        }
        
        Banque oldValue = this.banque;
        Banque newValue = banque;
        
        this.banque = banque;
        
        if (oldValue != null) {           
            fireXSChanged(this, BANQUE_CHANGED, oldValue.getBanque(), newValue
                   .getBanque());
            
        }
    }  
    
    public void reset() {
       setBanque(null);
    }
    
}