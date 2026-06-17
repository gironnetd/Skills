package com.yolaine.client.ui.transaction.remboursement.model;

import org.vstm.fwk.client.ui.xswing.core.model.XSModel;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementEventPropertyName;
import com.yolaine.client.ui.transaction.remboursement.event.RemboursementListener;
import com.yolaine.entity.transaction.*;

public interface RemboursementModel extends
XSModel<RemboursementListener, RemboursementEventPropertyName> {

	public Long getId();

	public Remboursement getRemboursement();
	
	public void setRemboursement(Remboursement remboursement);
	
	public String getDateremboursement();

	public void setDateremboursement(String dateremboursement);

	public Banque getBanqueRemboursement();

	public void setBanqueRemboursement(Banque banqueRemoursement);    

	public String getNumeroChequeRemboursement();

	public void setNumeroChequeRemboursement(String numeroChequeRemboursement);

	public String getMontantRembourse();

	public void setMontantRembourse(String montantRembourse);
	
	public TypePaiement getTypRemboursement();

	public void setTypeRemboursement(TypePaiement typeremboursement);
}