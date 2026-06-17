package com.yolaine.client.ui.transaction.remboursement.model;

import static com.yolaine.client.ui.transaction.remboursement.event.RemboursementEventPropertyName.*;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementEventPropertyName;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementListener;
import com.yolaine.entity.transaction.*;

public abstract class AbstractRemboursementModel extends
        AbstractXSModel<RemboursementListener, RemboursementEventPropertyName> implements
        RemboursementModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(RemboursementListener listener,
            XSEvent<RemboursementEventPropertyName, ?> evt) {
    	RemboursementEventPropertyName propertyName = evt.getPropertyNameEnumType();
         
    	  if (propertyName == BANQUEREMBOURSEMENT_CHANGED) {
 			listener
 			.banqueRemboursementChanged((XSEvent<RemboursementEventPropertyName, Banque>) evt);
 		} else if (propertyName == TYPEREMBOURSEMENT_CHANGED) {
 			listener
 			.typeremboursementChanged((XSEvent<RemboursementEventPropertyName, TypePaiement>) evt);
 		} else if (propertyName == NUMEROCHEQUEREMBOURSEMENT_CHANGED) {
 			listener
 			.numeroChequeRemboursementChanged((XSEvent<RemboursementEventPropertyName, String>) evt);
 		}  else if (propertyName == MONTANTREMBOURSE_CHANGED) {
 			listener
 			.montantRembourseChanged((XSEvent<RemboursementEventPropertyName, String>) evt);
 		} else if (propertyName == DATEREMBOURSEMENT_CHANGED) {
			listener
			.dateremboursementChanged((XSEvent<RemboursementEventPropertyName, String>) evt);
		} 
 		
    }
    
}