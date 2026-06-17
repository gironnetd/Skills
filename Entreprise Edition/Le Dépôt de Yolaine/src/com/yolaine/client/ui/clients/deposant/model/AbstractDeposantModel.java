package com.yolaine.client.ui.clients.deposant.model;


import static com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName.*;


import java.util.Date;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName;
import com.yolaine.client.ui.clients.client.event.ClientListener;
import com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName;
import com.yolaine.client.ui.clients.deposant.event.DeposantListener;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public abstract class AbstractDeposantModel extends
AbstractXSModel<DeposantListener, DeposantEventPropertyName> implements
DeposantModel {


	@Override
	@SuppressWarnings("unchecked")
	protected void listenerMethodCaller(DeposantListener listener,
			XSEvent<DeposantEventPropertyName, ?> evt) {
		DeposantEventPropertyName propertyName = evt.getPropertyNameEnumType();

		 if (propertyName == CIVILITE_CHANGED) {
			listener
			.civiliteChanged((XSEvent<DeposantEventPropertyName, Civilite>) evt);
		}else if (propertyName == DEPOSANT_CHANGED) {
			listener
			.deposantChanged((XSEvent<DeposantEventPropertyName, Boolean>) evt);
		}else if (propertyName == NOM_CHANGED) {
			listener
			.nomChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		} else if (propertyName == PRENOM_CHANGED) {
			listener
			.prenomChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == NOMCOMBO_CHANGED) {
			listener
			.nomComboChanged((XSEvent<DeposantEventPropertyName, Client>) evt);
		} else if (propertyName == PRENOMCOMBO_CHANGED) {
			listener
			.prenomComboChanged((XSEvent<DeposantEventPropertyName, Client>) evt);
		}  else if (propertyName == LOGIN_CHANGED) {
			listener
			.loginChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		} else if (propertyName == PASSWORD_CHANGED) {
			listener
			.passwordChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == TELEPHONE_FIXE_CHANGED) {
			listener
			.telephonefixeChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		} else if (propertyName == TELEPHONE_PORTABLE_CHANGED) {
			listener
			.telephoneportableChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		} else if (propertyName == EMAIL_CHANGED) {
			listener
			.emailChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == TYPEIDENTITE_CHANGED) {
			listener
			.typeidentiteChanged((XSEvent<DeposantEventPropertyName, TypeIdentite>) evt);
		}else if (propertyName == NUMEROIDENTITE_CHANGED) {
			listener
			.numeroidentiteChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == COMMENTAIRE_CHANGED) {
			listener
			.commentaireChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == CHAMP_NUMERIQUE_1_CHANGED) {
			listener
			.champnumerique1Changed((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == CHAMP_NUMERIQUE_2_CHANGED) {
			listener
			.champnumerique2Changed((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == DATE_DE_NAISSANCE_CHANGED) {
			listener
			.datenaissanceChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == MONTANTDEPOSE_CHANGED) {
			listener
			.montantdeposeChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		}else if (propertyName == MONTANTDU_CHANGED) {
			listener
			.montantduChanged((XSEvent<DeposantEventPropertyName, String>) evt);
		} 
		
	}

}