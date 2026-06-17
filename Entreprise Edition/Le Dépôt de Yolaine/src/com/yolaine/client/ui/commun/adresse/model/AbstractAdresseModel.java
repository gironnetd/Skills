package com.yolaine.client.ui.commun.adresse.model;


import static com.yolaine.client.ui.commun.adresse.event.AdresseEventPropertyName.*;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.commun.adresse.event.AdresseEventPropertyName;
import com.yolaine.client.ui.commun.adresse.event.AdresseListener;


public abstract class AbstractAdresseModel extends
AbstractXSModel<AdresseListener, AdresseEventPropertyName> implements
AdresseModel {

	@Override
	@SuppressWarnings("unchecked")
	protected void listenerMethodCaller(AdresseListener listener,
			XSEvent<AdresseEventPropertyName, ?> evt) {
		AdresseEventPropertyName propertyName = evt.getPropertyNameEnumType();

		if (propertyName == ADRESSE_1_CHANGED) {
			listener
			.adresse1Changed((XSEvent<AdresseEventPropertyName, String>) evt);
		} else if (propertyName == ADRESSE_2_CHANGED) {
			listener
			.adresse2Changed((XSEvent<AdresseEventPropertyName, String>) evt);
		}else if (propertyName == CODEPOSTAL_CHANGED) {
			listener
			.codepostalChanged((XSEvent<AdresseEventPropertyName, String>) evt);
		}else if (propertyName == VILLE_CHANGED) {
			listener
			.villeChanged((XSEvent<AdresseEventPropertyName, String>) evt);
		} 
	}

}