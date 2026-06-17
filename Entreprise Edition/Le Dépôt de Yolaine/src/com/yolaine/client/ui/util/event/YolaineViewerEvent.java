package com.yolaine.client.ui.util.event;


import java.util.EventObject;

import com.yolaine.client.ui.util.YolaineViewType;


public class YolaineViewerEvent extends EventObject {
    
    private static final long serialVersionUID = 1064634318266602259L;
    

    private final YolaineViewType oldValue;
    private final YolaineViewType newValue;
    
    
    public YolaineViewerEvent(Object source, YolaineViewType oldValue,
            YolaineViewType newValue) {
        super(source);
        
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    
    public YolaineViewType getOldValue() {
        return oldValue;
    }
    
    public YolaineViewType getNewValue() {
        return newValue;
    }
    
}