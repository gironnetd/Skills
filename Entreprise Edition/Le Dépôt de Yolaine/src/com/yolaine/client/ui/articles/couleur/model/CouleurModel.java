package com.yolaine.client.ui.articles.couleur.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;


public interface CouleurModel extends
        XSModel<CouleurListener, CouleurEventPropertyName> {

    public Long getId();

    public Couleur getCouleur();
    
    public void setCouleur(Couleur couleur);
    
    
}