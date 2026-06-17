package com.yolaine.client.ui.articles.typearticle.model;


import static com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName.*;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;




public abstract class AbstractTypearticleModel extends
        AbstractXSModel<TypearticleListener, TypearticleEventPropertyName> implements
        TypearticleModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(TypearticleListener listener,
            XSEvent<TypearticleEventPropertyName, ?> evt) {
        TypearticleEventPropertyName propertyName = evt.getPropertyNameEnumType();
        
        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<TypearticleEventPropertyName, Long>) evt);
        } else if (propertyName == NAME_CHANGED) {
            listener
                    .nameChanged((XSEvent<TypearticleEventPropertyName, String>) evt);
        } else if (propertyName == DESCRIPTION_CHANGED) {
            listener
                    .descriptionChanged((XSEvent<TypearticleEventPropertyName, String>) evt);
        }
    }
    
}