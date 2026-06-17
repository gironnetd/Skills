package com.yolaine.client.ui.transaction.paiement.model;

import static com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName.*;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.event.PaiementListener;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.client.TypeIdentite;

public abstract class AbstractPaiementModel extends
        AbstractXSModel<PaiementListener, PaiementEventPropertyName> implements
        PaiementModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(PaiementListener listener,
            XSEvent<PaiementEventPropertyName, ?> evt) {
    	PaiementEventPropertyName propertyName = evt.getPropertyNameEnumType();
         
    	 if (propertyName == DATEVENTE_CHANGED) {
			listener
			.dateventeChanged((XSEvent<PaiementEventPropertyName, String>) evt);
		}  else if (propertyName == BANQUEPAIEMENT_CHANGED) {
			listener
			.banquePaiementChanged((XSEvent<PaiementEventPropertyName, Banque>) evt);
		}  else if (propertyName == TYPEPAIEMENT_CHANGED) {
			listener
			.typepaiementChanged((XSEvent<PaiementEventPropertyName, TypePaiement>) evt);
		} else if (propertyName == BANQUETYPEIDENTITE_CHANGED) {
			listener
			.typeIdentiteChanged((XSEvent<PaiementEventPropertyName, TypeIdentite>) evt);
		} else if (propertyName == NUMEROBANQUE_CHANGED) {
			listener
			.numeroBanqueChanged((XSEvent<PaiementEventPropertyName, String>) evt);
		} else if (propertyName == NUMEROCHEQUEPAIEMENT_CHANGED) {
			listener
			.numeroChequePaiementChanged((XSEvent<PaiementEventPropertyName, String>) evt);
		}   else if (propertyName == BANQUEPAIEMENT_CHANGED) {
			listener
			.banquePaiementChanged((XSEvent<PaiementEventPropertyName, Banque>) evt);
		}   else if (propertyName == PRIXVENTEREEL_CHANGED) {
			listener
			.prixVenteReelChanged((XSEvent<PaiementEventPropertyName, String>) evt);
		} 
    }
    
}