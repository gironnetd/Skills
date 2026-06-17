package com.yolaine.client.ui.transaction.remboursement;

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
import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueAdapter;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;
import com.yolaine.client.ui.transaction.banque.model.BanqueModel;
import com.yolaine.client.ui.transaction.banque.model.DefaultBanqueModel;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementAdapter;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementEventPropertyName;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementListener;
import com.yolaine.client.ui.transaction.remboursement.model.DefaultRemboursementModel;
import com.yolaine.client.ui.transaction.remboursement.model.RemboursementModel;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.BanqueComboItem;
import com.yolaine.client.ui.util.combo.TypePaiementComboItem;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;

public class RemboursementPane
extends
YolaineComponentPane<RemboursementModel, RemboursementListener, RemboursementEventPropertyName> {

	private static final long serialVersionUID = -3566363661706630486L;


	private JLabel dateRTextField;
	private JTextField dateremboursementField;
	private JLabel typeRTextField;
	private JTextField montantrembourseField;
	private JLabel montantrembourseTextField;
	private JLabel banqueRTextField;
	private JTextField typeremboursementTextField;
	private JTextField banqueRemboursementTextField;
	private JTextField numeroChequeRemboursementField;
	private JLabel emptyField;
	private JLabel emptyField1;
	private JLabel numeroCRTextField;
	private JComboBox typeremboursementField;
	private JComboBox banqueRemboursementField;


	public RemboursementPane() {
		super();
	}

	public RemboursementPane(RemboursementModel model) {
		super(model);
	}

	public RemboursementPane(YolaineViewType viewType) {
		super(viewType);
	}

	public RemboursementPane(RemboursementModel model, YolaineViewType viewType) {
		super(model, viewType);
	}


	@Override
	protected RemboursementModel createDefaultModel() {
		return new DefaultRemboursementModel(new Remboursement());
	}

	@Override
	protected void initView() {
		dateRTextField = new JLabel("Date de remboursement : ");
		numeroCRTextField = new JLabel("Numéro de Chèque : "); 		
		typeRTextField = new JLabel("Type de remboursement : "); 		
		banqueRTextField = new JLabel("Banque de remboursement : ");
		montantrembourseTextField = new JLabel("Montant remboursé : ");
		montantrembourseField = new JTextField();		
		dateremboursementField = new JTextField();		
		typeremboursementTextField = new JTextField();		
		numeroChequeRemboursementField = new JTextField();		
		banqueRemboursementTextField = new JTextField();			
		typeremboursementField = new JComboBox();		
		banqueRemboursementField = new JComboBox();

		try {
			List<TypePaiement> paiements = CatalogDelegate.trouverTypePaiements();

			typeremboursementField.addItem(null);
			for (TypePaiement typepaiement : paiements) {
				typeremboursementField.addItem(new TypePaiementComboItem(typepaiement));
			}
		} catch (Exception exc) {
			TypePaiementComboItem error = new TypePaiementComboItem(null);
			typeremboursementField.addItem(error);
		}				

		try {
			List<Banque> banques = CatalogDelegate.trouverBanques();

			banqueRemboursementField.addItem(null);
			for (Banque banque : banques) {
				banqueRemboursementField.addItem(new BanqueComboItem(banque));
			}
		} catch (Exception exc) {
			BanqueComboItem error = new BanqueComboItem(null);
			banqueRemboursementField.addItem(error);
		}	

		setLayout(new GridBagLayout());
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		int row = 0;
		Insets insets = new Insets(2, 5, 2, 5);

		add(dateRTextField, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));	
		add(montantrembourseTextField, new GridBagConstraints(1, row++, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));		
		add(dateremboursementField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	
		add(montantrembourseField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));		
		add(typeRTextField, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));			
		add(numeroCRTextField, new GridBagConstraints(1, row++, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));

		add(typeremboursementField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	
		add(typeremboursementTextField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));			
		add(numeroChequeRemboursementField, new GridBagConstraints(1, row++,1, 1, 1.0,
				0.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, insets,
				0, 0));


		synchronizeViewType(getViewType());
	}

	@Override
	protected void installViewListeners() {

		typeremboursementField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				TypePaiement oldValue = model.getTypRemboursement();
				TypePaiement newValue = ((TypePaiementComboItem) evt.getItem())
				.getTypepaiement();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTypeRemboursement(newValue);
				}
			}
		});

		dateremboursementField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getDateremboursement();
				String newValue = dateremboursementField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {					
					model.setDateremboursement(newValue);					                 
				}
			}            
		});

		montantrembourseField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getMontantRembourse();
				String newValue = null;

				try {
					String s = montantrembourseField.getText();
					newValue = s;					
					montantrembourseField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));

					montantrembourseField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					montantrembourseField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantRembourse(newValue);
				}
			}

		});

		montantrembourseField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getMontantRembourse();
				String newValue = montantrembourseField.getText();	       

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantRembourse(newValue);
				}
			}

		});

		banqueRemboursementField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Banque oldValue = model.getBanqueRemboursement();
				Banque newValue = ((BanqueComboItem) evt.getItem())
				.getBanque();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setBanqueRemboursement(newValue);
				}
			}
		});

		banqueRemboursementTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getBanqueRemboursement().getBanque();
				String newValue = banqueRemboursementTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setBanqueRemboursement(new Banque(newValue));
				}
			}

		});

		typeremboursementTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTypRemboursement().getTypePaiement();
				String newValue = typeremboursementTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTypeRemboursement(new TypePaiement(newValue));
				}
			}

		});





		numeroChequeRemboursementField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getNumeroChequeRemboursement();
				String newValue = null;

				try {
					String s = numeroChequeRemboursementField.getText();
					newValue = s;					
					numeroChequeRemboursementField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));

					numeroChequeRemboursementField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					numeroChequeRemboursementField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroChequeRemboursement(newValue);
				}
			}

		});

		numeroChequeRemboursementField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getNumeroChequeRemboursement();
				String newValue = numeroChequeRemboursementField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroChequeRemboursement(newValue);
				}
			}
		});	

	}

	@Override
	protected void initViewValues() {        


		//banqueRemboursementTextField.setText(model.getBanqueRemboursement().getBanque() == null 
		//		? " " : model.getBanqueRemboursement().getBanque());

		typeremboursementField.setSelectedItem(new TypePaiementComboItem(model.getTypRemboursement()));
		banqueRemboursementField.setSelectedItem(new BanqueComboItem(model.getBanqueRemboursement()));


		dateremboursementField.setText(model.getDateremboursement() == null ? "" : model
				.getDateremboursement());
	}

	@Override
	protected RemboursementListener createDefaultPropertyChangeHandler() {
		return new PropertyChangeHandler();
	}


	private class PropertyChangeHandler extends RemboursementAdapter {

		@Override
		public void typeremboursementChanged(
				XSEvent<RemboursementEventPropertyName, TypePaiement> evt) {
			TypePaiement oldValue = null;
			Object selectedItem = typeremboursementField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof TypePaiementComboItem) {
				oldValue = ((TypePaiementComboItem) selectedItem).getTypepaiement();
			}

			TypePaiement newValue = evt.getNewValueAsParameterizedType();

			if(newValue.getTypePaiement().equals("chèque")){
				numeroCRTextField.setVisible(true);
				numeroChequeRemboursementField.setVisible(true);

			}else{
				numeroCRTextField.setVisible(false);
				numeroChequeRemboursementField.setVisible(false);

			}

			if (!ObjectUtils.equals(oldValue, newValue)) {
				typeremboursementField.setSelectedItem(new TypePaiementComboItem(newValue));
			}
		}

		@Override
		public void dateremboursementChanged(XSEvent<RemboursementEventPropertyName, String> evt) {
			String oldValue = dateremboursementField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				dateremboursementField.setText(newValue);
			}
		}  
		
		@Override
		public void montantRembourseChanged(XSEvent<RemboursementEventPropertyName, String> evt){		 
			String oldValue = montantrembourseField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				montantrembourseTextField.setText(newValue);
			}
		}
	}

	@Override
	protected void synchronizeViewType(YolaineViewType viewType) {

		if(viewType == LIRE || viewType == SUPPRIMER || viewType == VENTE_ARTICLE
				|| viewType == MISE_A_JOUR ||  viewType == REMBOURSEMENT_ARTICLE){

		}

		if(viewType == MISE_A_JOUR){			
			dateRTextField.setVisible(false);			
			typeRTextField.setVisible(false);
			banqueRTextField.setVisible(false);			
			dateremboursementField.setVisible(false);			
			typeremboursementField.setVisible(false);
			dateremboursementField.setVisible(false);
			banqueRemboursementField.setVisible(false);			
			typeremboursementTextField.setVisible(false);			
			banqueRemboursementTextField.setVisible(false);			
			numeroCRTextField.setVisible(false);			
			numeroChequeRemboursementField.setVisible(false);
			montantrembourseField.setVisible(false);
			montantrembourseTextField.setVisible(false); 
		}		

		if (viewType == LIRE){				
			typeremboursementField.setVisible(false);			
			banqueRemboursementField.setVisible(false);				
			montantrembourseField.setVisible(true);
			montantrembourseTextField.setVisible(true); 				
			typeremboursementTextField.setVisible(true);			
			banqueRemboursementTextField.setVisible(false);				
			numeroCRTextField.setVisible(false);				
			numeroChequeRemboursementField.setVisible(false);
				if (model.getTypRemboursement().equals(null)){
					
				} else {
					if(model.getTypRemboursement().getTypePaiement().equals("chèque")){
						numeroCRTextField.setVisible(true);
						numeroChequeRemboursementField.setVisible(true);

					}else{
						numeroCRTextField.setVisible(false);
						numeroChequeRemboursementField.setVisible(false);

					}
				}
				
			}

		

		if (viewType == SUPPRIMER){				
			typeremboursementField.setVisible(false);			
			banqueRemboursementField.setVisible(false);				
			typeremboursementTextField.setVisible(true);			
			banqueRemboursementTextField.setVisible(true);				
			montantrembourseField.setVisible(false);
			montantrembourseTextField.setVisible(false); 
			if (model.getTypRemboursement().equals(null)){
				
			} else {
				if(model.getTypRemboursement().getTypePaiement().equals("chèque")){
					numeroCRTextField.setVisible(true);
					numeroChequeRemboursementField.setVisible(true);

				}else{
					numeroCRTextField.setVisible(false);
					numeroChequeRemboursementField.setVisible(false);

				}
			}
			
		}
		
		if (viewType == LIRE || viewType == SUPPRIMER){
			if(model.getRemboursement().getTyperemboursement().getTypePaiement().equals("chèque")){
				
				montantrembourseField.setText(model.getMontantRembourse());
				typeremboursementTextField.setText(model.getTypRemboursement().getTypePaiement() == null
						? " " : model.getTypRemboursement().getTypePaiement());
				numeroChequeRemboursementField.setText(model.getMontantRembourse() == null ? "" : model.getMontantRembourse());
			}else{
				montantrembourseField.setText(model.getMontantRembourse());
				typeremboursementTextField.setText(model.getTypRemboursement().getTypePaiement() == null
						? " " : model.getTypRemboursement().getTypePaiement());
			}				
		}

		if(viewType == VENTE_ARTICLE){							
			dateRTextField.setVisible(false);
			typeRTextField.setVisible(false);
			banqueRTextField.setVisible(false);				
			typeremboursementField.setVisible(false);				
			typeremboursementTextField.setVisible(false);
			dateremboursementField.setVisible(false);
			typeremboursementTextField.setVisible(false);
			dateremboursementField.setVisible(false);
			banqueRemboursementField.setVisible(false);
			banqueRemboursementTextField.setVisible(false);				
			numeroCRTextField.setVisible(false);				
			numeroChequeRemboursementField.setVisible(false);
			montantrembourseField.setVisible(false);
			montantrembourseTextField.setVisible(false); 				
		}

		if(viewType == REMBOURSEMENT_ARTICLE){				
			model.setDateremboursement(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));				
			dateRTextField.setVisible(true);			
			typeRTextField.setVisible(true);
			banqueRTextField.setVisible(true);				
			typeremboursementField.setVisible(true);				
			typeremboursementTextField.setVisible(false);
			dateremboursementField.setVisible(false);
			typeremboursementTextField.setVisible(false);
			dateremboursementField.setVisible(true);
			dateremboursementField.setEditable(false);
			banqueRemboursementField.setVisible(true);
			banqueRemboursementTextField.setVisible(false);				
			numeroCRTextField.setVisible(false);				
			numeroChequeRemboursementField.setVisible(false);
			montantrembourseField.setVisible(true);
			montantrembourseTextField.setVisible(true); 
		}

		if (viewType == CREER_DEPOT){				
			dateRTextField.setVisible(false);
			typeRTextField.setVisible(false);
			banqueRTextField.setVisible(false);

			typeremboursementField.setVisible(false);
			dateremboursementField.setVisible(false);
			banqueRemboursementField.setVisible(false);

			typeremboursementTextField.setVisible(false);			
			banqueRemboursementTextField.setVisible(false);

			numeroCRTextField.setVisible(false);

			numeroChequeRemboursementField.setVisible(false);
			montantrembourseField.setVisible(false);
			montantrembourseTextField.setVisible(false); 
		}


		typeremboursementTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
		banqueRemboursementTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE && viewType != MISE_A_JOUR_DEPOT);
		montantrembourseField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE );
		typeremboursementField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE);			
		banqueRemboursementField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE);			
		dateremboursementField.setEditable(viewType != LIRE && viewType != SUPPRIMER);					
		numeroChequeRemboursementField.setEditable(viewType != LIRE && viewType != SUPPRIMER);		
	}

	@Override
	public String toString() {		
		return null;
	}

}