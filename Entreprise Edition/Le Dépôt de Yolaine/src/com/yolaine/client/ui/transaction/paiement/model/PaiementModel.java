package com.yolaine.client.ui.transaction.paiement.model;

import org.vstm.fwk.client.ui.xswing.core.model.XSModel;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;
import com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.event.PaiementListener;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.client.TypeIdentite;

public interface PaiementModel extends
XSModel<PaiementListener, PaiementEventPropertyName> {

	public Long getId();

	public Paiement getPaiement();
	
	public void setPaiement(Paiement paiement);
	
	public void setTypePaiement(TypePaiement typepaiement);

	public TypePaiement getTypePaiement();	

	public String getDatevente();

	public void setDatevente(String datevente);

	public Banque getBanquePaiement();

	public void setBanquePaiement(Banque banquePaiement);

	public TypeIdentite getTypeidentite();

	public void setTypeidentite(TypeIdentite typeidentite);

	public String getNumeroBanque();

	public void setNumeroBanque(String numeroBanque);

	public String getNumeroChequePaiement();

	public void setNumeroChequePaiement(String numeroChequePaiement);
	
	public String getPrixVenteReel();

	public void setPrixVenteReel(String prixVenteReel);
}