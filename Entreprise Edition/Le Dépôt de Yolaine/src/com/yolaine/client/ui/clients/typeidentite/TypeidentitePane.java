package com.yolaine.client.ui.clients.typeidentite;

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
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteAdapter;
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteEventPropertyName;
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteListener;
import com.yolaine.client.ui.clients.typeidentite.model.DefaultTypeidentiteModel;
import com.yolaine.client.ui.clients.typeidentite.model.TypeidentiteModel;
import com.yolaine.client.ui.commun.adresse.AdressePane;

import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.CategoryComboItem;
import com.yolaine.client.ui.util.combo.CiviliteComboItem;
import com.yolaine.client.ui.util.combo.IdentiteComboItem;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public class TypeidentitePane
extends
YolaineComponentPane<TypeidentiteModel, TypeidentiteListener, TypeidentiteEventPropertyName> {

	private static final long serialVersionUID = 1418503926208288280L;

	private boolean areMinimumInfoDisplayed;

	
	private JTextField typeidentiteField;
		

	public TypeidentitePane() {
	}

	public TypeidentitePane(TypeidentiteModel model) {
		super(model);
	}

	public TypeidentitePane(TypeidentiteModel model, boolean areMinimumInfoDisplayed) {
		super(model);

		this.areMinimumInfoDisplayed = areMinimumInfoDisplayed;
	}

	public TypeidentitePane(YolaineViewType viewType) {
		super(viewType);
	}

	public TypeidentitePane(TypeidentiteModel model, YolaineViewType viewType) {
		super(model, viewType);
	}


	@Override
	protected TypeidentiteModel createDefaultModel() {
		return new DefaultTypeidentiteModel();
	}

	@Override
	protected void initView() {

		
		typeidentiteField = new JTextField();
		
		setLayout(new GridBagLayout());		
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		int row = 0;
		Insets insets = new Insets(2, 3, 2, 3);		

		row = 0;
		

			add(new JLabel("Type d'identité : "), new GridBagConstraints(0, row, 1, 1, 0.0,
					0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					insets, 0, 0));
			add(typeidentiteField, new GridBagConstraints(1, row++,1, 1, 1.0, 0.0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
					0, 0));		

		synchronizeViewType(getViewType());
	}	


	@Override
	protected void installViewListeners() {			

		typeidentiteField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getTypeidentite().getTypeIdentite();
				String newValue = typeidentiteField.getText();			

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.getTypeidentite().setTypeIdentite(newValue);
				}
			}
		});
	}
		
	@Override
	protected void initViewValues() {	

		typeidentiteField.setText(model.getTypeidentite().getTypeIdentite());
		   
	}

	@Override
	protected TypeidentiteListener createDefaultPropertyChangeHandler() {
		return new PropertyChangeHandler();
	}


	private class PropertyChangeHandler extends TypeidentiteAdapter {		

		

		@Override
		public void typeidentiteChanged(
				XSEvent<TypeidentiteEventPropertyName, TypeIdentite> evt) {
			String oldValue = typeidentiteField.getText();
			TypeIdentite newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {			
				typeidentiteField.setText(newValue.getTypeIdentite());
			}
		}		
	}

	@Override
	protected void synchronizeViewType(YolaineViewType viewType) {
		
		typeidentiteField.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		
	}

	@Override
	public String toString() {
		String text = "Type identité";

		if (model.getTypeidentite().getTypeIdentite() != null && !model.getTypeidentite().getTypeIdentite().equals("")){
			text += " " + model.getTypeidentite().getTypeIdentite() +"";
			return text;			
		}else{
		return text;
		}
		}


	public boolean areMinimumInfoDisplayed() {
		return areMinimumInfoDisplayed;
	}

}