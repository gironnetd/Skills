package com.yolaine.client.ui.articles.typearticle.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface TypearticleListener extends XSListener<TypearticleEventPropertyName> {
    
    // ------------------------------------------------------------------------
    // 
    public void identifierChanged(XSEvent<TypearticleEventPropertyName, Long> evt);
    
    public void nameChanged(XSEvent<TypearticleEventPropertyName, String> evt);
    
    public void descriptionChanged(
            XSEvent<TypearticleEventPropertyName, String> evt);
    
}