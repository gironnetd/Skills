package com.yolaine.client.ui.clients.typeidentite.event;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.TypeIdentite;


public interface TypeidentiteListener extends XSListener<TypeidentiteEventPropertyName> {
    
    // ------------------------------------------------------------------------
    //     	
	 
    public void typeidentiteChanged(XSEvent<TypeidentiteEventPropertyName, TypeIdentite> evt);  
   
}