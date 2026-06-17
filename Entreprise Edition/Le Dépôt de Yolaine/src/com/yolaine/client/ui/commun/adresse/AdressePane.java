package com.yolaine.client.ui.commun.adresse;


import static com.yolaine.client.ui.util.YolaineViewType.*;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.ui.commun.adresse.event.AdresseAdapter;
import com.yolaine.client.ui.commun.adresse.event.AdresseEventPropertyName;
import com.yolaine.client.ui.commun.adresse.event.AdresseListener;
import com.yolaine.client.ui.commun.adresse.model.AdresseModel;
import com.yolaine.client.ui.commun.adresse.model.DefaultAdresseModel;
import com.yolaine.client.ui.util.YolaineComponentPane;
import com.yolaine.client.ui.util.YolaineViewType;


public class AdressePane
extends
YolaineComponentPane<AdresseModel, AdresseListener, AdresseEventPropertyName> {

	private static final long serialVersionUID = -3015488093324282217L;


	private JTextField adresse1Field;
	private JTextField adresse2Field;
	private JTextField codepostalField; 
	private JTextField villeField;    


	public AdressePane() {
		super();
	}

	public AdressePane(AdresseModel model) {
		super(model);
	}

	public AdressePane(YolaineViewType viewType) {
		super(viewType);
	}

	public AdressePane(AdresseModel model, YolaineViewType viewType) {
		super(model, viewType);
	}


	@Override
	protected AdresseModel createDefaultModel() {
		return new DefaultAdresseModel();
	}

	@Override
	protected void initView() {
		adresse1Field = new JTextField();
		adresse2Field = new JTextField();
		villeField = new JTextField();        
		codepostalField = new JTextField();        

		setLayout(new GridBagLayout());
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		int row = 0;
		Insets insets = new Insets(2, 5, 2, 5);

		add(new JLabel("Adresse 1 : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(adresse1Field, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));

		add(new JLabel("Adresse 2 : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(adresse2Field, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));

		add(new JLabel("Code postal : "), new GridBagConstraints(0, row, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				insets, 0, 0));
		add(codepostalField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));

		add(new JLabel("Ville : "), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
				0, 0));
		add(villeField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
				0, 0));                

		synchronizeViewType(getViewType());
	}

	@Override
	protected void installViewListeners() {
		adresse1Field.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getAdresse1();
				String newValue = adresse1Field.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setAdresse1(newValue);
				}
			}

		});

		adresse2Field.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getAdresse2();
				String newValue = adresse2Field.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setAdresse2(newValue);
				}
			}

		});

		codepostalField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getCodepostal();
				String newValue = codepostalField.getText();


				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setCodepostal(newValue);
				}
			}

		});

		villeField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				String oldValue = model.getVille();
				String newValue = villeField.getText();

				if (!ObjectUtils.equals(oldValue, newValue)) {
					model.setVille(newValue);
				}
			}

		});  

	}

	@Override
	protected void initViewValues() {
		adresse1Field.setText(model.getAdresse1());		
		adresse2Field.setText(model.getAdresse2());
		codepostalField.setText(model.getCodepostal());
		villeField.setText(model.getVille());       
		        
	}

	@Override
	protected AdresseListener createDefaultPropertyChangeHandler() {
		return new PropertyChangeHandler();
	}


	private class PropertyChangeHandler extends AdresseAdapter {

		@Override
		public void adresse1Changed(XSEvent<AdresseEventPropertyName, String> evt) {
			String oldValue = adresse1Field.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				adresse1Field.setText(newValue);
			}
		}

		@Override
		public void adresse2Changed(XSEvent<AdresseEventPropertyName, String> evt) {
			String oldValue = adresse2Field.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				adresse2Field.setText(newValue);
			}
		}

		@Override
		public void codepostalChanged(XSEvent<AdresseEventPropertyName, String> evt) {
			String oldValue = codepostalField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				codepostalField.setText(newValue);
			}
		}   
		
		@Override
		public void villeChanged(XSEvent<AdresseEventPropertyName, String> evt) {
			String oldValue = villeField.getText();
			String newValue = evt.getNewValueAsParameterizedType();

			if (!ObjectUtils.equals(oldValue, newValue)) {
				villeField.setText(newValue);
			}
		}     	  

	}


	@Override
	protected void synchronizeViewType(YolaineViewType viewType) {
		adresse1Field.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		adresse2Field.setEditable(viewType != LIRE && viewType != SUPPRIMER);
		villeField.setEditable(viewType != LIRE && viewType != SUPPRIMER);        
		codepostalField.setEditable(viewType != LIRE && viewType != SUPPRIMER);        
	}    
}