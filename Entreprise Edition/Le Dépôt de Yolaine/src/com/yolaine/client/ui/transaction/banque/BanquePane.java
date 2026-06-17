package com.yolaine.client.ui.transaction.banque;

import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueAdapter;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;
import com.yolaine.client.ui.transaction.banque.model.BanqueModel;
import com.yolaine.client.ui.transaction.banque.model.DefaultBanqueModel;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.transaction.Banque;

public class BanquePane
        extends
        YolaineComponentPane<BanqueModel, BanqueListener, BanqueEventPropertyName> {
    
    private static final long serialVersionUID = -3566363661706630486L;
    

    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionField;
    
    
    public BanquePane() {
        super();
    }
    
    public BanquePane(BanqueModel model) {
        super(model);
    }
    
    public BanquePane(YolaineViewType viewType) {
        super(viewType);
    }
    
    public BanquePane(BanqueModel model, YolaineViewType viewType) {
        super(model, viewType);
    }
    
    
    @Override
    protected BanqueModel createDefaultModel() {
        return new DefaultBanqueModel();
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
        
        add(new JLabel("Banque : "), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
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
            	Banque oldValue = model.getBanque();
                String newValue = nameField.getText();
                
                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setBanque(new Banque(newValue));
                }
            }
            
        });
        
       
        
    }
    
    @Override
    protected void initViewValues() {        
        nameField.setText(model.getBanque().getBanque());       
    }
    
    @Override
    protected BanqueListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }
    
    
    private class PropertyChangeHandler extends BanqueAdapter {
        
       
        
        @Override
        public void banqueChanged(XSEvent<BanqueEventPropertyName, String> evt) {
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
        
        if (model.getBanque().getBanque() != null && !model.getBanque().getBanque().equals("")) {
            text += " - " + model.getBanque().getBanque();
        }
        
        return text;
    }
    
}