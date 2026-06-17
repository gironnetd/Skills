package com.yolaine.client.ui.articles.manche.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.manche.event.MancheEventPropertyName;
import com.yolaine.client.ui.articles.manche.event.MancheListener;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;


public interface MancheModel extends
        XSModel<MancheListener, MancheEventPropertyName> {

    public Long getId();

    public Manche getManche();
    
    public void setManche(Manche manche);
    
    
}