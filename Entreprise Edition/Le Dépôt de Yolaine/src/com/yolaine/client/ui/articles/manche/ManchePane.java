package com.yolaine.client.ui.articles.manche;


import static com.yolaine.client.ui.util.YolaineViewType.*;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.ui.articles.couleur.event.CouleurAdapter;
import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.event.CouleurListener;
import com.yolaine.client.ui.articles.couleur.model.CouleurModel;
import com.yolaine.client.ui.articles.couleur.model.DefaultCouleurModel;
import com.yolaine.client.ui.articles.manche.event.MancheAdapter;
import com.yolaine.client.ui.articles.manche.event.MancheEventPropertyName;
import com.yolaine.client.ui.articles.manche.event.MancheListener;
import com.yolaine.client.ui.articles.manche.model.DefaultMancheModel;
import com.yolaine.client.ui.articles.manche.model.MancheModel;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleAdapter;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName;
import com.yolaine.client.ui.articles.typearticle.event.TypearticleListener;
import com.yolaine.client.ui.articles.typearticle.model.DefaultTypearticleModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleModel;

import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;


public class ManchePane
        extends
        YolaineComponentPane<MancheModel, MancheListener, MancheEventPropertyName> {
    
    private static final long serialVersionUID = -3566363661706630486L;
    

    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionField;
    
    
    public ManchePane() {
        super();
    }
    
    public ManchePane(MancheModel model) {
        super(model);
    }
    
    public ManchePane(YolaineViewType viewType) {
        super(viewType);
    }
    
    public ManchePane(MancheModel model, YolaineViewType viewType) {
        super(model, viewType);
    }
    
    
    @Override
    protected MancheModel createDefaultModel() {
        return new DefaultMancheModel();
    }
    
    @Override
    protected void initView() {
        idField = new JTextField();
        nameField = new JTextField();
        descriptionField = new JTextArea(3, 25);
        

        descriptionField.setWrapStyleWord(true);
        descriptionField.setLineWrap(true);
        JScrollPane scrollingDescription = new JScrollPane(descriptionField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        

        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        int row = 0;
        Insets insets = new Insets(2, 5, 2, 5);
        
       // add(new JLabel("Identifiant : "), new GridBagConstraints(0, row, 1, 1, 0.0,
        //        0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
        //        insets, 0, 0));
       // add(idField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
        //        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
        //        0, 0));
        
        add(new JLabel("Couleur : "), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
                0, 0));
        add(nameField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));
        
        add(new JLabel("Description : "), new GridBagConstraints(0, row, 1, 1,
                0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(scrollingDescription, new GridBagConstraints(1, row++, 1, 1, 1.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                insets, 0, 0));
        

        synchronizeViewType(getViewType());
    }
    
    @Override
    protected void installViewListeners() {
       
        nameField.addFocusListener(new FocusAdapter() {
            
            public void focusLost(FocusEvent evt) {
                Manche oldValue = model.getManche();
                String newValue = nameField.getText();
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setManche(new Manche(newValue));
                }
            }
            
        });
        
       
        
    }
    
    @Override
    protected void initViewValues() {        
        nameField.setText(model.getManche().getManche());       
    }
    
    @Override
    protected MancheListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }
    
    
    private class PropertyChangeHandler extends MancheAdapter {
        
       
        
        @Override
        public void mancheChanged(XSEvent<MancheEventPropertyName, String> evt) {
            String oldValue = nameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();
            
            if (!ObjectUtils.equals(oldValue, newValue)) {
                nameField.setText(newValue);
            }
        }
        
       
        
    }
    
     
    @Override
    protected void synchronizeViewType(YolaineViewType viewType) {
        idField.setEditable(viewType == CHERCHER || viewType == CREER
                || viewType == CHERCHER_OU_CREER);
        nameField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != MISE_A_JOUR);
        descriptionField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
    }
    
    @Override
    public String toString() {
        String text = "Manche";
        
        if (model.getManche().getManche() != null && !model.getManche().getManche().equals("")) {
            text += " - " + model.getManche().getManche();
        }
        
        return text;
    }
    
}