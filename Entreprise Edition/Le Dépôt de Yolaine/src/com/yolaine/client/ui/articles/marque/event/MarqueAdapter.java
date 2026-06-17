package com.yolaine.client.ui.articles.marque.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.entity.catalogue.Categorie;


public class MarqueAdapter extends XSAdapter<MarqueEventPropertyName>
        implements MarqueListener {
    
    public void identifierChanged(XSEvent<MarqueEventPropertyName, Long> evt) {
    }
    
    public void nameChanged(XSEvent<MarqueEventPropertyName, String> evt) {
    }
    
    public void descriptionChanged(XSEvent<MarqueEventPropertyName, String> evt) {
    }
    
    public void categoryChanged(XSEvent<MarqueEventPropertyName, Categorie> evt) {
    }
    
}