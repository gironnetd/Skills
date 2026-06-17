package com.yolaine.client.ui.depots.event;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public interface DepotListener extends XSListener<DepotEventPropertyName> {
    
    // ------------------------------------------------------------------------
    //     
	
    public void identifiantChanged(XSEvent<DepotEventPropertyName, Integer> evt);
    
    public void datedepotChanged(XSEvent<DepotEventPropertyName, String> evt);
    
    public void cloturedepotChanged(XSEvent<DepotEventPropertyName, Boolean> evt);    
   
    public void clientChanged(XSEvent<DepotEventPropertyName,Client> evt);    
    
}