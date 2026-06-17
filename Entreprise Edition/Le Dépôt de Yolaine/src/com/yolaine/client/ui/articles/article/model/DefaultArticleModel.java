package com.yolaine.client.ui.articles.article.model;

import static com.yolaine.client.ui.articles.article.event.ArticleEventPropertyName.*;
import static com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName.TYPEIDENTITE_CHANGED;

import com.yolaine.client.ui.transaction.paiement.model.DefaultPaiementModel;
import com.yolaine.client.ui.transaction.paiement.model.PaiementModel;
import com.yolaine.client.ui.transaction.remboursement.model.DefaultRemboursementModel;
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

public class DefaultArticleModel extends AbstractArticleModel {

	private static final long serialVersionUID = -3870151937579538411L;


	private Article article;
	private PaiementModel paiementModel;
	private RemboursementModel remboursementModel ;
	private Long identifierToFind;

	public DefaultArticleModel() {
		Article article = new Article();
		Client client = new Client();
		Marque marque = new Marque("");
		Categorie typearticle = new Categorie("","");
		//TypePaiement typepaiement = new TypePaiement("");
		//Banque banque = new Banque("");
		Couleur couleur = new Couleur("");
		Manche manche = new Manche("");
		Situation situation = new Situation("");
		Paiement paiement = new Paiement();
		Remboursement remboursement = new Remboursement();
		article.setClientArticle(client);
		article.setSituation(situation);
		article.setMarque(marque);
		article.setManche(manche);
		article.setCouleur1(couleur);
		article.setCouleur2(couleur);
		article.setCategorie(typearticle);
		article.setPaiement(paiement);
		article.setRemboursement(remboursement);
		article.setMontantDepot("");
		article.setPrixVente("");
		article.setPourcentage("");
		setPaiementModel(new DefaultPaiementModel());
		setRemboursementModel(new DefaultRemboursementModel());	
		setArticle(article);
		//this.paiementModel = getPaiementModel();
		//this.remboursementModel  = getRemboursementModel();
		//this.paiementModel = new DefaultPaiementModel(new Paiement());
		//this.remboursementModel  = new DefaultRemboursementModel(new Remboursement());
	}

	public DefaultArticleModel(Article article) {
		this.article = article;
		setArticle(article);		
	}	
	
	public void setPaiementModel(PaiementModel paiementModel) {
		this.paiementModel = paiementModel;
	}

	public void setRemboursementModel(RemboursementModel remboursementModel) {
		this.remboursementModel = remboursementModel;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		if (article == null) {
			throw new IllegalArgumentException("item must be non null");
		}

		Article oldValue = this.article;
		Article newValue = article;

		this.article = article;

		if (oldValue != null) {
			fireXSChanged(this, IDENTIFIANT_CHANGED, oldValue.getId(), newValue
					.getId());
			fireXSChanged(this, CLIENT_ID_CHANGED, oldValue.getClientArticle(), newValue
					.getClientArticle());
			fireXSChanged(this, COULEUR_1_CHANGED, oldValue.getCouleur1(),
					newValue.getCouleur1());
			fireXSChanged(this, COULEUR_2_CHANGED, oldValue.getCouleur2(),
					newValue.getCouleur2());
			fireXSChanged(this, DATEDEPOT_CHANGED, oldValue.getDateDepot(),
					newValue.getDateDepot());
			fireXSChanged(this, MANCHE_CHANGED, oldValue.getManche(), newValue
					.getManche());
			fireXSChanged(this, MARQUE_CHANGED, oldValue.getMarque(), newValue
					.getMarque());
			fireXSChanged(this, MONTANTDEPOT_CHANGED, oldValue.getMontantDepot(),
					newValue.getMontantDepot());
			fireXSChanged(this, POURCENTAGE_CHANGED, oldValue.getPourcentage(),
					newValue.getPourcentage());
			fireXSChanged(this, PRIXVENTE_CHANGED, oldValue.getPrixVente(),
					newValue.getPrixVente());
			fireXSChanged(this, SITUATION_CHANGED, oldValue.getSituation(),
					newValue.getSituation());
			fireXSChanged(this, SOLDE_CHANGED, oldValue.isSolde(), newValue
					.isSolde());
			fireXSChanged(this, TAILLE_CHANGED, oldValue.getTaille(), newValue
					.getTaille());
			fireXSChanged(this, TEXTE_CHANGED, oldValue.getTexte(),
					newValue.getTexte());
			fireXSChanged(this, TYPEARTICLE_CHANGED, oldValue.getCategorie(),
					newValue.getCategorie());
			fireXSChanged(this, VERSION_CHANGED, oldValue.getVersion(),
					newValue.getVersion());
			fireXSChanged(this, PAIEMENT_CHANGED, oldValue.getPaiement(),
					newValue.getPaiement());
			fireXSChanged(this, REMBOURSEMENT_CHANGED, oldValue.getRemboursement(),
					newValue.getRemboursement());
			if(paiementModel == null){
				paiementModel = new DefaultPaiementModel(article.getPaiement());
			}else{
				paiementModel.setPaiement(article.getPaiement());
			}
			if (remboursementModel == null){
				remboursementModel = new DefaultRemboursementModel(article.getRemboursement());
			} else{
				remboursementModel.setRemboursement(article.getRemboursement());
			}
		}
	}


