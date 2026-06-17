package com.yolaine.client.ui.util.event;


import java.util.EventListener;


public interface YolaineViewerListener extends EventListener {
    
    public void yolaineViewChanged(YolaineViewerEvent evt);
    
}