package com.yolaine.client.ui.transaction.typepaiement.model;


import static com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementEventPropertyName.*;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;
import com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementEventPropertyName;
import com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementListener;




public abstract class AbstractTypePaiementModel extends
        AbstractXSModel<TypePaiementListener, TypePaiementEventPropertyName> implements
        TypePaiementModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(TypePaiementListener listener,
            XSEvent<TypePaiementEventPropertyName, ?> evt) {
    	TypePaiementEventPropertyName propertyName = evt.getPropertyNameEnumType();
         
       if (propertyName == TYPEPAIEMENT_CHANGED) {
            listener
                    .typePaiementChanged((XSEvent<TypePaiementEventPropertyName, String>) evt);
        }
    }
    
}