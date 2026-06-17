package com.yolaine.client.ui.commun.adresse.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface AdresseListener extends XSListener<AdresseEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
    public void adresse1Changed(XSEvent<AdresseEventPropertyName, String> evt);
    
    public void adresse2Changed(XSEvent<AdresseEventPropertyName, String> evt);
    
    public void codepostalChanged(XSEvent<AdresseEventPropertyName, String> evt);    
    
    public void villeChanged(XSEvent<AdresseEventPropertyName, String> evt);    
    
    
        
}