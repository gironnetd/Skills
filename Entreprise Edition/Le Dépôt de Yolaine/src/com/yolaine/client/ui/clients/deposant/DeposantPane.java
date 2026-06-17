package com.yolaine.client.ui.clients.deposant;

import static org.apache.commons.lang.time.DateFormatUtils.format;
import static org.apache.commons.lang.time.DateUtils.parseDate;


import static com.yolaine.client.ui.util.YolaineUIConstants.DATE_PATTERN;
import static com.yolaine.client.ui.util.YolaineUIConstants.DATE_PATTERNS;
import static com.yolaine.client.ui.util.YolaineViewType.*;
import static com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName.*;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.clients.client.event.ClientAdapter;
import com.yolaine.client.ui.clients.client.event.ClientEventPropertyName;
import com.yolaine.client.ui.clients.client.event.ClientListener;
import com.yolaine.client.ui.clients.client.model.ClientModel;
import com.yolaine.client.ui.clients.client.model.DefaultClientModel;
import com.yolaine.client.ui.clients.deposant.event.DeposantAdapter;
import com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName;
import com.yolaine.client.ui.clients.deposant.event.DeposantListener;
import com.yolaine.client.ui.clients.deposant.model.DefaultDeposantModel;
import com.yolaine.client.ui.clients.deposant.model.DeposantModel;
import com.yolaine.client.ui.commun.adresse.AdressePane;

