package com.yolaine.client.ui.clients.client.model;


import static com.yolaine.client.ui.clients.client.event.ClientEventPropertyName.*;


import java.util.Date;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;


import com.yolaine.client.ui.clients.client.event.ClientEventPropertyName;
import com.yolaine.client.ui.clients.client.event.ClientListener;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.TypeIdentite;


public abstract class AbstractClientModel extends
AbstractXSModel<ClientListener, ClientEventPropertyName> implements
ClientModel {


	@Override
	@SuppressWarnings("unchecked")
	protected void listenerMethodCaller(ClientListener listener,
			XSEvent<ClientEventPropertyName, ?> evt) {
		ClientEventPropertyName propertyName = evt.getPropertyNameEnumType();

		 if (propertyName == CIVILITE_CHANGED) {
			listener
			.civiliteChanged((XSEvent<ClientEventPropertyName, Civilite>) evt);
		}else if (propertyName == DEPOSANT_CHANGED) {
			listener
			.deposantChanged((XSEvent<ClientEventPropertyName, Boolean>) evt);
		}else if (propertyName == NOM_CHANGED) {
			listener
			.nomChanged((XSEvent<ClientEventPropertyName, String>) evt);
		} else if (propertyName == PRENOM_CHANGED) {
			listener
			.prenomChanged((XSEvent<ClientEventPropertyName, String>) evt);
		} else if (propertyName == LOGIN_CHANGED) {
			listener
			.loginChanged((XSEvent<ClientEventPropertyName, String>) evt);
		} else if (propertyName == PASSWORD_CHANGED) {
			listener
			.passwordChanged((XSEvent<ClientEventPropertyName, String>) evt);
		}else if (propertyName == TELEPHONE_FIXE_CHANGED) {
			listener
			.telephonefixeChanged((XSEvent<ClientEventPropertyName, String>) evt);
		} else if (propertyName == TELEPHONE_PORTABLE_CHANGED) {
			listener
			.telephoneportableChanged((XSEvent<ClientEventPropertyName, String>) evt);
		} else if (propertyName == EMAIL_CHANGED) {
			listener
			.emailChanged((XSEvent<ClientEventPropertyName, String>) evt);
		}else if (propertyName == TYPEIDENTITE_CHANGED) {
			listener
			.typeidentiteChanged((XSEvent<ClientEventPropertyName, TypeIdentite>) evt);
		}else if (propertyName == NUMEROIDENTITE_CHANGED) {
			listener
			.numeroidentiteChanged((XSEvent<ClientEventPropertyName, String>) evt);
		}else if (propertyName == COMMENTAIRE_CHANGED) {
			listener
			.commentaireChanged((XSEvent<ClientEventPropertyName, String>) evt);
		}else if (propertyName == CHAMP_NUMERIQUE_1_CHANGED) {
			listener
			.champnumerique1Changed((XSEvent<ClientEventPropertyName, String>) evt);
		}else if (propertyName == CHAMP_NUMERIQUE_2_CHANGED) {
			listener
			.champnumerique2Changed((XSEvent<ClientEventPropertyName, String>) evt);
		}else if (propertyName == DATE_DE_NAISSANCE_CHANGED) {
			listener
			.datenaissanceChanged((XSEvent<ClientEventPropertyName, String>) evt);
		}	
	}

}