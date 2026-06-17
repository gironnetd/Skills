package com.yolaine.client.ui.articles.manche.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface MancheListener extends XSListener<MancheEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
   
    public void mancheChanged(XSEvent<MancheEventPropertyName, String> evt);
    
   
    
}