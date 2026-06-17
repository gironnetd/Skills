package com.yolaine.client.ui.articles.marque;


import static com.yolaine.client.ui.util.YolaineViewType.*;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.delegate.CatalogDelegate;

import com.yolaine.client.ui.articles.marque.event.MarqueAdapter;
import com.yolaine.client.ui.articles.marque.event.MarqueEventPropertyName;
import com.yolaine.client.ui.articles.marque.event.MarqueListener;
import com.yolaine.client.ui.articles.marque.model.DefaultMarqueModel;
import com.yolaine.client.ui.articles.marque.model.MarqueModel;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.CategoryComboItem;
import com.yolaine.entity.catalogue.Categorie;


public class MarquePane
        extends
        YolaineComponentPane<MarqueModel, MarqueListener, MarqueEventPropertyName> {
    
    private static final long serialVersionUID = 8616843607600688725L;
    

    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JComboBox categoryCombo;
    
    
    public MarquePane() {
        super();
        
        this.setSize(new Dimension(300,150));
    }
    
    public MarquePane(MarqueModel model) {
        super(model);
        this.setSize(new Dimension(300,150));
    
    }
    
    public MarquePane(YolaineViewType viewType) {
        super(viewType);
        this.setSize(new Dimension(300,150));
    }
    
    public MarquePane(MarqueModel model, YolaineViewType viewType) {
        super(model, viewType);
        this.setSize(new Dimension(300,150));
    }
    
    
    @Override
    protected MarqueModel createDefaultModel() {
        return new DefaultMarqueModel();
    }
    
    @Override
    protected void initView() {
        idField = new JTextField();
        nameField = new JTextField();
        descriptionField = new JTextArea(3, 25);
        categoryCombo = new JComboBox();
        

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
        
        add(new JLabel("Nom : "), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
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
        idField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent evt) {
                Long oldValue = model.getIdentifierToFind();
                Long newValue = null;
                
                try {
                    newValue = new Long(idField.getText());
                    idField.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    idField.setForeground(Color.red);
                }
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setIdentifierToFind(newValue);
                }
            }
            
        });
        
        idField.addFocusListener(new FocusAdapter() {
            
            public void focusLost(FocusEvent evt) {
                Long oldValue = model.getIdentifierToFind();
                Long newValue = null;
                
                try {
                    newValue = new Long(idField.getText());
                    idField.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    idField.setForeground(Color.red);
                }
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setIdentifierToFind(newValue);
                }
            }
            
        });
        
        nameField.addFocusListener(new FocusAdapter() {
            
            public void focusLost(FocusEvent evt) {
                String oldValue = model.getName();
                String newValue = nameField.getText();
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setName(newValue);
                }
            }
            
        });
        
        descriptionField.addFocusListener(new FocusAdapter() {
            
            public void focusLost(FocusEvent evt) {
                String oldValue = model.getDescription();
                String newValue = descriptionField.getText();
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setDescription(newValue);
                }
            }
            
        });
        
        categoryCombo.addItemListener(new java.awt.event.ItemListener() {
            
            public void itemStateChanged(ItemEvent evt) {
                Categorie oldValue = model.getCategory();
                Categorie newValue = ((CategoryComboItem) evt.getItem())
                        .getCategory();
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setCategory(newValue);
                }
            }
            
        });
        
    }
    
    @Override
    protected void initViewValues() {
       
        nameField.setText(model.getName());
        descriptionField.setText(model.getDescription());
        descriptionField.setCaretPosition(0);        
    }
    
    @Override
    protected MarqueListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }
    
    
    private class PropertyChangeHandler extends MarqueAdapter {
        
        @Override
        public void identifierChanged(
                XSEvent<MarqueEventPropertyName, Long> evt) {
            Long oldValue = null;
            
            try {
                oldValue = new Long(idField.getText());
            } catch (Exception exc) {
            }
            
            Long newValue = evt.getNewValueAsParameterizedType();
            
            if (!ObjectUtils.equals(oldValue, newValue)) {
                idField.setText(newValue == null ? null : newValue.toString());
            }
        }
        
        @Override
        public void nameChanged(XSEvent<MarqueEventPropertyName, String> evt) {
            String oldValue = nameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();
            
            if (!ObjectUtils.equals(oldValue, newValue)) {
                nameField.setText(newValue);
            }
        }
        
        @Override
        public void descriptionChanged(
                XSEvent<MarqueEventPropertyName, String> evt) {
            String oldValue = descriptionField.getText();
            String newValue = evt.getNewValueAsParameterizedType();
            
            if (!ObjectUtils.equals(oldValue, newValue)) {
                descriptionField.setText(newValue);
            }
        }
        
        @Override
        public void categoryChanged(
                XSEvent<MarqueEventPropertyName, Categorie> evt) {
            Categorie oldValue = null;
            Object selectedItem = categoryCombo.getSelectedItem();
            
            if (selectedItem != null
                    && selectedItem instanceof CategoryComboItem) {
                oldValue = ((CategoryComboItem) selectedItem).getCategory();
            }
            
            Categorie newValue = evt.getNewValueAsParameterizedType();
            
            if (!ObjectUtils.equals(oldValue, newValue)) {
                categoryCombo.setSelectedItem(new CategoryComboItem(newValue));
            }
        }
        
    }
    
    
    @Override
    protected void synchronizeViewType(YolaineViewType viewType) {
        idField.setEditable(viewType == CHERCHER || viewType == CREER
                || viewType == CHERCHER_OU_CREER);
        nameField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != MISE_A_JOUR && viewType != MISE_A_JOUR_OU_SUPPRIMER);
        if (viewType == LIRE || viewType == SUPPRIMER){
			Color c = new Color(240,240,240);
			descriptionField.setBackground(c);
		}
        descriptionField.setEditable(viewType != LIRE && viewType != SUPPRIMER);        
    }
    
    @Override
    public String toString() {
        String text = "Marque";
        
        if (model.getName() != null && !model.getName().equals("")) {
            text += " - " + model.getName();
        }
        
        return text;
    }
    
}