package com.yolaine.client.ui.transaction.paiement.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.entity.transaction.*;
import com.yolaine.entity.client.TypeIdentite;


public class PaiementAdapter extends XSAdapter<PaiementEventPropertyName>
implements PaiementListener {

	public void numeroBanqueChanged(XSEvent<PaiementEventPropertyName, String> evt){		
	}

	public void numeroChequePaiementChanged(XSEvent<PaiementEventPropertyName, String> evt){	    	
	}

	public void typepaiementChanged(XSEvent<PaiementEventPropertyName, TypePaiement> evt){	    	
	}

	public void typeIdentiteChanged(XSEvent<PaiementEventPropertyName, TypeIdentite> evt){	    	
	}

	public void banquePaiementChanged(XSEvent<PaiementEventPropertyName, Banque> evt){	    	
	}

	public void dateventeChanged(XSEvent<PaiementEventPropertyName, String> evt){	    	
	}
	
	public void prixVenteReelChanged(XSEvent<PaiementEventPropertyName, String> evt){		 
	}
}