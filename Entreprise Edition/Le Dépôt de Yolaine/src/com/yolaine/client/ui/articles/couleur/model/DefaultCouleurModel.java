package com.yolaine.client.ui.articles.couleur.model;


import static com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName.*;


import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;


public class DefaultCouleurModel extends AbstractCouleurModel {
    
    private static final long serialVersionUID = 1602655452741754011L;
    

    private Couleur couleur;
    private Long identifierToFind;
    
    
    public DefaultCouleurModel() {
       setCouleur(new Couleur());
    }
    
    public DefaultCouleurModel(Couleur couleur) {
        setCouleur(couleur);
    }

    public Long getId() { return couleur.getId();}
    
    public Couleur getCouleur() {
        return couleur;
    }
    
    public void setCouleur(Couleur couleur) {
        if (couleur == null) {
            throw new IllegalArgumentException("category must be non null");
        }
        
        Couleur oldValue = this.couleur;
        Couleur newValue = couleur;
        
        this.couleur = couleur;
        
       // if (oldValue != null) {           
        //    fireXSChanged(this, NAME_CHANGED, oldValue.getCouleur(), newValue
        //            .getCouleur());
            
     //   }
    }  
    
    public void reset() {
        setCouleur(null);
    }
    
}