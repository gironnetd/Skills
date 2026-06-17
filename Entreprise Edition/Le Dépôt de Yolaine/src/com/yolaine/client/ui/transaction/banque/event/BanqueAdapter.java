package com.yolaine.client.ui.transaction.banque.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class BanqueAdapter extends XSAdapter<BanqueEventPropertyName>
        implements BanqueListener {
    
   
    
    public void banqueChanged(XSEvent<BanqueEventPropertyName, String> evt) {
    }  
    
}