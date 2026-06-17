package com.yolaine.client.ui.articles.couleur.model;


import static com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName.*;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;




public abstract class AbstractCouleurModel extends
        AbstractXSModel<CouleurListener, CouleurEventPropertyName> implements
        CouleurModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(CouleurListener listener,
            XSEvent<CouleurEventPropertyName, ?> evt) {
        CouleurEventPropertyName propertyName = evt.getPropertyNameEnumType();
         
       if (propertyName == NAME_CHANGED) {
            listener
                    .couleurChanged((XSEvent<CouleurEventPropertyName, String>) evt);
        }
    }
    
}