package com.yolaine.client.ui.transaction.typepaiement.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;
import com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementEventPropertyName;
import com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementListener;
import com.yolaine.entity.transaction.TypePaiement;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;


public interface TypePaiementModel extends
        XSModel<TypePaiementListener, TypePaiementEventPropertyName> {

    public Long getId();

    public TypePaiement getTypePaiement();
    
    public void setTypePaiement(TypePaiement typePaiement);
    
    
}