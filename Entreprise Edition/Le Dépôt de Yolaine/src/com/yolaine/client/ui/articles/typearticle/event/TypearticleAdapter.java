package com.yolaine.client.ui.articles.typearticle.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class TypearticleAdapter extends XSAdapter<TypearticleEventPropertyName>
        implements TypearticleListener {
    
    public void identifierChanged(XSEvent<TypearticleEventPropertyName, Long> evt) {
    }
    
    public void nameChanged(XSEvent<TypearticleEventPropertyName, String> evt) {
    }
    
    public void descriptionChanged(
            XSEvent<TypearticleEventPropertyName, String> evt) {
    }
    
}