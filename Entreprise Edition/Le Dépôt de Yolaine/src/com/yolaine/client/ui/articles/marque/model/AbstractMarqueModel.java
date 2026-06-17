package com.yolaine.client.ui.articles.marque.model;


import static com.yolaine.client.ui.articles.marque.event.MarqueEventPropertyName.*;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.marque.event.MarqueEventPropertyName;
import com.yolaine.client.ui.articles.marque.event.MarqueListener;
import com.yolaine.entity.catalogue.Categorie;


public abstract class AbstractMarqueModel extends
        AbstractXSModel<MarqueListener, MarqueEventPropertyName> implements
        MarqueModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(MarqueListener listener,
            XSEvent<MarqueEventPropertyName, ?> evt) {
        MarqueEventPropertyName propertyName = evt.getPropertyNameEnumType();
        
        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<MarqueEventPropertyName, Long>) evt);
        } else if (propertyName == NAME_CHANGED) {
            listener
                    .nameChanged((XSEvent<MarqueEventPropertyName, String>) evt);
        } else if (propertyName == DESCRIPTION_CHANGED) {
            listener
                    .descriptionChanged((XSEvent<MarqueEventPropertyName, String>) evt);
        } else if (propertyName == CATEGORY_CHANGED) {
            listener
                    .categoryChanged((XSEvent<MarqueEventPropertyName, Categorie>) evt);
        }
    }
    
}