package com.yolaine.client.ui.articles.marque.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import com.yolaine.entity.catalogue.Categorie;


public interface MarqueListener extends XSListener<MarqueEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
    public void identifierChanged(XSEvent<MarqueEventPropertyName, Long> evt);
    
    public void nameChanged(XSEvent<MarqueEventPropertyName, String> evt);
    
    public void descriptionChanged(XSEvent<MarqueEventPropertyName, String> evt);
    
    public void categoryChanged(XSEvent<MarqueEventPropertyName, Categorie> evt);
    
}