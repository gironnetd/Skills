package com.yolaine.client.ui.articles.article;


import static com.yolaine.client.ui.util.YolaineViewType.*;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.text.*;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jxl.biff.drawing.CheckBox;
import jxl.biff.drawing.ComboBox;

import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import static com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName.*;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.articles.article.event.ArticleAdapter;
import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName.*;
import com.yolaine.client.ui.articles.article.event.ArticleListener;
import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.articles.article.model.DefaultArticleModel;
import com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.PaiementPane;
import com.yolaine.client.ui.transaction.paiement.model.DefaultPaiementModel;
import com.yolaine.client.ui.transaction.paiement.model.PaiementModel;
import com.yolaine.client.ui.transaction.remboursement.RemboursementPane;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.BanqueComboItem;
import com.yolaine.client.ui.util.combo.CategoryComboItem;
import com.yolaine.client.ui.util.combo.CiviliteComboItem;
import com.yolaine.client.ui.util.combo.CouleurComboItem;
import com.yolaine.client.ui.util.combo.IdentiteComboItem;
import com.yolaine.client.ui.util.combo.MancheComboItem;
import com.yolaine.client.ui.util.combo.MarqueComboItem;
import com.yolaine.client.ui.util.combo.ProductComboItem;
import com.yolaine.client.ui.util.combo.SituationComboItem;
import com.yolaine.client.ui.util.combo.TypePaiementComboItem;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;


