package com.yolaine.client.ui.depots.model;


import static com.yolaine.client.ui.depots.event.DepotEventPropertyName.*;


import java.util.Date;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;


import com.yolaine.client.ui.depots.event.DepotEventPropertyName;
import com.yolaine.client.ui.depots.event.DepotListener;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.TypeIdentite;


public abstract class AbstractDepotModel extends
AbstractXSModel<DepotListener, DepotEventPropertyName> implements
DepotModel {

	@Override
	@SuppressWarnings("unchecked")
	protected void listenerMethodCaller(DepotListener listener,
			XSEvent<DepotEventPropertyName, ?> evt) {
		DepotEventPropertyName propertyName = evt.getPropertyNameEnumType();

		 if (propertyName == IDENTIFIANT_CHANGED) {
			listener
			.identifiantChanged((XSEvent<DepotEventPropertyName, Integer>) evt);
		}else if (propertyName == DATEDEPOT_CHANGED) {
			listener
			.datedepotChanged((XSEvent<DepotEventPropertyName, String>) evt);
		}else if (propertyName == CLOTUREDEPOT_CHANGED) {
			listener
			.cloturedepotChanged((XSEvent<DepotEventPropertyName, Boolean>) evt);
		}
	}
}