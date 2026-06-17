package com.yolaine.client.ui.transaction.banque.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface BanqueListener extends XSListener<BanqueEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
   
    public void banqueChanged(XSEvent<BanqueEventPropertyName, String> evt);
    
   
    
}