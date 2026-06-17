package com.yolaine.client.ui.articles.article.model;


import java.util.Date;

import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName;
import com.yolaine.client.ui.articles.article.event.ArticleListener;
import com.yolaine.client.ui.transaction.paiement.model.PaiementModel;
import com.yolaine.client.ui.transaction.remboursement.model.RemboursementModel;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;


public interface ArticleModel extends XSModel<ArticleListener, ArticleEventPropertyName> {

	public Article getArticle();

	public void setArticle(Article article);

	public Depot getDepot();

	public void setDepot(Depot depot);

	public Long getIdentifierToFind();

	public void setIdentifierToFind(Long identifier);

	public Long getId();

	public void setId(Long id);

	public int getVersion();

	public void setVersion(int version);

	public Client getClientArticle();

	public void setClientArticle(Client clientarticle);

	public Categorie getCategorie();

	public void setCategorie(Categorie typearticle);

	public String getDateDepot();

	public void setDateDepot(String dateDepot);

	public Marque getMarque();

	public void setMarque(Marque marque);

	public Couleur getCouleur1();

	public void setCouleur1(Couleur couleur1);

	public Couleur getCouleur2();

	public void setCouleur2(Couleur couleur2);

	public String getTaille();

	public void setTaille(String taille);

	public Manche getManche();

	public void setManche(Manche manche);

	public String getMontantDepot();

	public void setMontantDepot(String montantdepot);

	public void setMontantDepot(String montantdepot,boolean update);

	public String getPrixVente();

	public void setPrixVente(String prixvente);

	public Situation getSituation();

	public void setSituation(Situation situation);

	public boolean isSolde();

	public void setSolde(boolean solde);

	public String getPourcentage();

	public void setPourcentage(String pourcentage);

	public String getTexte();

	public void setTexte(String texte);	
	
	public Paiement getPaiement();
	
	public void setPaiement(Paiement paiement);
	
	public Remboursement getRemboursement();
	
	public void setRemboursement(Remboursement remboursement);
	
    public PaiementModel getPaiementModel();
    
    public void setPaiementModel(PaiementModel paiementModel);
    
    public RemboursementModel getRemboursementModel();
    
    public void setRemboursementModel(RemboursementModel remboursementModel);
}