	public Long getIdentifierToFind() {
		return identifierToFind;
	}

	public void setIdentifierToFind(Long identifierToFind) {
		Object oldValue = this.identifierToFind;
		Object newValue = identifierToFind;

		this.identifierToFind = identifierToFind;

		fireXSChanged(this, IDENTIFIANT_CHANGED, oldValue, newValue);
	}

	public Long getIdentifier() {
		return article.getId();
	}

	public Long getId() {
		return article.getId();
	}

	public void setId(Long id) {
		Object oldValue = article.getId();
		Object newValue = id;

		article.setId(id);

		fireXSChanged(this, IDENTIFIANT_CHANGED, oldValue, newValue);
	}


	public int getVersion() {		
		return article.getVersion();
	}


	public void setVersion(int version) {	
		Object oldValue = article.getVersion();
		Object newValue = version;

		article.setVersion(version);

		fireXSChanged(this, VERSION_CHANGED, oldValue, newValue);
	}


	public Client getClientArticle() {
		return article.getClientArticle();
	}

	public void setClientArticle(Client clientarticle) {
		Object oldValue = article.getClientArticle();
		Object newValue = clientarticle;

		article.setClientArticle(clientarticle);

		fireXSChanged(this, CLIENT_ID_CHANGED, oldValue, newValue);
	}

	public Categorie getCategorie() {
		return article.getCategorie();
	}


	public void setCategorie(Categorie typearticle) {
		Object oldValue = article.getCategorie();
		Object newValue = typearticle;

		article.setCategorie(typearticle);

		fireXSChanged(this, TYPEARTICLE_CHANGED, oldValue, newValue);
	}

	public String getDateDepot() {
		return article.getDateDepot();
	}

	public void setDateDepot(String datedepot) {
		Object oldValue = article.getDateDepot();
		Object newValue = datedepot;
		article.setDateDepot(datedepot);

		fireXSChanged(this, DATEDEPOT_CHANGED, oldValue, newValue);
	}

	public Marque getMarque() {		
		return article.getMarque();
	}

	public void setMarque(Marque marque) {
		Object oldValue = article.getMarque();
		Object newValue = marque;

		article.setMarque(marque);

		fireXSChanged(this, MARQUE_CHANGED, oldValue, newValue);
	}

	public Couleur getCouleur1() {		
		return article.getCouleur1();
	}

	public void setCouleur1(Couleur couleur1) {
		Object oldValue = article.getCouleur1();
		Object newValue = couleur1;

		article.setCouleur1(couleur1);

		fireXSChanged(this, COULEUR_1_CHANGED, oldValue, newValue);
	}


	public Couleur getCouleur2() {		
		return article.getCouleur2();
	}


	public void setCouleur2(Couleur couleur2) {
		Object oldValue = article.getCouleur2();
		Object newValue = couleur2;

		article.setCouleur2(couleur2);

		fireXSChanged(this, COULEUR_2_CHANGED, oldValue, newValue);
	}

	
	
	
	
	public String getTaille() {		
		return article.getTaille();
	}


	public void setTaille(String taille) {
		Object oldValue = article.getTaille();
		Object newValue = taille;

		article.setTaille(taille);

		fireXSChanged(this, TAILLE_CHANGED, oldValue, newValue);
	}


	public Manche getManche() {		
		return article.getManche();
	}


