package com.yolaine.client.ui.util;


import com.yolaine.client.ui.util.event.YolaineCommonActionListener;
import com.yolaine.client.ui.util.event.YolaineCrudActionListener;
import com.yolaine.client.ui.util.event.YolaineFindActionListener;


public interface YolaineActionModel {
    
    public void addYolaineCrudActionListener(YolaineCrudActionListener listener);
    
    public void addYolaineFindActionListener(YolaineFindActionListener listener);
    
    public void addYolaineCommonActionListener(YolaineCommonActionListener listener);
    
    
    public void removeYolaineCrudActionListener(YolaineCrudActionListener listener);
    
    public void removeYolaineFindActionListener(YolaineFindActionListener listener);
    
    public void removeYolaineCommonActionListener(YolaineCommonActionListener listener);
    
    
    public YolaineCrudActionListener[] getYolaineCrudActionListeners();
    
    public YolaineFindActionListener[] getYolaineFindActionListeners();
    
    public YolaineCommonActionListener[] getYolaineCommonActionListeners();
    
}