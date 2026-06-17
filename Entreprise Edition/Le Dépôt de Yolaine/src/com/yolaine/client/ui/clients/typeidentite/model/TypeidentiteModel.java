package com.yolaine.client.ui.clients.typeidentite.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.commun.adresse.model.AdresseModel;

import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteEventPropertyName;
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteListener;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public interface TypeidentiteModel extends
        XSModel<TypeidentiteListener, TypeidentiteEventPropertyName> {

	public Long getId();
    
	public TypeIdentite getTypeidentite();

	public void setTypeidentite(TypeIdentite typeidentite);

}