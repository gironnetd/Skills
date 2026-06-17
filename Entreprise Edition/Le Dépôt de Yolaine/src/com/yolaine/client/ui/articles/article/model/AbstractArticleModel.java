package com.yolaine.client.ui.articles.article.model;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import static com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName.*;

import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.articles.article.event.ArticleListener;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.TypeIdentite;


public abstract class AbstractArticleModel extends
AbstractXSModel<ArticleListener, ArticleEventPropertyName> implements
ArticleModel {

	@Override
	@SuppressWarnings("unchecked")
	protected void listenerMethodCaller(ArticleListener listener,
			XSEvent<ArticleEventPropertyName, ?> evt) {
		ArticleEventPropertyName propertyName = evt.getPropertyNameEnumType();

		if (propertyName == IDENTIFIANT_CHANGED) {
			listener
			.identifiantChanged((XSEvent<ArticleEventPropertyName,String>) evt);
		} else if (propertyName == CLIENT_ID_CHANGED) {
			listener.clientChanged((XSEvent<ArticleEventPropertyName, String>) evt);
		} else if (propertyName == TYPEARTICLE_CHANGED) {
			listener
			.typearticleChanged((XSEvent<ArticleEventPropertyName, Categorie>) evt);
		}else if (propertyName == DATEDEPOT_CHANGED) {
			listener
			.datedepotChanged((XSEvent<ArticleEventPropertyName, String>) evt);
		} else if (propertyName == MONTANTDEPOT_CHANGED) {
			listener
			.montantdepotChanged((XSEvent<ArticleEventPropertyName,String>) evt);
		}else if (propertyName == PRIXVENTE_CHANGED) {
			listener.prixventeChanged((XSEvent<ArticleEventPropertyName, String>) evt);
		}else if (propertyName == MARQUE_CHANGED) {
			listener
			.marqueChanged((XSEvent<ArticleEventPropertyName, Marque>) evt);
		}  else if (propertyName == TAILLE_CHANGED) {
			listener
			.tailleChanged((XSEvent<ArticleEventPropertyName, String>) evt);
		}else if (propertyName == COULEUR_1_CHANGED) {
			listener
			.couleur1Changed((XSEvent<ArticleEventPropertyName, Couleur>) evt);
		} else if (propertyName == COULEUR_2_CHANGED) {
			listener
			.couleur2Changed((XSEvent<ArticleEventPropertyName, Couleur>) evt);
		} else if (propertyName == MANCHE_CHANGED) {
			listener.mancheChanged((XSEvent<ArticleEventPropertyName, Manche>) evt);
		}else if (propertyName == SOLDE_CHANGED) {
			listener
			.soldeChanged((XSEvent<ArticleEventPropertyName, Boolean>) evt);
		}else if (propertyName == POURCENTAGE_CHANGED) {
			listener
			.pourcentageChanged((XSEvent<ArticleEventPropertyName, String>) evt);
		} else if (propertyName == SITUATION_CHANGED) {
			listener
			.situationChanged((XSEvent<ArticleEventPropertyName, Situation>) evt);
		} else if (propertyName == TEXTE_CHANGED) {
			listener.commentaireChanged((XSEvent<ArticleEventPropertyName, String>) evt);
		}  else if (propertyName == VERSION_CHANGED) {
			listener
			.versionChanged((XSEvent<ArticleEventPropertyName, Integer>) evt);
		}  else if (propertyName == PAIEMENT_CHANGED) {
			listener
			.paiementChanged((XSEvent<ArticleEventPropertyName, Paiement>) evt);
		}  else if (propertyName == REMBOURSEMENT_CHANGED) {
			listener
			.remboursementChanged((XSEvent<ArticleEventPropertyName, Remboursement>) evt);
		}
		
	}    
}