package com.yolaine.client.ui.clients.typeidentite.event;

import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.TypeIdentite;


public class TypeidentiteAdapter extends XSAdapter<TypeidentiteEventPropertyName>
implements TypeidentiteListener {	
	
	public void typeidentiteChanged(XSEvent<TypeidentiteEventPropertyName, TypeIdentite> evt){	    	
	}	
}