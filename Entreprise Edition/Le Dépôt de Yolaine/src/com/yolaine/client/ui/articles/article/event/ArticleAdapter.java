package com.yolaine.client.ui.articles.article.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.TypeIdentite;

public class ArticleAdapter extends XSAdapter<ArticleEventPropertyName> implements
        ArticleListener {

	
	public void identifiantChanged(XSEvent<ArticleEventPropertyName,String> evt) {		
	}
	
	public void clientChanged(XSEvent<ArticleEventPropertyName,String> evt) {
	}	
	  
	public void datedepotChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}	
	
	public void paiementChanged(XSEvent<ArticleEventPropertyName, Paiement> evt){		
	}

	public void remboursementChanged(XSEvent<ArticleEventPropertyName, Remboursement> evt){		
	}
	
	public void dateventeChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}
	
	public void dateremboursementChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}
	
	public void marqueChanged(XSEvent<ArticleEventPropertyName, Marque> evt) {		
	}
	
	public void montantdepotChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}
	
	 public void montantRembourseChanged(XSEvent<ArticleEventPropertyName, String> evt){		 
	 }
	
	public void prixventeChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}
	
	public void pourcentageChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}	
	
	public void situationChanged(XSEvent<ArticleEventPropertyName, Situation> evt) {		
	}
	
	public void soldeChanged(XSEvent<ArticleEventPropertyName, Boolean> evt) {		
	}
	
	public void tailleChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}
	
	public void commentaireChanged(XSEvent<ArticleEventPropertyName, String> evt) {		
	}
	
	public void typearticleChanged(XSEvent<ArticleEventPropertyName,Categorie> evt) {		
	}

	public void typepaiementChanged(XSEvent<ArticleEventPropertyName,TypePaiement> evt) {		
	}
	
	public void typeremboursementChanged(XSEvent<ArticleEventPropertyName,TypePaiement> evt) {		
	}
	
	public void typeIdentiteChanged(XSEvent<ArticleEventPropertyName, TypeIdentite> evt){		
	}	
	
	public void numeroBanqueChanged(XSEvent<ArticleEventPropertyName, String> evt){		
	}
	
	public void numeroChequePaiementChanged(XSEvent<ArticleEventPropertyName, String> evt){		
	}
	
	public void numeroChequeRemboursementChanged(XSEvent<ArticleEventPropertyName, String> evt){		
	}
	
	public void versionChanged(XSEvent<ArticleEventPropertyName, Integer> evt) {		
	}
	
	public void couleur1Changed(XSEvent<ArticleEventPropertyName, Couleur> evt) {		
	}
	
	public void couleur2Changed(XSEvent<ArticleEventPropertyName, Couleur> evt) {		
	}
	
	public void banquePaiementChanged(XSEvent<ArticleEventPropertyName, Banque> evt) {		
	}
	
	public void banqueRemboursementChanged(XSEvent<ArticleEventPropertyName, Banque> evt) {		
	}
	
	public void mancheChanged(XSEvent<ArticleEventPropertyName, Manche> evt) {	
	}    
   
}