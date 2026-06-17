package com.yolaine.client.ui.articles.typearticle.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;
import com.yolaine.entity.catalogue.Categorie;


public interface TypearticleModel extends
        XSModel<TypearticleListener, TypearticleEventPropertyName> {

    public Long getId();

    public Categorie getCategory();
    
    public void setCategory(Categorie category);

   // public Long getIdentifierToFind();
    
  //  public void setIdentifierToFind(Long identifier);
    
  //  public Long getIdentifier();
    
    
    public String getName();
    
    public void setName(String name);
    
    
    public String getDescription();
    
    public void setDescription(String description);
    
}