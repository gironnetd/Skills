package com.yolaine.client.ui.articles.manche.model;


import static com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName.*;


import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;


public class DefaultMancheModel extends AbstractMancheModel {
    
    private static final long serialVersionUID = 1602655452741754011L;
    

    private Manche manche;
    private Long identifierToFind;
    
    
    public DefaultMancheModel() {
       setManche(new Manche());
    }
    
    public DefaultMancheModel(Manche manche) {
       setManche(manche);
    }
    
    
    public Manche getManche() {
        return manche;
    }

    public Long getId() { return manche.getId();}

    public void setManche(Manche manche) {
        if (manche == null) {
            throw new IllegalArgumentException("category must be non null");
        }
        
        Manche oldValue = this.manche;
        Manche newValue = manche;
        
        this.manche = manche;
        
       // if (oldValue != null) {           
        //    fireXSChanged(this, NAME_CHANGED, oldValue.getCouleur(), newValue
        //            .getCouleur());
            
     //   }
    }  
    
    public void reset() {
        setManche(null);
    }
    
}