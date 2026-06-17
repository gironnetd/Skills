package com.yolaine.client.ui.clients.typeidentite.model;


import static com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteEventPropertyName.*;


import java.util.Date;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;


import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteEventPropertyName;
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteListener;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.TypeIdentite;


public abstract class AbstractTypeidentiteModel extends
AbstractXSModel<TypeidentiteListener, TypeidentiteEventPropertyName> implements
TypeidentiteModel {


	@Override
	@SuppressWarnings("unchecked")
	protected void listenerMethodCaller(TypeidentiteListener listener,
			XSEvent<TypeidentiteEventPropertyName, ?> evt) {
		TypeidentiteEventPropertyName propertyName = evt.getPropertyNameEnumType();

		 if (propertyName == TYPEIDENTITE_CHANGED) {
			listener
			.typeidentiteChanged((XSEvent<TypeidentiteEventPropertyName, TypeIdentite>) evt);
		}		
	}

}