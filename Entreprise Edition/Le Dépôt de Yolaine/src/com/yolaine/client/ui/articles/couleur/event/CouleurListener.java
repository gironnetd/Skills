package com.yolaine.client.ui.articles.couleur.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface CouleurListener extends XSListener<CouleurEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
   
    public void couleurChanged(XSEvent<CouleurEventPropertyName, String> evt);
    
   
    
}