package com.yolaine.client.ui.util;


import static com.yolaine.client.ui.util.YolaineUIConstants.DEFAULT_BG_COLOR;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.yolaine.client.ui.util.event.YolaineCommonActionListener;
import com.yolaine.client.ui.util.event.YolaineCrudActionListener;
import com.yolaine.client.ui.util.event.YolaineFindActionListener;
import com.yolaine.client.ui.util.event.YolaineViewerEvent;
import com.yolaine.client.ui.util.event.YolaineViewerListener;


public abstract class YolaineCrudFrame<P extends YolaineComponentPane> extends
        YolaineInternalFrame implements YolaineViewerListener,
        YolaineCrudActionListener, YolaineFindActionListener,
        YolaineCommonActionListener, YolaineActionModel {
    
    protected final P mainPane;
    protected final YolaineActionPane actionPane;
    
    protected JLabel labelTitle;
    
    
    public YolaineCrudFrame(final P mainPane) {
        this.mainPane = mainPane;
        actionPane = new YolaineActionPane(mainPane.getViewType());
        
        initComponent();
        
        mainPane.addYolaineViewListener(this);
        
        actionPane.addYolaineCrudActionListener(this);
        actionPane.addYolaineFindActionListener(this);
        actionPane.addYolaineCommonActionListener(this);
    }
    
    private void initComponent() {
        JPanel globalPane = new JPanel();
        globalPane.setLayout(new BorderLayout());
        
        JPanel mainBodyPane = new JPanel();
        mainBodyPane.setLayout(new BorderLayout());
        mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));
        
        JPanel mainContentPane = new JPanel();
        mainContentPane.setLayout(new BorderLayout());
        
        labelTitle = new JLabel();
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", 1, 18));
        labelTitle.setBackground(Color.white);
        labelTitle.setOpaque(true);
        
        initTitle(mainPane.getViewType());
        
        mainContentPane.add(labelTitle, BorderLayout.NORTH);
        
        JPanel mainBorderedPane = new JPanel();
        mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
        mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
        mainBorderedPane.setLayout(new BorderLayout());
        mainBorderedPane.add(mainPane, BorderLayout.CENTER);
        
        mainContentPane.add(mainBorderedPane, BorderLayout.CENTER);
        
        mainBodyPane.add(mainContentPane, BorderLayout.CENTER);
        globalPane.add(mainBodyPane, BorderLayout.CENTER);
        
        JPanel actionBodyPane = new JPanel();
        actionBodyPane.setLayout(new BorderLayout());
        actionBodyPane.setBorder(BorderFactory
                .createLineBorder(Color.white, 10));
        actionBodyPane.add(actionPane, BorderLayout.SOUTH);
        
        globalPane.add(actionBodyPane, BorderLayout.SOUTH);
        
        setInnerPane(globalPane);
    }
    
    
    public void yolaineViewChanged(YolaineViewerEvent evt) {
        actionPane.setViewType(evt.getNewValue());
        
        initTitle(evt.getNewValue());
    }
    
    
    public void addYolaineCrudActionListener(YolaineCrudActionListener listener) {
        actionPane.addYolaineCrudActionListener(listener);
    }
    
    public void addYolaineFindActionListener(YolaineFindActionListener listener) {
        actionPane.addYolaineFindActionListener(listener);
    }
    
    public void addYolaineCommonActionListener(YolaineCommonActionListener listener) {
        actionPane.addYolaineCommonActionListener(listener);
    }    
    
    public void removeYolaineCrudActionListener(YolaineCrudActionListener listener) {
        actionPane.removeYolaineCrudActionListener(listener);
    }
    
    public void removeYolaineFindActionListener(YolaineFindActionListener listener) {
        actionPane.removeYolaineFindActionListener(listener);
    }
    
    public void removeYolaineCommonActionListener(YolaineCommonActionListener listener) {
        actionPane.removeYolaineCommonActionListener(listener);
    }
    
    
    public YolaineCrudActionListener[] getYolaineCrudActionListeners() {
        return actionPane.getYolaineCrudActionListeners();
    }
    
    public YolaineFindActionListener[] getYolaineFindActionListeners() {
        return actionPane.getYolaineFindActionListeners();
    }
    
    public YolaineCommonActionListener[] getYolaineCommonActionListeners() {
        return actionPane.getYolaineCommonActionListeners();
    }
    
    
    public void closeActionPerformed(EventObject evt) {
        dispose();
    }
    
    
    protected void initTitle(YolaineViewType viewType) {
        labelTitle.setText(mainPane.toString() + " (" + viewType.getLabel()
                + ")");
        
        setTitle(mainPane.toString() + " (" + viewType.getLabel() + ")");
    }
    
}