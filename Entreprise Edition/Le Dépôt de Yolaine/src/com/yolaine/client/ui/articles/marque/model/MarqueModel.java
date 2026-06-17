package com.yolaine.client.ui.articles.marque.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.marque.event.MarqueEventPropertyName;
import com.yolaine.client.ui.articles.marque.event.MarqueListener;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Marque;


public interface MarqueModel extends
        XSModel<MarqueListener, MarqueEventPropertyName> {

    public Long getId();

    public Marque getMarque();

    public void setMarque(Marque marque);    
    
    public Long getIdentifierToFind();
    
    public void setIdentifierToFind(Long identifier);        
    
    public String getName();
    
    public void setName(String name);    
    
    public String getDescription();
    
    public void setDescription(String description);    
    
    public Categorie getCategory();
    
    public void setCategory(Categorie category);
    
}