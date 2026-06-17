package com.yolaine.client.ui.depots;

import static org.apache.commons.lang.time.DateFormatUtils.format;
import static org.apache.commons.lang.time.DateUtils.parseDate;


import static com.yolaine.client.ui.util.YolaineUIConstants.DATE_PATTERN;
import static com.yolaine.client.ui.util.YolaineUIConstants.DATE_PATTERNS;
import static com.yolaine.client.ui.util.YolaineViewType.*;


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
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.yolaine.client.ui.articles.article.ArticlePane;
import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.clients.client.event.ClientAdapter;
import com.yolaine.client.ui.clients.client.event.ClientEventPropertyName;
import com.yolaine.client.ui.clients.client.event.ClientListener;
import com.yolaine.client.ui.clients.client.model.ClientModel;
import com.yolaine.client.ui.clients.client.model.DefaultClientModel;
import com.yolaine.client.ui.commun.adresse.AdressePane;
import com.yolaine.client.ui.depots.event.DepotAdapter;
import com.yolaine.client.ui.depots.event.DepotEventPropertyName;
import com.yolaine.client.ui.depots.event.DepotListener;
import com.yolaine.client.ui.depots.model.DefaultDepotModel;
import com.yolaine.client.ui.depots.model.DepotModel;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.CategoryComboItem;
import com.yolaine.client.ui.util.combo.CiviliteComboItem;
import com.yolaine.client.ui.util.combo.ClientComboItem;
import com.yolaine.client.ui.util.combo.IdentiteComboItem;
import com.yolaine.client.ui.util.combo.MancheComboItem;
import com.yolaine.client.ui.util.combo.MarqueComboItem;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;

