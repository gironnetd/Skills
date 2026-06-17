package com.yolaine.client.ui.transaction.typepaiement.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class TypePaiementAdapter extends XSAdapter<TypePaiementEventPropertyName>
        implements TypePaiementListener {
    
   
    
    public void typePaiementChanged(XSEvent<TypePaiementEventPropertyName, String> evt) {
    }  
    
}