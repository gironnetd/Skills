package com.yolaine.client.ui.transaction.paiement;

import static com.yolaine.client.ui.util.YolaineViewType.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueAdapter;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;
import com.yolaine.client.ui.transaction.banque.model.BanqueModel;
import com.yolaine.client.ui.transaction.banque.model.DefaultBanqueModel;
import com.yolaine.client.ui.transaction.paiement.event.PaiementAdapter;
import com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.event.PaiementListener;
import com.yolaine.client.ui.transaction.paiement.model.DefaultPaiementModel;
import com.yolaine.client.ui.transaction.paiement.model.PaiementModel;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.BanqueComboItem;
import com.yolaine.client.ui.util.combo.IdentiteComboItem;
import com.yolaine.client.ui.util.combo.TypePaiementComboItem;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.TypeIdentite;

public class PaiementPane
        extends
        YolaineComponentPane<PaiementModel, PaiementListener, PaiementEventPropertyName> {
    
    private static final long serialVersionUID = -3566363661706630486L;
    

    private JLabel dateVTextField;
    private JLabel typeidentitePTextField;
    private JLabel typePTextField;
    private JLabel banquePTextField;    
	private JLabel prixventereelTextField;
	private JLabel numeroBTextField;
	private JLabel numeroCPTextField;
	private JComboBox typepaiementField;
	private JComboBox typeidentiteField;
	private JComboBox banquePaiementField;
	private JTextField dateventeField;
	private JTextField typeidentiteTextField;		
	private JTextField typepaiementTextField;
	private JTextField banquePaiementTextField;
	private JTextField numerobanqueField;
	private JTextField prixventereelField;
	private JTextField numeroChequePaiementField;
	
	
    public PaiementPane() {
        super();
    }
    
    public PaiementPane(PaiementModel model) {
        super(model);
    }
    
    public PaiementPane(YolaineViewType viewType) {
        super(viewType);
    }
    
    public PaiementPane(PaiementModel model, YolaineViewType viewType) {
        super(model, viewType);
    }
    
    
    @Override
    protected PaiementModel createDefaultModel() {
        return new DefaultPaiementModel(new Paiement());
    }
    
    @Override
    protected void initView() {
        
    	dateVTextField = new JLabel("Date de vente : ");
        prixventereelField = new JTextField();
		prixventereelTextField = new JLabel("Prix de vente réel : ");
		numeroBTextField = new JLabel("Numéro de Banque : ");
		numeroCPTextField = new JLabel("Numéro de Chèque : ");
		typeidentitePTextField = new JLabel("Type d' identité : ");		
		typePTextField = new JLabel("Type de paiement : ");
		banquePTextField = new JLabel("Banque de paiement : ");
		typeidentiteTextField = new JTextField();
		typeidentiteField = new JComboBox();
		typepaiementTextField = new JTextField();
		banquePaiementTextField = new JTextField();
		numeroChequePaiementField = new JTextField();
		numerobanqueField = new JTextField();
		banquePaiementField = new JComboBox();
		typepaiementField = new JComboBox();
		dateventeField = new JTextField();       
        
		try {
			List<TypePaiement> paiements = CatalogDelegate.trouverTypePaiements();

			typepaiementField.addItem(null);
			for (TypePaiement typepaiement : paiements) {
				typepaiementField.addItem(new TypePaiementComboItem(typepaiement));
			}
		} catch (Exception exc) {
			TypePaiementComboItem error = new TypePaiementComboItem(null);
			typepaiementField.addItem(error);
		}	
		
		try {
			List<Banque> banques = CatalogDelegate.trouverBanques();

			banquePaiementField.addItem(null);
			for (Banque banque : banques) {
				banquePaiementField.addItem(new BanqueComboItem(banque));
			}
		} catch (Exception exc) {
			BanqueComboItem error = new BanqueComboItem(null);
			banquePaiementField.addItem(error);
		}
		
		try {
			List<TypeIdentite> typeidentites = ClientDelegate.trouverTypeIdentites();

			for (TypeIdentite typeidentite : typeidentites) {
				typeidentiteField.addItem(new IdentiteComboItem(typeidentite));
			}
		} catch (Exception exc) {
			IdentiteComboItem error = new IdentiteComboItem(null);
			typeidentiteField.addItem(error);
		}
		
		
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        int row = 0;
        Insets insets = new Insets(2, 5, 2, 5);
        
       
        add(dateVTextField, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));
		add(prixventereelTextField, new GridBagConstraints(1, row++, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));
		add(dateventeField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	
		add(prixventereelField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(typePTextField, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));
		add(banquePTextField, new GridBagConstraints(1, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));		
		add(typeidentitePTextField, new GridBagConstraints(1, row++, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));	
		
		
		add(typepaiementField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(typepaiementTextField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));		
		add(banquePaiementField, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	
		add(banquePaiementTextField, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(typeidentiteField, new GridBagConstraints(1, row,1, 1, 1.0,
				0.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(typeidentiteTextField, new GridBagConstraints(1, row ++,1, 1, 1.0,
				0.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(numeroBTextField, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));	
		add(numeroCPTextField, new GridBagConstraints(1, row++, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));
		add(numerobanqueField, new GridBagConstraints(0, row,1, 1, 1.0,
				0.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(numeroChequePaiementField, new GridBagConstraints(1, row++,1, 1, 1.0,
				0.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(new JLabel(), new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0)); 

        synchronizeViewType(getViewType());
    }
    
    @Override
    protected void installViewListeners() {
       
    	typepaiementField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				TypePaiement oldValue = model.getTypePaiement();
				TypePaiement newValue = ((TypePaiementComboItem) evt.getItem())
				.getTypepaiement();
				
				//System.out.println("addItemListener()");
				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTypePaiement(newValue);					
				}
				//if(newValue.equals(new TypePaiement("chèque"))){
				//	typepaiementField.setVisible(false);
				//}
			}
		});
        
    	dateventeField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getDatevente();
				String newValue = dateventeField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {					
					model.setDatevente(newValue);					                 
				}
			}            
		});
    	
    	prixventereelField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = null;
				String newValue = null;

				try {
					String s = prixventereelField.getText();
					newValue = s;					
					prixventereelField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));

					prixventereelField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					prixventereelField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPrixVenteReel(newValue);
				}
			}

		});


		prixventereelField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getPrixVenteReel();
				String newValue = prixventereelField.getText();	       

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPrixVenteReel(newValue);
				}
			}

		});

		banquePaiementField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Banque oldValue = model.getBanquePaiement();
				Banque newValue = ((BanqueComboItem) evt.getItem())
				.getBanque();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setBanquePaiement(newValue);

				}
			}
		});

		typepaiementTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTypePaiement().getTypePaiement();
				String newValue = typepaiementTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTypePaiement(new TypePaiement(newValue));
				}
			}

		});

		banquePaiementTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getBanquePaiement().getBanque();
				String newValue = banquePaiementTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setBanquePaiement(new Banque(newValue));
				}
			}

		});

		typeidentiteField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				TypeIdentite oldValue = model.getTypeidentite();
				TypeIdentite newValue = ((IdentiteComboItem) evt.getItem())
				.getTypeidentite();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTypeidentite(newValue);
				}
			}

		});

		typeidentiteTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTypeidentite().getTypeIdentite();
				String newValue = typeidentiteTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTypeidentite(new TypeIdentite(newValue));
				}
			}

		});	

		numerobanqueField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getNumeroBanque();
				String newValue = null;

				try {
					String s = numerobanqueField.getText();
					newValue = s;					
					numerobanqueField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));

					numerobanqueField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					numerobanqueField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroBanque(newValue);
				}
			}

		});

		numerobanqueField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getNumeroBanque();
				String newValue = numerobanqueField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroBanque(newValue);
				}
			}
		});	

		numeroChequePaiementField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getNumeroChequePaiement();
				String newValue = null;

				try {
					String s = numeroChequePaiementField.getText();
					newValue = s;					
					numeroChequePaiementField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));

					numeroChequePaiementField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					numeroChequePaiementField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroChequePaiement(newValue);
				}
			}

		});

		numeroChequePaiementField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getNumeroChequePaiement();
				String newValue = numeroChequePaiementField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroChequePaiement(newValue);
				}
			}
		});	
        
    }
    
    @Override
    protected void initViewValues() { 
    	
    	typepaiementField.setSelectedItem(new TypePaiementComboItem(model.getTypePaiement()));
    	
    	
    	
    	banquePaiementField.setSelectedItem(new BanqueComboItem(model.getBanquePaiement()));
    		
		prixventereelField.setText(model.getPrixVenteReel());
		dateventeField.setText(model.getDatevente() == null ? "" : model
				.getDatevente());
		numerobanqueField.setText(model.getNumeroBanque() == null ? "" : model.getNumeroBanque());		
		typeidentiteField.setSelectedItem(new IdentiteComboItem(model.getTypeidentite()));
			
		numerobanqueField.setText(model.getNumeroBanque() == null ? "" : model.getNumeroBanque());				
		numeroChequePaiementField.setText(model.getNumeroChequePaiement() == null ? "" : model.getNumeroChequePaiement());
    }
    
    @Override
    protected PaiementListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }
    
    
    private class PropertyChangeHandler extends PaiementAdapter {
        
    	@Override
		public void typeIdentiteChanged(
				XSEvent<PaiementEventPropertyName, TypeIdentite> evt) {
			TypeIdentite oldValue = null;
			Object selectedItem = typeidentiteField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof IdentiteComboItem) {
				oldValue = ((IdentiteComboItem) selectedItem).getTypeidentite();
			}

			TypeIdentite newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				typeidentiteField.setSelectedItem(new IdentiteComboItem(newValue));
				
			}
		}
    	
    	
    	@Override
		public void typepaiementChanged(
				XSEvent<PaiementEventPropertyName, TypePaiement> evt) {
			TypePaiement oldValue = null;
			Object selectedItem = typepaiementField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof TypePaiementComboItem) {
				oldValue = ((TypePaiementComboItem) selectedItem).getTypepaiement();

			}

			TypePaiement newValue = evt.getNewValueAsParameterizedType();
			System.out.println("typepaiementChanged()" + newValue.getTypePaiement());
			if(newValue.getTypePaiement().equals("chèque")){
				banquePTextField.setVisible(false);
				banquePaiementField.setVisible(false);				
				typeidentitePTextField.setVisible(true);
				typeidentiteField.setVisible(true);
				if (viewType == VENTE_ARTICLE){
					typeidentiteTextField.setVisible(false);
					
				}else{
					typeidentiteTextField.setVisible(true);
				}
				numeroBTextField.setVisible(true);
				numerobanqueField.setVisible(true);
				numeroCPTextField.setVisible(true);
				numeroChequePaiementField.setVisible(true);
			}else if (newValue.getTypePaiement().equals("") || newValue.getTypePaiement().equals("espèce")){
				banquePTextField.setVisible(false);
				banquePaiementField.setVisible(false);
				typeidentitePTextField.setVisible(false);
				typeidentiteField.setVisible(false);
				typeidentiteTextField.setVisible(false);
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				numeroCPTextField.setVisible(false);
				numeroChequePaiementField.setVisible(false);
			}else{
				banquePTextField.setVisible(true);
				banquePaiementField.setVisible(true);
				typeidentitePTextField.setVisible(false);
				typeidentiteField.setVisible(false);
				typeidentiteTextField.setVisible(false);
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				numeroCPTextField.setVisible(false);
				numeroChequePaiementField.setVisible(false);
			}
			if (!ObjectUtils.equals(oldValue, newValue)) {
				typepaiementField.setSelectedItem(new TypePaiementComboItem(newValue));
				typepaiementTextField.setText(newValue.getTypePaiement());
			}
		}
    	
    	@Override
		public void dateventeChanged(XSEvent<PaiementEventPropertyName, String> evt) {
			String oldValue = dateventeField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				dateventeField.setText(newValue);
			}
		} 
    	
    	@Override
    	public void numeroBanqueChanged(XSEvent<PaiementEventPropertyName, String> evt){		
    		String oldValue = numerobanqueField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				numerobanqueField.setText(newValue);
			}
    	}

    	@Override
    	public void numeroChequePaiementChanged(XSEvent<PaiementEventPropertyName, String> evt){	    	
    		String oldValue = numeroChequePaiementField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				numeroChequePaiementField.setText(newValue);
			}
    	}    	

    	@Override
    	public void banquePaiementChanged(XSEvent<PaiementEventPropertyName, Banque> evt){	    	
    		Banque oldValue = null;
			Object selectedItem = banquePaiementField.getSelectedItem();
			
			if (selectedItem != null
					&& selectedItem instanceof BanqueComboItem) {
				oldValue = ((BanqueComboItem) selectedItem).getBanque();
			}
			
			Banque newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				banquePaiementField.setSelectedItem(new BanqueComboItem(newValue));
				banquePaiementTextField.setText(newValue.getBanque());
			}
    	}     	
    	
    	@Override
    	public void prixVenteReelChanged(XSEvent<PaiementEventPropertyName, String> evt){		 
    		String oldValue = prixventereelField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				prixventereelField.setText(newValue);
			}
    	}
    }    
     
    @Override
    protected void synchronizeViewType(YolaineViewType viewType) {				
    	    System.out.println(" paiementPane : " + viewType);
			if (viewType == LIRE){	
				typePTextField.setVisible(true);
				   	
		    	if(model.getPaiement().getTypepaiement().getTypePaiement().equals("chèque")){
		    		System.out.println("type chèque");
		    			
		    		typepaiementField.setVisible(false);
		    		banquePaiementField.setVisible(false);
		    		banquePTextField.setVisible(false);
					banquePaiementTextField.setVisible(false);
					
					typeidentitePTextField.setVisible(true);
					typeidentiteField.setVisible(false);
					typePTextField.setVisible(true);
					numeroBTextField.setVisible(true);
					numerobanqueField.setVisible(true);
					numeroCPTextField.setVisible(true);
					numeroChequePaiementField.setVisible(true);
				}else if (model.getPaiement().getTypepaiement().getTypePaiement().equals("espèce")){
					banquePTextField.setVisible(false);					
					banquePaiementField.setVisible(false);
					typepaiementField.setVisible(false);
					banquePaiementTextField.setVisible(false);
					typeidentitePTextField.setVisible(false);
					typeidentiteField.setVisible(false);
					typeidentiteTextField.setVisible(false);
					numeroBTextField.setVisible(false);
					numerobanqueField.setVisible(false);
					numeroCPTextField.setVisible(false);
					numeroChequePaiementField.setVisible(false);
				}else{
					banquePTextField.setVisible(true);
					typepaiementField.setVisible(false);
					banquePaiementField.setVisible(false);					
					typeidentitePTextField.setVisible(false);
					typeidentiteField.setVisible(false);
					typeidentiteTextField.setVisible(false);
					numeroBTextField.setVisible(false);
					numerobanqueField.setVisible(false);
					numeroCPTextField.setVisible(false);
					numeroChequePaiementField.setVisible(false);
				}
			}

			if(viewType == LIRE || viewType == SUPPRIMER){	    		
	    		if(model.getPaiement().getTypepaiement().getTypePaiement().equals("chèque")){
	    			typepaiementTextField.setText(model.getTypePaiement().getTypePaiement() == null
	        				? "" : model.getTypePaiement().getTypePaiement());
	        		typeidentiteTextField.setText(model.getTypeidentite().getTypeIdentite() == null
	        				 ? "" :	model.getTypeidentite().getTypeIdentite());
	        	}else if (model.getPaiement().getTypepaiement().getTypePaiement().equals("espèce")){
	        		typepaiementTextField.setText(model.getTypePaiement().getTypePaiement() == null
	        				? "" : model.getTypePaiement().getTypePaiement());
	    		}else{
	    			typepaiementTextField.setText(model.getTypePaiement().getTypePaiement() == null
	        				? "" : model.getTypePaiement().getTypePaiement());
	    			banquePaiementTextField.setText(model.getBanquePaiement().getBanque() == null
	        				 ? "" : model.getBanquePaiement().getBanque());	
	    		}
	    	}	
			
			if (viewType == SUPPRIMER){	
				typePTextField.setVisible(true);
				  	
		    	if(model.getPaiement().getTypepaiement().getTypePaiement().equals("chèque")){
		    		System.out.println("type chèque");
		    		
		    		typepaiementField.setVisible(false);
		    		banquePaiementField.setVisible(false);
		    		banquePTextField.setVisible(false);
					banquePaiementField.setVisible(false);				
					typeidentitePTextField.setVisible(true);
					typeidentiteField.setVisible(false);					
					numeroBTextField.setVisible(true);
					numerobanqueField.setVisible(true);
					numeroCPTextField.setVisible(true);
					numeroChequePaiementField.setVisible(true);
				}else if (model.getPaiement().getTypepaiement().getTypePaiement().equals("espèce")){
					banquePTextField.setVisible(false);					
					banquePaiementField.setVisible(false);
					typepaiementField.setVisible(false);
					banquePaiementTextField.setVisible(false);
					typeidentitePTextField.setVisible(false);
					typeidentiteField.setVisible(false);
					typeidentiteTextField.setVisible(false);
					numeroBTextField.setVisible(false);
					numerobanqueField.setVisible(false);
					numeroCPTextField.setVisible(false);
					numeroChequePaiementField.setVisible(false);
				}else{
					banquePTextField.setVisible(true);
					typepaiementField.setVisible(false);
					banquePaiementField.setVisible(false);					
					typeidentitePTextField.setVisible(false);
					typeidentiteField.setVisible(false);
					typeidentiteTextField.setVisible(false);
					numeroBTextField.setVisible(false);
					numerobanqueField.setVisible(false);
					numeroCPTextField.setVisible(false);
					numeroChequePaiementField.setVisible(false);
				}
			}

			if(viewType == VENTE_ARTICLE){
				model.setDatevente(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				
				dateVTextField.setVisible(true);
				typePTextField.setVisible(true);
				banquePTextField.setVisible(false);
				
				dateventeField.setVisible(true);
				dateventeField.setEditable(true);
				typepaiementField.setVisible(true);
				banquePaiementField.setVisible(false);
				typepaiementTextField.setVisible(false);
				banquePaiementTextField.setVisible(false);				
				typeidentitePTextField.setVisible(false);
				typeidentiteField.setVisible(false);
				typeidentiteTextField.setVisible(false);
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
			
				numeroChequePaiementField.setVisible(false);		
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				numeroCPTextField.setVisible(false);
				
				
			}

			if(viewType == REMBOURSEMENT_ARTICLE){
				
				
				prixventereelField.setVisible(false);
				prixventereelTextField.setVisible(false);
				
				dateVTextField.setVisible(false);
				typePTextField.setVisible(false);
				banquePTextField.setVisible(false);
				
				dateventeField.setVisible(false);			
				typepaiementField.setVisible(false);
				banquePaiementField.setVisible(false);
				typepaiementTextField.setVisible(false);
				banquePaiementTextField.setVisible(false);
				
				banquePaiementField.setVisible(false);
				
				typeidentitePTextField.setVisible(false);
				typeidentiteField.setVisible(false);
				typeidentiteTextField.setVisible(false);
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				
				numeroChequePaiementField.setVisible(false);		
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				numeroCPTextField.setVisible(false);
				
			}

			if (viewType == CREER_DEPOT){
				prixventereelField.setVisible(true);
				prixventereelTextField.setVisible(true);
				
				dateVTextField.setVisible(true);
				typePTextField.setVisible(true);
				banquePTextField.setVisible(false);
				
				dateventeField.setVisible(true);
				typepaiementField.setVisible(true);
				banquePaiementField.setVisible(false);
				
				typepaiementTextField.setVisible(false);
				banquePaiementTextField.setVisible(false);
						
				typeidentitePTextField.setVisible(false);
				typeidentiteField.setVisible(false);
				typeidentiteTextField.setVisible(false);
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				
				numeroChequePaiementField.setVisible(false);		
				numeroBTextField.setVisible(false);
				numerobanqueField.setVisible(false);
				numeroCPTextField.setVisible(false);
			}
			

			
			typepaiementTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			banquePaiementTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			
			typepaiementField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != REMBOURSEMENT_ARTICLE);		
			typeidentiteField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != REMBOURSEMENT_ARTICLE);
			typeidentiteTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != REMBOURSEMENT_ARTICLE);		
			numerobanqueField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != REMBOURSEMENT_ARTICLE);
			
			banquePaiementField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != REMBOURSEMENT_ARTICLE);
			
			dateventeField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
			
			prixventereelField.setEditable(viewType != LIRE && viewType != SUPPRIMER);			
			numeroChequePaiementField.setEditable(viewType != LIRE && viewType != SUPPRIMER);			
			
    	
    }
    
    @Override
    public String toString() {        
        return null;
    }
    
}