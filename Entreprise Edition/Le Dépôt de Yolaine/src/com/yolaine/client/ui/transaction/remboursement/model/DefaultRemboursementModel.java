package com.yolaine.client.ui.transaction.remboursement.model;

import static com.yolaine.client.ui.transaction.remboursement.event.RemboursementEventPropertyName.*;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;

public class DefaultRemboursementModel extends AbstractRemboursementModel {

	private static final long serialVersionUID = 1602655452741754011L;


	private Remboursement remboursement;



	public DefaultRemboursementModel() {
		this(new Remboursement());
	}

	public DefaultRemboursementModel(Remboursement remboursement) {
		setRemboursement(remboursement);
		
	}	

	public Remboursement getRemboursement() {
		return remboursement;
	}

	public void setRemboursement(Remboursement remboursement) {
		
		if (remboursement == null) {
			throw new IllegalArgumentException("La banque ne doît pas être nul.");
		}

		Remboursement oldValue = this.remboursement;
		Remboursement newValue = remboursement;

		this.remboursement = remboursement;

		if (oldValue != null) {           
			fireXSChanged(this, TYPEREMBOURSEMENT_CHANGED, oldValue.getTyperemboursement(),
					newValue.getTyperemboursement());
			fireXSChanged(this, BANQUEREMBOURSEMENT_CHANGED, oldValue.getBanqueRemboursement(),
					newValue.getBanqueRemboursement());

			fireXSChanged(this, NUMEROCHEQUEREMBOURSEMENT_CHANGED, oldValue.getNumeroChequeRemboursement(),
					newValue.getNumeroChequeRemboursement());
			fireXSChanged(this, MONTANTREMBOURSE_CHANGED, oldValue.getMontantRembourse(),
					newValue.getMontantRembourse());

		}
	}

	public Long getId() { return remboursement.getId(); }

	public String getNumeroChequeRemboursement() {
		return remboursement.getNumeroChequeRemboursement();
	}

	public void setNumeroChequeRemboursement(String numeroChequeRemboursement) {
		Object oldValue = remboursement.getNumeroChequeRemboursement();
		Object newValue = numeroChequeRemboursement;

		remboursement.setNumeroChequeRemboursement(numeroChequeRemboursement);

		fireXSChanged(this, NUMEROCHEQUEREMBOURSEMENT_CHANGED, oldValue, newValue);
	}

	public Banque getBanqueRemboursement() {	
		return remboursement.getBanqueRemboursement();
	}

	public void setBanqueRemboursement(Banque banqueRemboursement) {
		Object oldValue = remboursement.getBanqueRemboursement();
		Object newValue = banqueRemboursement;

		remboursement.setBanqueRemboursement(banqueRemboursement);

		fireXSChanged(this, BANQUEREMBOURSEMENT_CHANGED, oldValue, newValue);
	}

	public TypePaiement getTypRemboursement() {
		return remboursement.getTyperemboursement();
	}

	public void setTypeRemboursement(TypePaiement typeremboursement) {
		Object oldValue = remboursement.getTyperemboursement();
		Object newValue = typeremboursement;

		remboursement.setTyperemboursement(typeremboursement);

		fireXSChanged(this, TYPEREMBOURSEMENT_CHANGED, oldValue, newValue);		
	}

	public String getDateremboursement() {
		return remboursement.getDateremboursement();
	}

	public void setDateremboursement(String dateremboursement) {		
		Object oldValue = remboursement.getDateremboursement();
		Object newValue = dateremboursement;

		remboursement.setDateremboursement(dateremboursement);

		fireXSChanged(this, DATEREMBOURSEMENT_CHANGED, oldValue, newValue);		
	}

	public String getMontantRembourse() {
		return remboursement.getMontantRembourse();
	}

	public void setMontantRembourse(String montantRembourse) {
		Object oldValue = remboursement.getMontantRembourse();
		Object newValue = montantRembourse;

		remboursement.setMontantRembourse(montantRembourse);

		fireXSChanged(this, MONTANTREMBOURSE_CHANGED, oldValue, newValue);		
	}


	public void reset() {
		setDateremboursement(null);		
		setTypeRemboursement(new TypePaiement());		
		setBanqueRemboursement(new Banque());
	}

}