public class ArticlePane extends
YolaineComponentPane<ArticleModel, ArticleListener, ArticleEventPropertyName> {

	private static final long serialVersionUID = 5074118210045914459L;

	//private boolean areMinimumInfoDisplayed;

	private JTextField idField;
	private JTextField clientField;
	private JComboBox typearticleField;
	private JTextField typearticleTextField;
	private JTextField datedepotField;
	private JTextField montantdepotField;
	private JTextField prixventeField;	
	private JTextField marqueTextField;
	private JComboBox marqueField;	
	private JTextField tailleField;
	private JTextField couleur1TextField;
	private JTextField couleur2TextField; 
	private JComboBox couleur1Field;
	private JComboBox couleur2Field;    
	private JTextField mancheTextField;
	private JComboBox mancheField;
	private Checkbox soldeField;    
	private JTextField pourcentageField;    
	private JTextField situationTextField;
	private JComboBox situationField;    
	private JTextField commentaireField;    
	private JLabel situationTextInt;
	private PaiementPane paiementPane;
	private RemboursementPane remboursementPane;

	public ArticlePane() {
		super();
	}

	public ArticlePane(ArticleModel model) {
		super(model);
	}

	public ArticlePane(YolaineViewType viewType) {
		super(viewType);
	}

	public ArticlePane(ArticleModel model, YolaineViewType viewType) {
		super(model, viewType);
	}

	@Override
	protected ArticleModel createDefaultModel() {
		return new DefaultArticleModel();
	}

	@Override
	protected void initView() {

		idField = new JTextField();        
		clientField = new JTextField();
		typearticleField = new JComboBox();
		typearticleTextField = new JTextField();
		datedepotField = new JTextField();		
		montantdepotField = new JTextField();
		prixventeField = new JTextField();		
		marqueTextField = new JTextField();
		marqueField = new JComboBox();		
		tailleField = new JTextField();
		couleur1TextField = new JTextField();
		couleur2TextField = new JTextField();
		couleur1Field = new JComboBox();
		couleur2Field = new JComboBox();        
		mancheTextField = new JTextField();
		mancheField = new JComboBox();
		soldeField = new Checkbox(" soldé",null,false);
		soldeField.setBackground(Color.lightGray);
		pourcentageField = new JTextField();
		situationTextField = new JTextField();
		situationField = new JComboBox();        
		commentaireField = new JTextField();
		situationTextInt = new JLabel(" Situation : ");		
		paiementPane = new PaiementPane(getModel().getPaiementModel());		
		paiementPane.setBorder(BorderFactory.createTitledBorder("Paiement"));
		remboursementPane = new RemboursementPane(getModel().getRemboursementModel());
		remboursementPane.setBorder(BorderFactory.createTitledBorder("Remboursement"));
		try {
			List<Categorie> categories = CatalogDelegate.findCategories();

			for (Categorie categorie : categories) {
				typearticleField.addItem(new CategoryComboItem(categorie));
			}
		} catch (Exception exc) {
			CategoryComboItem error = new CategoryComboItem(null);
			typearticleField.addItem(error);
		}

		try {
			List<Marque> marques = CatalogDelegate.trouverMarques();

			for (Marque marque : marques) {
				marqueField.addItem(new MarqueComboItem(marque));
			}
		} catch (Exception exc) {
			MarqueComboItem error = new MarqueComboItem(null);
			marqueField.addItem(error);
		}

		try {
			
			List<Manche> manches = CatalogDelegate.trouverManches();

			for (Manche manche : manches) {
				mancheField.addItem(new MancheComboItem(manche));
			}
		} catch (Exception exc) {
			MancheComboItem error = new MancheComboItem(null);
			mancheField.addItem(error);
		}

		try {
			
			List<Couleur> couleurs = CatalogDelegate.trouverCouleurs();		

			for (Couleur couleur : couleurs) {
				couleur1Field.addItem(new CouleurComboItem(couleur));
			}
		} catch (Exception exc) {
			CouleurComboItem error = new CouleurComboItem(null);
			couleur1Field.addItem(error);
		}

		try {
			
			List<Couleur> couleurs = CatalogDelegate.trouverCouleurs();

			for (Couleur couleur : couleurs) {
				couleur2Field.addItem(new CouleurComboItem(couleur));
			}
		} catch (Exception exc) {
			CouleurComboItem error = new CouleurComboItem(null);
			couleur2Field.addItem(error);
		}	 

		try {
			
			List<Situation> situations = CatalogDelegate.trouverSituations();

			situationField.addItem(null);
			for (Situation situation : situations) {
				situationField.addItem(new SituationComboItem(situation));
			}
		} catch (Exception exc) {
			SituationComboItem error = new SituationComboItem(null);
			situationField.addItem(error);
		}	

		setLayout(new GridBagLayout());
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		int row = 0;
		Insets insets = new Insets(2, 5, 2, 5);

		add(new JLabel("Identifiant : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(idField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));			
		add(new JLabel("Soldé : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(soldeField, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(new JLabel("Pourcentage : "), new GridBagConstraints(3, row, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(pourcentageField, new GridBagConstraints(4, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	
		add(new JLabel("Type Article : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(new JLabel("Couleur principale : "), new GridBagConstraints(1, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(new JLabel("Seconde couleur  : "), new GridBagConstraints(2, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(new JLabel("Manche : "), new GridBagConstraints(3, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));
		add(new JLabel("Marque : "), new GridBagConstraints(4, row++, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(typearticleField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(couleur1Field, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));		
		add(couleur2Field, new GridBagConstraints(2, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(mancheField, new GridBagConstraints(3, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(marqueField, new GridBagConstraints(4, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(typearticleTextField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(couleur1TextField, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));		
		add(couleur2TextField, new GridBagConstraints(2, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(mancheTextField, new GridBagConstraints(3, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(marqueTextField, new GridBagConstraints(4, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(new JLabel("Taille : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(new JLabel("Montant dépôt : "), new GridBagConstraints(1, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(new JLabel("Prix de vente : "), new GridBagConstraints(2, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));		

		add(situationTextInt, new GridBagConstraints(3, row++, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));		
		add(tailleField, new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	
		add(montantdepotField, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(prixventeField, new GridBagConstraints(2, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));	

		add(situationField, new GridBagConstraints(3, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(situationTextField, new GridBagConstraints(3, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));  
		add(new JLabel("Commentaire : "), new GridBagConstraints(0, row++, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(commentaireField, new GridBagConstraints(0, row++, 5, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0)); 

		add(paiementPane, new GridBagConstraints(0, row, 2, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(new JLabel(), new GridBagConstraints(2, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0)); 
		
		add(remboursementPane, new GridBagConstraints(3, row++, 2, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));
		add(new JLabel(), new GridBagConstraints(0, row, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0)); 
		
		synchronizeViewType(getViewType());
	}


	@Override
	protected void installViewListeners() {
		idField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				Long oldValue = model.getId();
				String newValue;

				try {
					newValue = idField.getText();
					idField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue.toString();
					idField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					//model.setId(newValue);
				}
			}

		});

		idField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {			

				String oldValue = model.getId().toString();
				String newValue;
				try {
					newValue = idField.getText();
					idField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					idField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					//model.setId(newValue);
				}
			}

		});

		clientField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getClientArticle().getNom();
				String newValue = clientField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.getClientArticle().setNom(newValue);
				}
			}

		});

		typearticleField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Categorie oldValue = model.getCategorie();
				Categorie newValue = ((CategoryComboItem) evt.getItem())
				.getCategory();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCategorie(newValue);
				}
			}
		});

		datedepotField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getDateDepot();
				String newValue = datedepotField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {					
					model.setDateDepot(newValue);
				}
			}            
		});

		tailleField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTaille();
				String newValue = tailleField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTaille(newValue);
				}
			}

		});

		montantdepotField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getMontantDepot();
				String newValue = null;

				try {
					String s = montantdepotField.getText();

					newValue = s;
					montantdepotField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					montantdepotField.setForeground(Color.red);
				}
				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantDepot(newValue);
				}
			}

		});


		montantdepotField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getMontantDepot();
				String newValue = montantdepotField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMontantDepot(newValue);
				}
			}

		});

		prixventeField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getPrixVente();
				String newValue = null;

				try {
					String s = prixventeField.getText();
					newValue = s;					
					prixventeField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));

					prixventeField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					prixventeField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPrixVente(newValue);
				}
			}
		});

		prixventeField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getPrixVente();
				String newValue = prixventeField.getText();	       

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPrixVente(newValue);
				}
			}

		});

		marqueField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Marque oldValue = model.getMarque();
				Marque newValue = ((MarqueComboItem) evt.getItem())
				.getMarque();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMarque(newValue);
				}
			}
		});

		couleur1Field.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Couleur oldValue = model.getCouleur1();
				Couleur newValue = ((CouleurComboItem) evt.getItem())
				.getCouleur();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCouleur1(newValue);
				}
			}
		});

		couleur2Field.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Couleur oldValue = model.getCouleur2();
				Couleur newValue = ((CouleurComboItem) evt.getItem())
				.getCouleur();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCouleur2(newValue);
				}
			}
		});

		mancheField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Manche oldValue = model.getManche();
				Manche newValue = ((MancheComboItem) evt.getItem())
				.getManche();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setManche(newValue);
				}
			}
		});


		soldeField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				boolean oldValue = model.isSolde();
				boolean newValue;			

				newValue = soldeField.getState();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setSolde(newValue);
				}
			}

			@Override
			public void keyPressed(KeyEvent evt) {
				boolean oldValue = model.isSolde();
				boolean newValue;			

				newValue = soldeField.getState();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setSolde(newValue);
				}
			}

		});


		soldeField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				boolean oldValue = model.isSolde();
				boolean newValue = soldeField.getState();



				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setSolde(newValue);
				}
			}

		});

		pourcentageField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				String oldValue = model.getPourcentage();
				String newValue = null;

				try {
					String s = pourcentageField.getText();
					newValue = s;
					pourcentageField.setForeground(Color.black);
					Float f = new Float(Float.parseFloat(s));					
					pourcentageField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					pourcentageField.setForeground(Color.red);
				}

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPourcentage(newValue);
				}
			}
		});

		pourcentageField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getPourcentage();
				String newValue = pourcentageField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setPourcentage(newValue);
				}
			}

		});

		situationField.addItemListener(new java.awt.event.ItemListener() {

			public void itemStateChanged(ItemEvent evt) {
				Situation oldValue = model.getSituation();
				Situation newValue = ((SituationComboItem) evt.getItem())
				.getSituation();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setSituation(newValue);
				}
			}

		});

		commentaireField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTexte();
				String newValue = commentaireField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setTexte(newValue);
				}
			}
		});

		
		
		typearticleTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getCategorie().getName();
				String newValue = typearticleTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCategorie(new Categorie(newValue,""));
				}
			}

		});

		couleur1TextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getCouleur1().getCouleur();
				String newValue = couleur1TextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCouleur1(new Couleur(newValue));
				}
			}

		});

		couleur2TextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getCouleur2().getCouleur();
				String newValue = couleur2TextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCouleur2(new Couleur(newValue));
				}
			}

		});

		mancheTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getManche().getManche();
				String newValue = mancheTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setManche(new Manche(newValue));
				}
			}

		});

		marqueTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getMarque().getName();
				String newValue = marqueTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setMarque(new Marque(newValue));
				}
			}

		});

		situationTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getSituation().getSituation();
				String newValue = situationTextField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setSituation(new Situation(newValue));
				}
			}

		});

		
		

	}

	@Override
	protected void initViewValues() {
		System.out.println(" zzzzz " + viewType);
		if (viewType == CREER_DEPOT){				
			idField.setVisible(false);
			idField.setEditable(false);	
			situationField.setVisible(false);
			System.out.println(" rr " + getViewType().toString());						
			model.reset();
		}

		if(viewType == MISE_A_JOUR){
			
			idField.setEditable(false);
			clientField.setEditable(false);
		}
		if (viewType ==LISTER_ARTICLE){
			
			//String [] st = model.getId().split("-");
			//model.setId(st[2]);
		}

		if(viewType == MISE_A_JOUR_DEPOT){
			idField.setEditable(false);
			clientField.setEditable(false);
			List<Article> listearticles = CatalogDelegate.findArticles(model.getDepot().getId());
//			Long idTmp = listearticles.get(0).getId();
//			Long []st = idTmp.split("-");
//			Long s = st[2];
//			int i = 0 ;
//			for (Article articleTmp : listearticles){
//				String []st1 = articleTmp.getId().split("-");
//				String s1 = st1[2];
//				if (s1.length() > s.length()){
//					s = s1;
//					i = Integer.parseInt(s1);
//				}else if (s1.length() == s.length()){
//					i = Integer.parseInt(s);
//					int i1 = Integer.parseInt(s1);
//
//					if (i1 > i){
//						i = i1;
//					}else{}
//				}else{}
//			}
			
//			i++;
//			s = String.valueOf(i);
//			model.setId(s);
		}
		//if(viewType == VENTE_ARTICLE){
		//if (paiementPane.getModel() != model.getPaiementModel()) {
		//	paiementPane.setModel(model.getPaiementModel());
		//	paiementPane.setViewType(VENTE_ARTICLE);
	//	}
		//}
		
		idField.setText(model.getId() == null ? null : 
			String.valueOf(model.getId()));
		clientField.setText(model.getClientArticle().getNom());
		typearticleTextField.setText(model.getCategorie().getName() == null
				? " " : model.getCategorie().getName());
		couleur1TextField.setText(model.getCouleur1().getCouleur() == null 
				|| model.getCouleur1().getCouleur().equals("")
				? " " :model.getCouleur1().getCouleur());
		couleur2TextField.setText(model.getCouleur2().getCouleur() == null
				? " " : model.getCouleur2().getCouleur());
		mancheTextField.setText(model.getManche().getManche() == null
				? " " : model.getManche().getManche());
		marqueTextField.setText(model.getMarque().getName() == null
				? " " : model.getMarque().getName());
		situationTextField.setText(model.getSituation().getSituation() == null
				? " " : model.getSituation().getSituation());		
		mancheField.setSelectedItem(new MancheComboItem(model.getManche()));
		marqueField.setSelectedItem(new MarqueComboItem(model.getMarque()));		
		typearticleField.setSelectedItem(new CategoryComboItem(model.getCategorie()));
		couleur1Field.setSelectedItem(new CouleurComboItem(model.getCouleur1()));
		couleur2Field.setSelectedItem(new CouleurComboItem(model.getCouleur2()));
		datedepotField.setText(model.getDateDepot() == null ? null : model
				.getDateDepot());
		tailleField.setText(model.getTaille() == null ? null : model.getTaille());
		montantdepotField.setText(model.getMontantDepot() == null || model.getMontantDepot().equals("")
				? "" : model.getMontantDepot());
		prixventeField.setText(model.getPrixVente() == null ? " " : model.getPrixVente());
		
		pourcentageField.setText(model.getPourcentage() == null ? " " : model.getPourcentage());
		soldeField.setState(model.isSolde());		
		situationField.setSelectedItem(new SituationComboItem(model.getSituation()));
		commentaireField.setText(model.getTexte() == null ? null : model
				.getTexte());
		if (paiementPane.getModel() != model.getPaiementModel()) {
			paiementPane.setModel(model.getPaiementModel());			   
		}
		if (remboursementPane.getModel() != model.getRemboursementModel()) {
			remboursementPane.setModel(model.getRemboursementModel());			   
		}
		
	}

	@Override
	protected ArticleListener createDefaultPropertyChangeHandler() {
		return new PropertyChangeHandler();
	}

	private class PropertyChangeHandler extends ArticleAdapter {

		@Override
		public void identifiantChanged(XSEvent<ArticleEventPropertyName,String> evt) {
			String oldValue = model.getId().toString();

			try {
				oldValue = idField.getText();
			} catch (Exception exc) {
			}

			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				idField.setText(String.valueOf(newValue) == null ? null : String.valueOf(newValue));
			}
		}

		

		@Override
		public void clientChanged(XSEvent<ArticleEventPropertyName, String> evt) {
			String oldValue = clientField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				clientField.setText(newValue);
			}
		}

		@Override
		public void mancheChanged(
				XSEvent<ArticleEventPropertyName, Manche> evt) {
			Manche oldValue = null;
			Object selectedItem = mancheField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof MancheComboItem) {
				oldValue = ((MancheComboItem) selectedItem).getManche();
			}

			Manche newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				mancheField.setSelectedItem(new MancheComboItem(newValue));
			}
		}

		@Override
		public void typearticleChanged(
				XSEvent<ArticleEventPropertyName, Categorie> evt) {
			Categorie oldValue = null;
			Object selectedItem = typearticleField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CategoryComboItem) {
				oldValue = ((CategoryComboItem) selectedItem).getCategory();
			}

			Categorie newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				typearticleField.setSelectedItem(new CategoryComboItem(newValue));
			}
		}

		

		

		@Override
		public void datedepotChanged(XSEvent<ArticleEventPropertyName, String> evt) {
			String oldValue = datedepotField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				datedepotField.setText(newValue);
			}
		}  

		

		
		@Override
		public void montantdepotChanged(XSEvent<ArticleEventPropertyName, String> evt) {
			String oldValue = montantdepotField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				montantdepotField.setText(newValue);
			}
		}  

		@Override
		public void prixventeChanged(XSEvent<ArticleEventPropertyName, String> evt) {
			String oldValue = prixventeField.getText();			
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				prixventeField.setText(newValue);

			}
		}  

		@Override
		public void marqueChanged(
				XSEvent<ArticleEventPropertyName, Marque> evt) {
			Marque oldValue = null;
			Object selectedItem = marqueField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof MarqueComboItem) {
				oldValue = ((MarqueComboItem) selectedItem).getMarque();
			}

			Marque newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				marqueField.setSelectedItem(new MarqueComboItem(newValue));
			}
		}

		@Override
		public void tailleChanged(XSEvent<ArticleEventPropertyName, String> evt) {
			String oldValue = tailleField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				tailleField.setText(newValue);
			}
		}  

		@Override
		public void couleur1Changed(
				XSEvent<ArticleEventPropertyName, Couleur> evt) {
			Couleur oldValue = null;
			Object selectedItem = couleur1Field.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CouleurComboItem) {
				oldValue = ((CouleurComboItem) selectedItem).getCouleur();
			}

			Couleur newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				couleur1Field.setSelectedItem(new CouleurComboItem(newValue));
			}
		}

		@Override
		public void couleur2Changed(
				XSEvent<ArticleEventPropertyName, Couleur> evt) {
			Couleur oldValue = null;
			Object selectedItem = couleur2Field.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CouleurComboItem) {
				oldValue = ((CouleurComboItem) selectedItem).getCouleur();
			}

			Couleur newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				couleur2Field.setSelectedItem(new CouleurComboItem(newValue));
			}
		}



		@Override
		public void soldeChanged(XSEvent<ArticleEventPropertyName,Boolean> evt) {
			boolean oldValue = soldeField.getState();
			boolean newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				soldeField.setState(newValue);
			}
		}  

		@Override
		public void pourcentageChanged(XSEvent<ArticleEventPropertyName,String> evt) {
			String oldValue = pourcentageField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				pourcentageField.setText(newValue);
			}
		}  

		@Override
		public void situationChanged(
				XSEvent<ArticleEventPropertyName, Situation> evt) {
			Situation oldValue = null;
			Object selectedItem = situationField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof CouleurComboItem) {
				oldValue = ((SituationComboItem) selectedItem).getSituation();
			}

			Situation newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				situationField.setSelectedItem(new SituationComboItem(newValue));
			}
		}		

		@Override
		public void commentaireChanged(XSEvent<ArticleEventPropertyName, String> evt) {
			String oldValue = commentaireField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				commentaireField.setText(newValue);
			}
		}  
	}    

	@Override
	protected void synchronizeViewType(YolaineViewType viewType) {

		if(viewType == LIRE || viewType == SUPPRIMER || viewType == VENTE_ARTICLE
				|| viewType == MISE_A_JOUR ||  viewType == REMBOURSEMENT_ARTICLE){
			//String [] st = model.getId();//.split("-");
			idField.setText(model.getId().toString()); //(st[2]);
		}

		if(viewType == MISE_A_JOUR){			
			idField.setVisible(true);
			idField.setEditable(false);
			clientField.setVisible(true);
			clientField.setEditable(false);
			typearticleTextField.setVisible(false);
			couleur1TextField.setVisible(false);
			couleur2TextField.setVisible(false);
			mancheTextField.setVisible(false);
			marqueTextField.setVisible(false);
			paiementPane.setVisible(false);
			remboursementPane.setVisible(false);
			situationField.setVisible(false);
			situationTextField.setVisible(true);
		}

			if (this.getViewType() == MISE_A_JOUR_DEPOT){	
				List<Article> listearticles = CatalogDelegate.findArticles(model.getDepot().getId());
				int i = 0 ;
				if (listearticles.size() == 0){

				} else {
					String idTmp = listearticles.get(0).getId().toString();
					String []st = idTmp.split("-");
					String s = listearticles.get(0).getId().toString(); //st[2];

//					for (Article articleTmp : listearticles){
//						String []st1 = articleTmp.getId().toString();//.split("-");
//						String s1 = st1[2];
//						if (s1.length() > s.length()){
//							s = s1;
//							i = Integer.parseInt(s1);
//						}else if (s1.length() == s.length()){
//							i = Integer.parseInt(s);
//							int i1 = Integer.parseInt(s1);
//
//							if (i1 > i){
//								i = i1;
//							}else{}
//						}else{}
//					}
				}
				
				
				i++;
				String s = String.valueOf(i);
				//model.setId(s);
				clientField.setVisible(false);
				clientField.setEditable(false);			
				idField.setVisible(true);
				idField.setEditable(false);	
				situationField.setVisible(false);
				situationTextField.setVisible(false);
				situationTextInt.setVisible(false);				
				typearticleTextField.setVisible(false);
				couleur1TextField.setVisible(false);
				couleur2TextField.setVisible(false);
				mancheTextField.setVisible(false);
				marqueTextField.setVisible(false);
				paiementPane.setVisible(false);
				remboursementPane.setVisible(false);
			}

			if (viewType == LIRE){

				typearticleField.setVisible(false);
				couleur1Field.setVisible(false);
				couleur2Field.setVisible(false);
				mancheField.setVisible(false);
				marqueField.setVisible(false);
				situationField.setVisible(false);
				
				typearticleTextField.setVisible(true);
				couleur1TextField.setVisible(true);
				couleur2TextField.setVisible(true);
				mancheTextField.setVisible(true);
				marqueTextField.setVisible(true);				
				situationTextField.setVisible(true);
				if(model.getSituation().getSituation().equals("déposé")){
					paiementPane.setVisible(false);
					remboursementPane.setVisible(false);
				}else if (model.getSituation().getSituation().equals("vendu")){
					paiementPane.setViewType(viewType);
					remboursementPane.setVisible(false);
				}else {					
				paiementPane.setViewType(viewType);
				remboursementPane.setViewType(viewType);
				}
			}

			if (viewType == SUPPRIMER){

				typearticleField.setVisible(false);
				couleur1Field.setVisible(false);
				couleur2Field.setVisible(false);
				mancheField.setVisible(false);
				marqueField.setVisible(false);
				situationField.setVisible(false);
				typearticleTextField.setVisible(true);
				couleur1TextField.setVisible(true);
				couleur2TextField.setVisible(true);
				mancheTextField.setVisible(true);
				marqueTextField.setVisible(true);			
				situationTextField.setVisible(true);			
				if(model.getSituation().getSituation().equals("déposé")){
					paiementPane.setVisible(false);
					remboursementPane.setVisible(false);
				}else if (model.getSituation().getSituation().equals("vendu")){
					paiementPane.setViewType(viewType);
					remboursementPane.setVisible(false);
				}else {					
				paiementPane.setViewType(viewType);
				remboursementPane.setViewType(viewType);
				}
				
			}

			if(viewType == VENTE_ARTICLE){				
				typearticleField.setVisible(false);
				couleur1Field.setVisible(false);
				couleur2Field.setVisible(false);
				mancheField.setVisible(false);
				marqueField.setVisible(false);
				situationField.setVisible(false);
				situationTextField.setVisible(true);
				situationTextInt.setVisible(true);
				typearticleTextField.setVisible(true);
				couleur1TextField.setVisible(true);
				couleur2TextField.setVisible(true);
				mancheTextField.setVisible(true);
				marqueTextField.setVisible(true);				
				remboursementPane.setVisible(false);
			}

			if(viewType == REMBOURSEMENT_ARTICLE){					
				typearticleField.setVisible(false);
				couleur1Field.setVisible(false);
				couleur2Field.setVisible(false);
				mancheField.setVisible(false);
				marqueField.setVisible(false);
				situationField.setVisible(false);
				situationTextField.setVisible(true);
				situationTextInt.setVisible(true);
				typearticleTextField.setVisible(true);
				couleur1TextField.setVisible(true);
				couleur2TextField.setVisible(true);
				mancheTextField.setVisible(true);
				marqueTextField.setVisible(true);
				paiementPane.setVisible(false);				
			}

			if (viewType == CREER_DEPOT){
				
				clientField.setVisible(false);
				clientField.setEditable(false);			
				idField.setVisible(true);
				idField.setEditable(false);	
				situationField.setVisible(false);
				situationTextField.setVisible(false);
				situationTextInt.setVisible(false);				
				typearticleTextField.setVisible(false);
				couleur1TextField.setVisible(false);
				couleur2TextField.setVisible(false);
				mancheTextField.setVisible(false);
				marqueTextField.setVisible(false);
				paiementPane.setVisible(false);
				remboursementPane.setVisible(false);				 
			}else{
				clientField.setEditable(viewType != LIRE_ARTICLE && viewType != SUPPRIMER );
			}

			idField.setEditable(viewType == CHERCHER && viewType != CREER
					&& viewType == CHERCHER_OU_CREER && viewType != CREER_ARTICLE 
					&& viewType != MISE_A_JOUR && viewType != SUPPRIMER && viewType 
					!= LIRE && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			clientField.setEditable(viewType != LIRE &&
					viewType != SUPPRIMER && viewType != CREER_DEPOT
					&& viewType != MISE_A_JOUR && viewType != VENTE_ARTICLE
					&& viewType != REMBOURSEMENT_ARTICLE);			
			typearticleTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			couleur1TextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE && viewType != MISE_A_JOUR_DEPOT);
			couleur2TextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			mancheTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			marqueTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			situationTextField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE && viewType != MISE_A_JOUR);
			typearticleField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			
			datedepotField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			
			montantdepotField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			prixventeField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			
			marqueField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			tailleField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			couleur1Field.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			couleur2Field.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			mancheField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			soldeField.setEnabled(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			pourcentageField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			situationField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType !=CREER_DEPOT && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			commentaireField.setEditable(viewType != LIRE && viewType != SUPPRIMER && viewType != VENTE_ARTICLE && viewType != REMBOURSEMENT_ARTICLE);
			if(model.getSituation().getSituation().equals("déposé") && viewType == LIRE || viewType == SUPPRIMER){
				paiementPane.setVisible(false);
				remboursementPane.setVisible(false);
			}else if (model.getSituation().getSituation().equals("vendu") && viewType == LIRE || viewType == SUPPRIMER){
				paiementPane.setViewType(viewType);
				remboursementPane.setVisible(false);
			} else {
				paiementPane.setViewType(viewType);
				remboursementPane.setViewType(viewType);
			}			
	}

		@Override
		public String toString() {
			String text = "Article";

//			if (String.valueOf(model.getId()) != null &&!String.valueOf(model.getId()).equals("")) {
//				String [] ts = model.getId().toString();//.split("-");
//
//				text += " n° " + ts[2]+ " du dépôt n° " + ts[1] + " ";
//				if (model.getClientArticle().getPrenom() != null && !model.getClientArticle().getPrenom().equals("")){
//					text += " de " + model.getClientArticle().getCivilite().getCivilite() + " ";
//					text += " " + model.getClientArticle().getPrenom() + " ";
//					if (model.getClientArticle().getNom() != null && !model.getClientArticle().getNom().equals("")){
//						text += "" + model.getClientArticle().getNom() + " ";
//						return text;
//					}
//					return text;
//				} else {
//					if (model.getClientArticle().getNom() != null && !model.getClientArticle().getNom().equals("")){
//						text += "" + model.getClientArticle().getCivilite().getCivilite() + " ";
//						text += "" + model.getClientArticle().getNom() + " ";
//						return text;
//					}
//				}
//			}

			return text;
		}

	}