import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.CategoryComboItem;
import com.yolaine.client.ui.util.combo.CiviliteComboItem;
import com.yolaine.client.ui.util.combo.ClientComboItem;
import com.yolaine.client.ui.util.combo.IdentiteComboItem;
import com.yolaine.client.ui.util.combo.NomComboItem;
import com.yolaine.client.ui.util.combo.PrenomComboItem;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public class DeposantPane
extends
YolaineComponentPane<DeposantModel, DeposantListener, DeposantEventPropertyName> {

	private static final long serialVersionUID = 1418503926208288280L;

	private boolean areMinimumInfoDisplayed;

	private JComboBox civiliteField ;
	private JTextField civiliteTextField;
	private Checkbox deposantField ;	
	private JTextField nomField;
	private JComboBox nomComboField;
	private JTextField prenomField;
	private JComboBox prenomComboField;
	private JTextField loginField;
	private JPasswordField passwordField;
	private AdressePane addressPane;
	private JTextField telephonefixeField;
	private JTextField telephoneportableField;
	private JTextField emailField;
	private JComboBox typeidentiteField;
	private JTextField typeidentiteTextField;
	private JTextField numeroidentiteField;
	private JTextField datenaissanceField;  	
	private JTextArea commentaireField;
	private JTextField champnumerique1Field;
	private JTextField champnumerique2Field;	
	private JTextField montantdeposeField;
	private JTextField montantduField;	

	public DeposantPane() {
	}

	public DeposantPane(DeposantModel model) {
		super(model);
	}

	public DeposantPane(DeposantModel model, boolean areMinimumInfoDisplayed) {
		super(model);

		this.areMinimumInfoDisplayed = areMinimumInfoDisplayed;
	}

	public DeposantPane(YolaineViewType viewType) {
		super(viewType);
	}

	public DeposantPane(DeposantModel model, YolaineViewType viewType) {
		super(model, viewType);
	}


	@Override
	protected DeposantModel createDefaultModel() {
		return new DefaultDeposantModel();
	}

	@Override
	protected void initView() {

		civiliteField = new JComboBox();
		civiliteTextField = new JTextField();
		deposantField = new Checkbox(" Déposante",null,false);
		deposantField.setBackground(Color.lightGray);			
		nomField = new JTextField();
		nomComboField = new JComboBox();
		prenomField = new JTextField();
		prenomComboField = new JComboBox();
		loginField = new JTextField();
		passwordField = new JPasswordField();
		addressPane = new AdressePane(getModel().getAddressModel());
		addressPane.setBorder(BorderFactory.createTitledBorder("Adresse"));		    
		telephonefixeField = new JTextField();
		telephoneportableField = new JTextField();
		emailField = new JTextField();
		typeidentiteTextField = new JTextField();
		typeidentiteField = new JComboBox();
		numeroidentiteField = new JTextField();
		datenaissanceField = new JTextField();
		commentaireField = new JTextArea(3,25);
		champnumerique1Field = new JTextField();
		champnumerique2Field = new JTextField();
		montantdeposeField = new JTextField();
		montantduField = new JTextField();


		//commentaireField.setWrapStyleWord(true);
		//commentaireField.setLineWrap(true);
		JScrollPane scrollingDescription = new JScrollPane(commentaireField,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		try {
			List<Civilite> civilites = ClientDelegate.trouverCivilites();

			
			for (Civilite civilite: civilites) {
				civiliteField.addItem(new CiviliteComboItem(civilite));
			}
		} catch (Exception exc) {
			CiviliteComboItem error = new CiviliteComboItem(null);
			civiliteField.addItem(error);
		}
			
		
		try {
			List<Client> clients = ClientDelegate.trouverClients(true);
			String nom =  clients.get(0).getNom();				
			
			nomComboField.addItem(null);
			nomComboField.addItem(new NomComboItem(clients.get(0)));
			for (Client client : clients) {
				String nomtmp = client.getNom().toUpperCase();
				if(!nomtmp.equals(nom)){
					nomComboField.addItem(new NomComboItem(client));					
					nom = nomtmp;					
				}else {
										
				}				
			}
		} catch (Exception exc) {
			NomComboItem error = new NomComboItem(null);
			nomComboField.addItem(error);
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

		JPanel identificationPane = new JPanel(new GridBagLayout());
		identificationPane.setOpaque(false);
		identificationPane.setBorder(BorderFactory
				.createTitledBorder("Identification"));

		int row = 0;
		Insets insets = new Insets(2, 3, 2, 3);

		identificationPane.add(new JLabel("Login : "), new GridBagConstraints(0,
				row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.VERTICAL, insets, 0, 0));
		identificationPane.add(loginField, new GridBagConstraints(1, row, 1,
				1, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, insets, 0, 0));

		identificationPane.add(new JLabel("Password : "), new GridBagConstraints(
				2, row, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.VERTICAL, insets, 0, 0));
		identificationPane.add(passwordField, new GridBagConstraints(3, row++,
				1, 1, 1.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, insets, 0, 0));

		row = 0;
		if (!areMinimumInfoDisplayed) {	

			add(new JLabel("Civilité * : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(civiliteField, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
			add(civiliteTextField, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
			add(new JLabel(" "), new GridBagConstraints(2, row, 1, 1, 0.0,
					0.0, GridBagConstraints.EAST, GridBagConstraints.EAST,
					insets, 0, 0));			
			add(deposantField, new GridBagConstraints(3, row++, 1, 1, 0.0,
					0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					insets, 0, 0));		

			add(new JLabel("Nom : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(nomField, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
			add(nomComboField, new GridBagConstraints(1, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
			add(new JLabel("Prénom : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(prenomField, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));	
			add(prenomComboField, new GridBagConstraints(1, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
		}

		add(addressPane, new GridBagConstraints(0, row++, 4, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));

		if (!areMinimumInfoDisplayed) {
			add(new JLabel("Telephone fixe : "), new GridBagConstraints(0, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST,
					GridBagConstraints.VERTICAL, insets, 0, 0));
			add(telephonefixeField, new GridBagConstraints(1, row,1, 1, 1.0,
					0.0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, insets, 0, 0));

			add(new JLabel("Telephone portable : "), new GridBagConstraints(2, row, 1, 1,
					0.0, 0.0, GridBagConstraints.EAST,
					GridBagConstraints.VERTICAL, insets, 0, 0));
			add(telephoneportableField, new GridBagConstraints(3, row++,1, 1, 1.0,
					0.0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, insets, 0, 0));		  

			add(new JLabel("Email : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(emailField, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					insets, 0, 0));	

			add(new JLabel("Date de naissance : "), new GridBagConstraints(2, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(datenaissanceField, new GridBagConstraints(3, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));   

			add(new JLabel("Type d'identité : "), new GridBagConstraints(0, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST,
					GridBagConstraints.VERTICAL, insets, 0, 0));
			add(typeidentiteField, new GridBagConstraints(1, row,1, 1, 1.0,
					0.0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, insets, 0, 0));
			add(typeidentiteTextField, new GridBagConstraints(1, row,1, 1, 1.0,
					0.0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, insets, 0, 0));
			add(new JLabel("Numéro d'identité : "), new GridBagConstraints(2, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST,
					GridBagConstraints.VERTICAL, insets, 0, 0));
			add(numeroidentiteField, new GridBagConstraints(3, row++,1, 1, 1.0,
					0.0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, insets, 0, 0));		  

			add(new JLabel("Commentaire : "), new GridBagConstraints(0, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(scrollingDescription, new GridBagConstraints(1, row++,3, 1, 1.0,
					0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					insets, 0, 0));

			add(new JLabel("Valeur 1 : "), new GridBagConstraints(0, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(champnumerique1Field, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));   

			add(new JLabel("Valeur 2 : "), new GridBagConstraints(2, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(champnumerique2Field, new GridBagConstraints(3, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0)); 

			add(new JLabel("Montant déposé : "), new GridBagConstraints(0, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(montantdeposeField, new GridBagConstraints(1, row,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));   

			add(new JLabel("Montant dû : "), new GridBagConstraints(2, row, 1, 1,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(montantduField, new GridBagConstraints(3, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0)); 

			add(identificationPane, new GridBagConstraints(1, row++,3, 1, 1.0,
					0.0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, insets, 0, 0));
		}

		synchronizeViewType(getViewType());
	}	


	@Override
	protected void installViewListeners() {		

		civiliteField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Civilite oldValue = model.getCivilite();
				Civilite newValue = ((CiviliteComboItem) evt.getItem())
				.getCivilite();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCivilite(newValue);
				}
			}

		});

		civiliteTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTypeidentite().getTypeIdentite();
				String newValue = civiliteTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCivilite(new Civilite(newValue));
				}
			}

		});	
		
		deposantField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				boolean oldValue = model.isDeposante();
				boolean newValue;

				if (deposantField.getState() == false)
				{
					montantdeposeField.setEditable(false);					
				}else if (deposantField.getState() == true){
					montantdeposeField.setEditable(true);
				//	montantdeposeField.setText(null);
					montantduField.setEditable(true);
				//	montantduField.setText(null);
				}				

				newValue = deposantField.getState();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setDeposante(newValue);
				}
			}

			@Override
			public void keyPressed(KeyEvent evt) {
				boolean oldValue = model.isDeposante();
				boolean newValue;

				if (deposantField.getState() == false){
					montantdeposeField.setEditable(false);					
				}else if (deposantField.getState() == true){
					montantdeposeField.setEditable(true);					
					montantduField.setEditable(true);					
				}				

				newValue = deposantField.getState();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setDeposante(newValue);
				}
			}
		});

		deposantField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				boolean oldValue = model.isDeposante();
				boolean newValue = deposantField.getState();
				
				if (deposantField.getState() == false || viewType == LIRE ||
						viewType == SUPPRIMER ){
					montantdeposeField.setText("");
					montantdeposeField.setEditable(false);
					montantduField.setText("");
					montantduField.setEditable(false);
				}else{
					montantdeposeField.setEditable(true);
					montantduField.setEditable(true);								
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setDeposante(newValue);
				}
			}
		});

		nomField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getNom();
				String newValue = nomField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNom(newValue);
				}
			}

		});	

		nomComboField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				String oldValue = model.getNom();
				String newValue = ((NomComboItem) evt.getItem())
				.toString();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNom(newValue);						
					prenomComboField.removeAllItems();
					try {
						List<Client> clients = ClientDelegate.trouverClients(true);

						prenomComboField.addItem(null);
						for (Client client : clients) {
							if (client.getNom().equals(model.getNom())){
								prenomComboField.addItem(new PrenomComboItem(client));
							}else{
								
							}
						}
					} catch (Exception exc) {
						PrenomComboItem error = new PrenomComboItem(null);
						prenomComboField.addItem(error);
					}
				}	
				
			}
		});


		prenomField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getPrenom();
				String newValue = prenomField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPrenom(newValue);
				}
			}

		});

		prenomComboField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				String oldValue = model.getPrenom();
				String newValue = ((PrenomComboItem) evt.getItem())
				.toString();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPrenom(newValue);
				}
			}

		});

		telephonefixeField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTelephonefixe();
				String newValue = telephonefixeField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTelephonefixe(newValue);
				}
			}

		});

		telephoneportableField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTelephoneportable();
				String newValue = telephoneportableField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTelephoneportable(newValue);
				}
			}

		});

		emailField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getEmail();
				String newValue = emailField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setEmail(newValue);
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
		
		numeroidentiteField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getNumeroidentite();
				String newValue = numeroidentiteField.getText();			

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setNumeroidentite(newValue);
				}
			}

		});

		datenaissanceField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getDatenaissance();
				String newValue = datenaissanceField.getText();			

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setDatenaissance(newValue);
				}
			}

		});


		commentaireField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getCommentaire();
				String newValue = commentaireField.getText();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCommentaire(newValue);
				}
			}

		});

		champnumerique1Field.addFocusListener(new FocusAdapter() {			

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getChampnumerique1();
				String newValue = champnumerique1Field.getText();



				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setChampnumerique1(newValue);
				}
			}
		});

		champnumerique2Field.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getChampnumerique2();
				String newValue = champnumerique2Field.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setChampnumerique2(newValue);
				}
			}
		});


		montantdeposeField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				Float oldValue = Float.valueOf(model.getMontantdepose());
				Float newValue = null;

				try {
					newValue = new Float(montantdeposeField.getText());
					montantdeposeField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					montantdeposeField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantdepose(String.valueOf(newValue));
				}
			}

		});

		montantdeposeField.addFocusListener(new FocusAdapter() {			

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getMontantdepose();
				String newValue = montantdeposeField.getText();               

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantdepose(newValue);
				}
			}
		});

		montantduField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				Float oldValue = Float.valueOf(model.getMontantdu());
				Float newValue = null;

				try {
					newValue = new Float(montantduField.getText());
					montantduField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					montantduField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantdu(String.valueOf(newValue));
				}
			}

		});

		montantduField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getMontantdu();
				String newValue = montantduField.getText();               

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantdu(newValue);
				}
			}
		});

		loginField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getLogin();
				String newValue = loginField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setLogin(newValue);
				}
			}

		});

		passwordField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getPassword();
				String newValue = (passwordField.getPassword() == null ? null
						: String.valueOf(passwordField.getPassword()));

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPassword(newValue);
				}
			}

		});
	}

	@Override
	protected void initViewValues() {		
		if (viewType == CREER){
			List<Client> list = ClientDelegate.trouverTousclients();			
//			model.setIdentifierToFind(list.get(list.size()).getId() + 1 );
			System.out.println("" + model.getIdentifierToFind() + "");
			
		}

		civiliteField.setSelectedItem(model.getCivilite() == null ? new CiviliteComboItem(null) : new CiviliteComboItem(model.getCivilite()));
		civiliteTextField.setText(model.getCivilite().getCivilite());
		deposantField.setState(model.isDeposante());
		if (deposantField.getState() == false || viewType != LIRE || viewType != SUPPRIMER)	{
			montantdeposeField.setEditable(false);
			montantduField.setEditable(false);
		}
		else {
			montantdeposeField.setEditable(true);
			montantduField.setEditable(true);
		}		
		
		nomField.setText(model.getNom());
		nomComboField.setSelectedItem(new NomComboItem(model.getClient()));		
		prenomField.setText(model.getPrenom());		     
		prenomComboField.setSelectedItem(new PrenomComboItem(model.getClient()));
		datenaissanceField.setText(model.getDatenaissance());
		typeidentiteTextField.setText(model.getTypeidentite().getTypeIdentite());
		typeidentiteField.setSelectedItem(new IdentiteComboItem(model.getTypeidentite()));
		numeroidentiteField.setText(model.getNumeroidentite());
		telephonefixeField.setText(model.getTelephonefixe());
		telephoneportableField.setText(model.getTelephoneportable());
		emailField.setText(model.getEmail());
		commentaireField.setText(model.getCommentaire());
		champnumerique1Field.setText(model.getChampnumerique1());
		champnumerique2Field.setText(model.getChampnumerique2());		
		montantdeposeField.setText(model.getMontantdepose() == null ? "" : model.getMontantdepose());
		montantduField.setText(model.getMontantdu() == "" ? "" : model.getMontantdu());
		if (addressPane.getModel() != model.getAddressModel()) {
			addressPane.setModel(model.getAddressModel());
			loginField.setText(model.getLogin());
			passwordField.setText(model.getPassword());   
		}
	}

	@Override
	protected DeposantListener createDefaultPropertyChangeHandler() {
		return new PropertyChangeHandler();
	}


	private class PropertyChangeHandler extends DeposantAdapter {		

		@Override
		public void civiliteChanged(
				XSEvent<DeposantEventPropertyName, Civilite> evt) {
			Civilite oldValue = null;
			Object selectedItem = civiliteField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CiviliteComboItem) {
				oldValue = ((CiviliteComboItem) selectedItem).getCivilite();
			}

			Civilite newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				civiliteField.setSelectedItem(new CiviliteComboItem(newValue));

			}
		}

		@Override
		public void deposantChanged(
				XSEvent<DeposantEventPropertyName, Boolean> evt) {
			boolean oldValue = deposantField.getState();
			boolean newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				deposantField.setState(newValue);
			}
		}


		@Override
		public void nomChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = nomField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				nomField.setText(newValue);
				
			}
		}

		@Override
		public void nomComboChanged(
				XSEvent<DeposantEventPropertyName, Client> evt) {
			Client oldValue = null;
			Object selectedItem = nomComboField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CiviliteComboItem) {
				oldValue = ((NomComboItem) selectedItem).getClient();
			}

			Client newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				nomComboField.setSelectedItem(new NomComboItem(newValue));				
			}
		}

		
		@Override
		public void prenomChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = prenomField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				prenomField.setText(newValue);
			}
		}


		@Override
		public void prenomComboChanged(
				XSEvent<DeposantEventPropertyName, Client> evt) {
			Client oldValue = null;
			Object selectedItem = prenomComboField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CiviliteComboItem) {
				oldValue = ((PrenomComboItem) selectedItem).client;
			}

			Client newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				prenomComboField.setSelectedItem(new PrenomComboItem(newValue));				
			}
		}



		@Override
		public void datenaissanceChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = datenaissanceField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {			
				datenaissanceField.setText(newValue);			
			}
		}

		@Override
		public void typeidentiteChanged(
				XSEvent<DeposantEventPropertyName, TypeIdentite> evt) {
			TypeIdentite oldValue = null;
			Object selectedItem = typeidentiteField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof IdentiteComboItem) {
				oldValue = ((IdentiteComboItem) selectedItem).getTypeidentite();
			}

			TypeIdentite newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				typeidentiteField.setSelectedItem(new IdentiteComboItem(newValue));
				typeidentiteTextField.setText(newValue.getTypeIdentite());
			}
		}

		@Override
		public void numeroidentiteChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = numeroidentiteField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {			
				numeroidentiteField.setText(newValue);			
			}
		}

		@Override
		public void telephonefixeChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = telephonefixeField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				telephonefixeField.setText(newValue);
			}
		}

		@Override
		public void telephoneportableChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = telephoneportableField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				telephoneportableField.setText(newValue);
			}
		}

		@Override
		public void emailChanged(XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = emailField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				emailField.setText(newValue);
			}
		}

		@Override
		public void commentaireChanged(XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = commentaireField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				commentaireField.setText(newValue);
			}
		}

		@Override
		public void champnumerique1Changed(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = champnumerique1Field.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				champnumerique1Field.setText(newValue);
			}
		}

		@Override
		public void champnumerique2Changed(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = champnumerique2Field.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				champnumerique2Field.setText(newValue);
			}
		}

		@Override
		public void montantdeposeChanged(XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = null;

			try {
				oldValue = montantdeposeField.getText();
			} catch (Exception exc) {
			}

			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				montantdeposeField.setText(newValue == null ? "" : newValue
						);
			}
		}


		@Override
		public void montantduChanged(XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = null;

			try {
				oldValue = montantduField.getText();
			} catch (Exception exc) {
			}

			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				montantduField.setText(newValue == null ? "" : newValue
						);
			}
		}

		@Override
		public void loginChanged(XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = loginField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				loginField.setText(newValue);
			}
		}

		@Override
		public void passwordChanged(
				XSEvent<DeposantEventPropertyName, String> evt) {
			String oldValue = new String(passwordField.getPassword());
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				passwordField.setText(newValue);
			}
		}
	}


	@Override
	protected void synchronizeViewType(YolaineViewType viewType) {
		if(viewType == CHERCHER || viewType == CHERCHER_OU_CREER){
			civiliteField.setVisible(false);
			typeidentiteField.setVisible(false);
			nomField.setVisible(false);
			nomComboField.setEditable(true);
			prenomField.setVisible(false);
			prenomComboField.setVisible(true);
			prenomComboField.setEditable(true);
			montantdeposeField.setText(null);
			montantduField.setText(null);
		}

		if(viewType == MISE_A_JOUR_OU_SUPPRIMER){
			typeidentiteTextField.setVisible(false);
			typeidentiteField.setVisible(true);			
		}
		
		if (viewType == CREER){
			
			civiliteTextField.setVisible(false);
			nomComboField.setVisible(false);
			prenomField.setEditable(true);
			nomField.setEditable(true);
			prenomComboField.setVisible(false);
			typeidentiteTextField.setVisible(false);
		}
		
		if (viewType == LIRE){
			civiliteField.setVisible(false);
			typeidentiteField.setVisible(false);
			nomComboField.setVisible(false);			
			prenomComboField.setVisible(false);	
		}
		if (viewType == MISE_A_JOUR){
			civiliteField.setVisible(false);
			nomComboField.setVisible(false);
			prenomField.setVisible(true);
			prenomField.setEditable(true);
			prenomComboField.setVisible(false);			
		}
		
		if (viewType == SUPPRIMER){
			civiliteField.setVisible(false);
			typeidentiteField.setVisible(false);
			nomComboField.setVisible(false);			
			prenomComboField.setVisible(false);			
		}
		civiliteTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER
				&& viewType != MISE_A_JOUR  && viewType != CHERCHER
				&& viewType != CHERCHER_OU_CREER  && viewType != MISE_A_JOUR_OU_SUPPRIMER);	
		civiliteField.setEditable(viewType != LIRE && viewType != SUPPRIMER
				&& viewType != MISE_A_JOUR  && viewType != CHERCHER && viewType != CHERCHER_OU_CREER);	
		deposantField.setEnabled(viewType != LIRE && viewType != SUPPRIMER);	
		nomField.setEditable(viewType != CHERCHER  && 
				viewType == CHERCHER_OU_CREER || viewType == CREER);
		nomComboField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != MISE_A_JOUR && viewType != CREER);
		prenomField.setEditable(viewType != CHERCHER  && 
				viewType == CHERCHER_OU_CREER || viewType == CREER);
		prenomComboField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != MISE_A_JOUR && viewType != CREER);
		if 
		(prenomField.getText().equals("")&& (viewType == MISE_A_JOUR_OU_SUPPRIMER || viewType == MISE_A_JOUR)){
			prenomField.setEditable(true);
		}
		addressPane.setViewType(viewType);
		telephonefixeField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		telephoneportableField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		datenaissanceField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		typeidentiteTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER 
				&& viewType != MISE_A_JOUR  );
		typeidentiteField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		numeroidentiteField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		emailField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		if (viewType == LIRE || viewType == SUPPRIMER){
			Color c = new Color(240,240,240);
			commentaireField.setBackground(c);
		}
		commentaireField.setEditable(viewType != LIRE && viewType != SUPPRIMER);		
		champnumerique1Field.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		champnumerique2Field.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		loginField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		passwordField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		if (deposantField.getState() == true && viewType != LIRE && viewType != SUPPRIMER)
		{
			montantdeposeField.setEditable(true);
			montantduField.setEditable(true);
		}
		else {
			montantdeposeField.setEditable(false);
			montantduField.setEditable(false);
		}


	}

	@Override
	public String toString() {
		String text = "Déposant";

		if (model.getNom() != null && !model.getNom().equals("")){

			text += " - " + model.getNom() + " ";

			if (model.getPrenom() != null && !model.getPrenom().equals("")){
				text += model.getPrenom();
				return text;
			}else
			{

				return text;
			}
		}

		return text;
	}


	public boolean areMinimumInfoDisplayed() {
		return areMinimumInfoDisplayed;
	}

}