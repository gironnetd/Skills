package com.yolaine.client.ui.util;


import static com.yolaine.client.ui.util.YolaineViewType.*;
import javax.swing.event.EventListenerList;
import org.vstm.fwk.client.ui.xswing.core.XSComponent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;
import com.yolaine.client.ui.util.event.YolaineViewerEvent;
import com.yolaine.client.ui.util.event.YolaineViewerListener;


public abstract class YolaineComponentPane<M extends XSModel<L, E>, L extends XSListener<E>, E extends Enum>
        extends XSComponent<M, L, E> {
    
    protected final EventListenerList listenerList = new EventListenerList();
    
    protected YolaineViewType viewType;
    
    
    public YolaineComponentPane() {
        this(CHERCHER_OU_CREER);
    }
    
    public YolaineComponentPane(M model) {
        this(model,CHERCHER_OU_CREER);
    }
    
    public YolaineComponentPane(YolaineViewType viewType) {
        setViewType(viewType);
    }
    
    public YolaineComponentPane(M model, YolaineViewType viewType) {
        super(model);
        
        setViewType(viewType);
    }
    
    
    public void addYolaineViewListener(YolaineViewerListener listener) {
        listenerList.add(YolaineViewerListener.class, listener);
    }
    
    public void removeYolaineViewListener(YolaineViewerListener listener) {
        listenerList.remove(YolaineViewerListener.class, listener);
    }
    
    public YolaineViewerListener[] getYolaineViewListeners() {
        return listenerList.getListeners(YolaineViewerListener.class);
    }
    
    
    protected void fireYolaineViewChanged(Object source, YolaineViewType oldValue,
            YolaineViewType newValue) {
        YolaineViewerListener[] listeners = getYolaineViewListeners();
        YolaineViewerEvent evt = null;
        
        for (int i = listeners.length - 1; i >= 0; i--) {
            if (evt == null) {
                evt = new YolaineViewerEvent(this, oldValue, newValue);
            }
            
            listeners[i].yolaineViewChanged(evt);
        }
    }
    
    public YolaineViewType getViewType() {
        return viewType;
    }
    
    public void setViewType(YolaineViewType viewType) {
        if (viewType == null) {
            viewType = CHERCHER_OU_CREER;
        }
        
        synchronizeViewType(viewType);
        
        YolaineViewType oldValue = this.viewType;
        YolaineViewType newValue = viewType;
        this.viewType = viewType;
        
        fireYolaineViewChanged(this, oldValue, newValue);
    }
    
    protected abstract void synchronizeViewType(YolaineViewType viewType);
    
}