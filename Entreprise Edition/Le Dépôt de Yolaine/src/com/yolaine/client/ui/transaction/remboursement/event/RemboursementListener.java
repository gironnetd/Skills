package com.yolaine.client.ui.transaction.remboursement.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName;
import com.yolaine.entity.transaction.*;

public interface RemboursementListener extends XSListener<RemboursementEventPropertyName> {

	// ------------------------------------------------------------------------
	// 

	public void montantRembourseChanged(XSEvent<RemboursementEventPropertyName, String> evt);

	public void numeroChequeRemboursementChanged(XSEvent<RemboursementEventPropertyName, String> evt);

	public void banqueRemboursementChanged(XSEvent<RemboursementEventPropertyName, Banque> evt);

	public void typeremboursementChanged(XSEvent<RemboursementEventPropertyName, TypePaiement> evt);

	public void dateremboursementChanged(XSEvent<RemboursementEventPropertyName, String> evt);  
}