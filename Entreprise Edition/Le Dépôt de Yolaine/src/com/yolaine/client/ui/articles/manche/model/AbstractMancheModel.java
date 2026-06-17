package com.yolaine.client.ui.articles.manche.model;


import static com.yolaine.client.ui.articles.manche.event.MancheEventPropertyName.*;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.manche.event.MancheEventPropertyName;
import com.yolaine.client.ui.articles.manche.event.MancheListener;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;




public abstract class AbstractMancheModel extends
        AbstractXSModel<MancheListener, MancheEventPropertyName> implements
        MancheModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(MancheListener listener,
            XSEvent<MancheEventPropertyName, ?> evt) {
        MancheEventPropertyName propertyName = evt.getPropertyNameEnumType();
         
       if (propertyName == NAME_CHANGED) {
            listener
                    .mancheChanged((XSEvent<MancheEventPropertyName, String>) evt);
        }
    }
    
}