	public void setManche(Manche manche) {
		Object oldValue = article.getManche();
		Object newValue = manche;

		article.setManche(manche);

		fireXSChanged(this, MANCHE_CHANGED, oldValue, newValue);
	}


	public String getMontantDepot() {
		return String.valueOf(article.getMontantDepot());
	}


	public void setMontantDepot(String montantdepot) {
		Object oldValue = article.getMontantDepot();
		Object newValue = montantdepot;

		article.setMontantDepot(montantdepot);

		fireXSChanged(this, MONTANTDEPOT_CHANGED, oldValue, newValue);       

		updatePrixVente();
	}

	public void setMontantDepot(String montantdepot, boolean update) {
		Object oldValue = article.getMontantDepot();
		Object newValue = montantdepot;

		article.setMontantDepot(montantdepot);

		fireXSChanged(this, MONTANTDEPOT_CHANGED, oldValue, newValue);

		if(update == true){
			updatePrixVente();
		} else{

		}

	}

	public String getPrixVente() {
		return article.getPrixVente();
	}


	public void setPrixVente(String prixvente) {
		Object oldValue = article.getPrixVente();
		Object newValue = prixvente;

		article.setPrixVente(prixvente);

		fireXSChanged(this, PRIXVENTE_CHANGED, oldValue, newValue);
	}


	private void updatePrixVente() {
		Object oldValue = article.getPrixVente();
		Object newValue = null;
		article.calculerPrixVente();

		newValue = article.getPrixVente();
		fireXSChanged(this, PRIXVENTE_CHANGED, oldValue, newValue);

	}

	public Situation getSituation() {		
		return article.getSituation();
	}


	public void setSituation(Situation situation) {
		Object oldValue = article.getSituation();
		Object newValue = situation;

		article.setSituation(situation);

		fireXSChanged(this, SITUATION_CHANGED, oldValue, newValue);
	}


	public boolean isSolde() {		
		return article.isSolde();
	}


	public void setSolde(boolean solde) {
		Object oldValue = article.isSolde();
		Object newValue = solde;

		article.setSolde(solde);

		fireXSChanged(this, SOLDE_CHANGED, oldValue, newValue);
	}


	public String getPourcentage() {		
		return article.getPourcentage();
	}


	public void setPourcentage(String pourcentage) {
		Object oldValue = article.getPourcentage();
		Object newValue = pourcentage;

		article.setPourcentage(pourcentage);

		fireXSChanged(this, POURCENTAGE_CHANGED, oldValue, newValue);
	}

	

	public String getTexte() {		
		return article.getTexte();
	}


	public void setTexte(String texte) {
		Object oldValue = article.getTexte();
		Object newValue = texte;

		article.setTexte(texte);

		fireXSChanged(this, TEXTE_CHANGED, oldValue, newValue);
	}

	public Depot getDepot() {
		return article.getDepot();
	}

	public void setDepot(Depot depot) {	
		Object oldValue = article.getDepot();
		Object newValue = depot;

		article.setDepot(depot);

		fireXSChanged(this, DEPOT_CHANGED, oldValue, newValue);		
	}
	
	public Paiement getPaiement() {		
		return article.getPaiement();
	}

	
	public void setPaiement(Paiement paiement) {
		Object oldValue = article.getPaiement();
		Object newValue = paiement;

		article.setPaiement(paiement);

		fireXSChanged(this, PAIEMENT_CHANGED, oldValue, newValue);		
	}
	
	public Remboursement getRemboursement() {
		return article.getRemboursement();
	}

	
	public void setRemboursement(Remboursement remboursement) {
		Object oldValue = article.getRemboursement();
		Object newValue = remboursement;

		article.setRemboursement(remboursement);

		fireXSChanged(this, REMBOURSEMENT_CHANGED, oldValue, newValue);		
	}	
	
	public PaiementModel getPaiementModel() {		
		return paiementModel;
	}
	
	public RemboursementModel getRemboursementModel() {		
		return remboursementModel;
	}
	
	public void reset(){		
		setCategorie(new Categorie());
		setMarque(new Marque());
		setCouleur1(new Couleur());
		setCouleur2(new Couleur());
		setManche(new Manche());
		setPaiement(new Paiement());
		setRemboursement(new Remboursement());
		setSolde(false);				
		setMontantDepot(null);
		setPrixVente(null);
		setPourcentage(null);
		setSituation(new Situation());
		setTexte(null);
		setTaille(null);
	}
}