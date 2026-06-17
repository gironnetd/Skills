package com.yolaine.client.ui.transaction.typepaiement.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface TypePaiementListener extends XSListener<TypePaiementEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
   
    public void typePaiementChanged(XSEvent<TypePaiementEventPropertyName, String> evt);
    
   
    
}