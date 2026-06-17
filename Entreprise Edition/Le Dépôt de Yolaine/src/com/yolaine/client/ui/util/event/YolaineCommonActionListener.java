package com.yolaine.client.ui.util.event;


import java.util.EventListener;
import java.util.EventObject;


public interface YolaineCommonActionListener extends EventListener {
    
    public void resetActionPerformed(EventObject evt);
    
    public void resetArticleActionPerformed(EventObject evt);
    
    public void closeActionPerformed(EventObject evt);
    
}