public class DepotPane
extends
YolaineComponentPane<DepotModel, DepotListener, DepotEventPropertyName> {

	private static final long serialVersionUID = 1418503926208288280L;

	private boolean areMinimumInfoDisplayed;

	private JTextField idField;
	private JTextField clientField;
	private JComboBox ClientField;
	private JComboBox typearticleField;
	private JTextField datedepotField;
	private JTextField identifiantField;
	private Checkbox cloturedepotField ;	
	private ArticlePane articlePane;
	private YolaineViewType view;

	public DepotPane() {}

	public DepotPane(DepotModel model) {
		super(model);
	}

	public DepotPane(DepotModel model, boolean areMinimumInfoDisplayed) {
		super(model);
		this.areMinimumInfoDisplayed = areMinimumInfoDisplayed;
	}

	public DepotPane(YolaineViewType viewType) {
		super(viewType);
		System.out.println(viewType.toString());
	}

	public DepotPane(DepotModel model, YolaineViewType viewType) {		
		super(model, viewType);
		this.setViewType(viewType);
		System.out.println(viewType.toString());
		this.view = viewType;
		System.out.println(this.view.toString());
	}		
	
	public YolaineViewType getView() {
		return view;
	}

	public void setView(YolaineViewType view) {
		this.view = view;
	}

	@Override
	protected DepotModel createDefaultModel() {
		return new DefaultDepotModel();
	}

	protected void initView() {
		identifiantField = new JTextField();
		idField = new JTextField(); 
		clientField = new JTextField();
		ClientField = new JComboBox();
		datedepotField = new JTextField();		
		cloturedepotField = new Checkbox();
		cloturedepotField = new Checkbox(" Dépôt cloturé",null,false);
		cloturedepotField.setBackground(Color.lightGray);
		System.out.println(" zzz" + this.getViewType());
		articlePane = new ArticlePane(getModel().getArticleModel());
		articlePane.setBorder(BorderFactory.createTitledBorder("Article"));
		
		try {
			List<Client> clients = ClientDelegate.trouverClients(true);

			ClientField.addItem(null);
			for (Client client : clients) {

				ClientField.addItem(new ClientComboItem(client));
			}
		} catch (Exception exc) {
			ClientComboItem error = new ClientComboItem(null);
			ClientField.addItem(error);
		}

		setLayout(new GridBagLayout());		
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));		

		int row = 0;
		Insets insets = new Insets(2, 3, 2, 3);


		row = 0;
		if (!areMinimumInfoDisplayed) {	
			add(articlePane, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
			add(new JLabel("Déposante : "), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
					0, 0));
			add(clientField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));

			add(ClientField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));

			add(new JLabel("Identifiant du dépôt : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.EAST, GridBagConstraints.EAST,
					insets, 0, 0));

			add(identifiantField
					, new GridBagConstraints(1, row, 1, 1, 0.0,
							0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
							insets, 0, 0)
			);	
			add(new JLabel(" "), new GridBagConstraints(2, row, 1, 1, 0.0,
					0.0, GridBagConstraints.EAST, GridBagConstraints.EAST,
					insets, 0, 0));
			add(cloturedepotField
					, new GridBagConstraints(3, row++, 1, 1, 0.0,
							0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
							insets, 0, 0)
			);	
			add(new JLabel("Date de dépôt : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(datedepotField, new GridBagConstraints(1, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));
		}

		synchronizeViewType(getViewType());
	}	

	@Override
	protected void installViewListeners() {		

		identifiantField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				Long oldValue = model.getDepot().getId();
				Long newValue ;

				try {
					newValue = Long.getLong(identifiantField.getText());
					identifiantField.setForeground(Color.black);
				} catch (NumberFormatException exc) {
					newValue = oldValue;
					identifiantField.setForeground(Color.red);
				}
				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.getDepot().setId(newValue);
				}
			}

		});

		identifiantField.addFocusListener(new FocusAdapter() {			

			public void focusLost(FocusEvent evt) {
				Long oldValue = model.getDepot().getId();
				Long newValue = Long.getLong(identifiantField.getText());

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.getDepot().setId(newValue);
				}
			}
		});

		cloturedepotField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				boolean oldValue = model.isCloturedepot();
				boolean newValue;								

				newValue = cloturedepotField.getState();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCloturedepot(newValue);
				}
			}
		});

		clientField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getDepot().getClient().getNom();
				String newValue = clientField.getText();				

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.getDepot().getClient().setNom(newValue);
				}
			}

		});

		cloturedepotField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				boolean oldValue = model.isCloturedepot();
				boolean newValue = cloturedepotField.getState();				

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCloturedepot(newValue);
				}
			}

		});

		ClientField.addItemListener(evt -> {
            Client oldValue = model.getDepot().getClient();
            Client newValue = ((ClientComboItem) evt.getItem())
            .getClient();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                model.getDepot().setClient(newValue);
            }
        });
	}

	@Override
	protected void initViewValues() {
		if (viewType == CREER_DEPOT ){
			clientField.setText(model.getDepot().getClient().getNom());
			if (articlePane.getModel() != model.getArticleModel()) {
				articlePane.setModel(model.getArticleModel());
				articlePane.setViewType(CREER_DEPOT);
			}
		}
		clientField.setText(model.getDepot().getClient().getNom());

		identifiantField.setText(String.valueOf(model.getDepot().getId()));
		cloturedepotField.setState(model.isCloturedepot());
		model.setDatedepot(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		datedepotField.setText(model.getDatedepot());
		ClientField.setSelectedItem(new ClientComboItem(model.getDepot().getClient()));
	}

	@Override
	protected DepotListener createDefaultPropertyChangeHandler() {
		return new PropertyChangeHandler();
	}


	private class PropertyChangeHandler extends DepotAdapter {		

		@Override
		public void identifiantChanged(XSEvent<DepotEventPropertyName,Integer> evt) {
			Long oldValue = model.getDepot().getId();

			try {
				oldValue = Long.getLong(identifiantField.getText());
			} catch (Exception exc) {
			}

			int newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				identifiantField.setText(String.valueOf(newValue) == null ? null : String.valueOf(newValue));
			}
		}

		@Override
		public void cloturedepotChanged(
				XSEvent<DepotEventPropertyName, Boolean> evt) {
			boolean oldValue = cloturedepotField.getState();
			boolean newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				cloturedepotField.setState(newValue);
			}
		}

		@Override
		public void datedepotChanged(
				XSEvent<DepotEventPropertyName, String> evt) {
			String oldValue = datedepotField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				datedepotField.setText(newValue);
			}
		}

		@Override
		public void clientChanged(
				XSEvent<DepotEventPropertyName, Client> evt) {
			Client oldValue = null;
			Object selectedItem = ClientField.getSelectedItem();

			if (selectedItem != null
					&& selectedItem instanceof ClientComboItem) {
				oldValue = ((ClientComboItem) selectedItem).getClient();
			}

			Client newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				ClientField.setSelectedItem(new ClientComboItem(newValue));
			}
		}
	}


	@Override
	protected void synchronizeViewType(YolaineViewType viewType) {	

		if (viewType == SUPPRIMER){
			articlePane.setVisible(false);
			ClientField.setVisible(false);
		}

		if (viewType == MISE_A_JOUR_DEPOT){
			articlePane.setVisible(true);
			clientField.setVisible(true);
			clientField.setEditable(false);
			ClientField.setVisible(false);
			identifiantField.setVisible(true);
			identifiantField.setEditable(false);
			idField.setEditable(false);
			datedepotField.setEditable(false);
		}

		if(viewType == CHERCHER){
			clientField.setVisible(false);
			ClientField.setVisible(true);
			ClientField.setEnabled(true);
		}
		
		if (viewType == LIRE){
			articlePane.setVisible(false);
			clientField.setVisible(true);
			clientField.setEditable(false);
			ClientField.setVisible(false);
			identifiantField.setVisible(true);
			identifiantField.setEditable(false);
			datedepotField.setEditable(false);
		}

		if (viewType == CREER_DEPOT ){
			ClientField.setVisible(false);
			clientField.setVisible(true);
			clientField.setEditable(false);
			datedepotField.setEditable(false);
			articlePane.setViewType(CREER_DEPOT);
		}
		
		identifiantField.setEditable( viewType != CREER_DEPOT &&
				viewType != CREER && viewType != MISE_A_JOUR_DEPOT
				&& viewType != LIRE && viewType != SUPPRIMER && viewType != MISE_A_JOUR);
		cloturedepotField.setEnabled(viewType != LIRE && viewType != SUPPRIMER && viewType != CREER_DEPOT && viewType != MISE_A_JOUR_DEPOT);		
		clientField.setEditable(viewType != SUPPRIMER && viewType != MISE_A_JOUR_DEPOT && viewType != CREER_DEPOT);
		datedepotField.setEditable(viewType != SUPPRIMER && viewType != MISE_A_JOUR_DEPOT && viewType != CREER_DEPOT );
		ClientField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		idField.setEditable(viewType != SUPPRIMER && viewType != MISE_A_JOUR_DEPOT);
		if (viewType == MISE_A_JOUR_DEPOT){
			articlePane.setViewType(MISE_A_JOUR_DEPOT);			
			articlePane.getModel().setDepot(model.getDepot());			
		}
	}

	@Override
	public String toString() {
		String text = "Depot n° " + model.getDepot().getId() + " ";		
		if (viewType == CREER_DEPOT) {
			if (model.getDepot().getClient().getPrenom() != null && !model.getDepot().getClient().getPrenom().equals("")){

				text += " de " + model.getDepot().getClient().getPrenom() + " ";

				if (model.getDepot().getClient().getNom() != null && !model.getDepot().getClient().getNom().equals("")){
					text += model.getDepot().getClient().getNom();
					return text;
				}
			}else if (model.getDepot().getClient().getNom() != null && !model.getDepot().getClient().getNom().equals("")){
				text += " de " + model.getDepot().getClient().getNom();
				return text;
			}
		}
		return text;
	}

	public boolean areMinimumInfoDisplayed() {
		return areMinimumInfoDisplayed;
	}

}