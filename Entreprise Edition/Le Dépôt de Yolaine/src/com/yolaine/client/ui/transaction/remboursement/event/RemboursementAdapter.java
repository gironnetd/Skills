package com.yolaine.client.ui.transaction.remboursement.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName;
import com.yolaine.entity.transaction.*;


public class RemboursementAdapter extends XSAdapter<RemboursementEventPropertyName>
implements RemboursementListener {   

	public void montantRembourseChanged(XSEvent<RemboursementEventPropertyName, String> evt){		 
	}

	public void numeroChequeRemboursementChanged(XSEvent<RemboursementEventPropertyName, String> evt){
	}

	public void banqueRemboursementChanged(XSEvent<RemboursementEventPropertyName, Banque> evt){
	}

	public void typeremboursementChanged(XSEvent<RemboursementEventPropertyName, TypePaiement> evt){		
	}

	public void dateremboursementChanged(XSEvent<RemboursementEventPropertyName, String> evt){		
	}
	
}