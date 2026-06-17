package com.yolaine.client.ui.articles.article.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.TypeIdentite;

public interface ArticleListener extends XSListener<ArticleEventPropertyName> {

	// ------------------------------------------------------------------------
	// 

	public void identifiantChanged(XSEvent<ArticleEventPropertyName, String> evt);

	public void clientChanged(XSEvent<ArticleEventPropertyName, String> evt);

	public void typearticleChanged(XSEvent<ArticleEventPropertyName, Categorie> evt); 

	public void paiementChanged(XSEvent<ArticleEventPropertyName, Paiement> evt);

	public void remboursementChanged(XSEvent<ArticleEventPropertyName, Remboursement> evt); 

	public void datedepotChanged(XSEvent<ArticleEventPropertyName, String> evt);  

	public void marqueChanged(XSEvent<ArticleEventPropertyName, Marque> evt);   

	public void montantdepotChanged(XSEvent<ArticleEventPropertyName, String> evt);   

	public void prixventeChanged(XSEvent<ArticleEventPropertyName, String> evt);

	public void tailleChanged(XSEvent<ArticleEventPropertyName, String> evt);

	public void couleur1Changed(XSEvent<ArticleEventPropertyName, Couleur> evt);

	public void couleur2Changed(XSEvent<ArticleEventPropertyName, Couleur> evt);    

	public void mancheChanged(XSEvent<ArticleEventPropertyName, Manche> evt);    

	public void soldeChanged(XSEvent<ArticleEventPropertyName, Boolean> evt);

	public void pourcentageChanged(XSEvent<ArticleEventPropertyName, String> evt);   

	public void situationChanged(XSEvent<ArticleEventPropertyName, Situation> evt);   

	public void commentaireChanged(XSEvent<ArticleEventPropertyName, String> evt);  

	public void versionChanged(XSEvent<ArticleEventPropertyName, Integer> evt);
}