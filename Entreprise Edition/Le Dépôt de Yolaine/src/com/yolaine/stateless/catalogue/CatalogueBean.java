package com.yolaine.stateless.catalogue;

import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.*;
import com.yolaine.entity.client.*;
import com.yolaine.exception.ValidationException;
import com.yolaine.util.Constants;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is a facade for all catalog services.
 *
 * @author Antonio Goncalves
 */
@SuppressWarnings(value = "unchecked")
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Stateless(name = "CatalogSB", mappedName = "ejb/stateless/Catalogue")
public class CatalogueBean implements CatalogueRemote, CatalogueLocal {

	// ======================================
	// =             Attributs              =
	// ======================================
	@PersistenceContext(unitName = "YolainePU")
	private EntityManager em;

	private final String cname = this.getClass().getName();
	private Logger logger = Logger.getLogger(Constants.LOGGER_STATELESS);

	// ======================================
	// = Methodes publiques pour categorie  =
	// ======================================
	public Categorie createCategory(final Categorie category) {
		final String mname = "getNewCategory";
		logger.entering(cname, mname, category);

		// On s'assure de la validité des paramêtres
		if (category == null)
			throw new ValidationException("Category object is null");
		//Marque marque;
		//marque = em.find(Marque.class, category.getMarque().getName());
		// L'objet est persisté en base de données
		//category.setMarque(marque);
		em.persist(em.merge(category));

		logger.exiting(cname, mname, category);
		return category;
	}

	public Categorie findCategory(Long categoryId) {
		final String mname = "findCategory";
		logger.entering(cname, mname, categoryId);

		// On s'assure de la validité des paramêtres
		if (categoryId == null){
			//categoryId = "";
		}

		Categorie category;

		// On recherche l'objet à partir de son identifiant
		category = em.find(Categorie.class, categoryId);

		logger.exiting(cname, mname, category);
		return category;
	}

	public void deleteCategory(final Categorie category) {
		final String mname = "deleteCategory";
		logger.entering(cname, mname, category);

		// On s'assure de la validité des paramêtres
		if (category == null)
			throw new ValidationException("Category object is null");

		// On supprime l'objet de la base de données
		em.remove(em.merge(category));

		logger.exiting(cname, mname);
	}

	public Categorie updateCategory(final Categorie category) {
		final String mname = "updateCategory";
		logger.entering(cname, mname, category);

		// On s'assure de la validité des paramêtres
		if (category == null)
			throw new ValidationException("Category object is null");

		// On modifie l'objet de la base de données
		em.merge(category);

		logger.exiting(cname, mname, category);
		return category;
	}

	public List<Categorie> findCategories() {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Categorie> categories;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT c FROM Categorie c ORDER BY c.id");
		categories = query.getResultList();

		logger.exiting(cname, mname, categories.size());
		return categories;
	}

	// ======================================
	// = Methodes publiques pour produit    =
	// ======================================
	public Marque creerMarque(final Marque product) {
		final String mname = "createProduct";
		logger.entering(cname, mname, product);

		// On s'assure de la validité des paramêtres
		if (product == null)
			throw new ValidationException("Product object is null");

		// L'objet est persisté en base de données
		em.persist(product);

		logger.exiting(cname, mname, product);
		return product;
	}

	public Marque trouverMarque(Long productId) {
		final String mname = "findProduct";
		logger.entering(cname, mname, productId);

		// On s'assure de la validité des paramêtres
		//if (productId == null || productId.equals(""))
			//productId = "";

		//Marque product;

		// On recherche l'objet à partir de son identifiant
		Marque product = em.find(Marque.class, productId);

		logger.exiting(cname, mname, product);
		return product;
	}

	public void supprimerMarque(final Marque marque) {
		final String mname = "deleteProduct";
		logger.entering(cname, mname, marque);

		// On s'assure de la validité des paramêtres
		if (marque == null)
			throw new ValidationException("Product object is null");

		// On supprime l'objet de la base de données
		em.remove(em.merge(marque));

		logger.exiting(cname, mname);
	}

	public Marque majMarque(final Marque marque) {
		final String mname = "updateProduct";
		logger.entering(cname, mname, marque);

		// On s'assure de la validité des paramêtres
		if (marque == null)
			throw new ValidationException("Product object is null");
		
		// On modifie l'objet de la base de donnes
		em.merge(marque);

		logger.exiting(cname, mname, marque);
		return marque;
	}

	public List<Marque> trouverMarques() {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Marque> products;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT m FROM Marque m ORDER BY m.id");
		products = query.getResultList();

		logger.exiting(cname, mname, products.size());
		return products;
	}

	// ===========================================
	// =  Méthodes publiques pour manche  =
	// ===========================================  

	public Manche creerManche(final Manche manche) {
		final String mname = "creerManche";
		logger.entering(cname, mname, manche);

		// On s'assure de la validité des paramêtres
		if (manche == null)
			throw new ValidationException("L'objet TypeIdentite est nulle");

		em.persist(manche);       
		
		logger.exiting(cname, mname, manche);
		return manche;
	}

	public Manche trouverManche(final Long mancheId) {
		final String mname = "trouverTypeIdentite";
		logger.entering(cname, mname, mancheId);

		// On s'assure de la validité des paramêtres
		if (mancheId == null)
			throw new ValidationException("Identifiant invalide ");

		Manche manche;

		// On recherche l'objet à partir de son identifiant
		manche = em.find(Manche.class, mancheId);

		logger.exiting(cname, mname, manche);
		return manche;
	}

	public void supprimerManche(final Manche manche) {
		final String mname = "supprimerManche";
		logger.entering(cname, mname, manche);

		// On s'assure de la validité des paramêtres
		if (manche == null)
			throw new ValidationException("L'objet TypeIdentite est nulle");

		em.remove(em.merge(manche));



		logger.exiting(cname, mname);
	}

	public Manche majManche(final Manche manche) {
		final String mname = "mettre_à_jour_Manche";
		logger.entering(cname, mname, manche);

		// On s'assure de la validité des paramêtres
		if (manche == null)
			throw new ValidationException("l'objet TypeIdentite n'existe pas");        

		// On modifie l'objet de la base de données
		em.merge(manche);

		logger.exiting(cname, mname, manche);
		return manche;
	}

	public List<Manche> trouverManches() {
		final String mname = "trouverManches";
		logger.entering(cname, mname);

		Query query;
		List<Manche> manches;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT m FROM Manche m ORDER BY m.id");
		manches = query.getResultList();

		logger.exiting(cname, mname, manches.size());
		return manches;
	}

	// ===========================================
	// =  Methodes publiques pour couleur  =
	// ===========================================  

	public Couleur creerCouleur(final Couleur couleur) {
		final String mname = "creerCouleur";
		logger.entering(cname, mname, couleur);

		// On s'assure de la validité des paramêtres
		if (couleur == null)
			throw new ValidationException("L'objet TypeIdentite est nulle");

		em.persist(couleur);       

		logger.exiting(cname, mname, couleur);
		return couleur;
	}

	public Couleur trouverCouleur(final Long couleurId) {
		final String mname = "trouverCouleur";
		logger.entering(cname, mname, couleurId);

		// On s'assure de la validité des paramêtres
		if (couleurId == null)
			throw new ValidationException("Identifiant invalide ");

		Couleur couleur;

		// On recherche l'objet à partir de son identifiant
		couleur = em.find(Couleur.class, couleurId);

		logger.exiting(cname, mname, couleur);
		return couleur;
	}

	public void supprimerCouleur(final Couleur couleur) {
		final String mname = "supprimerCouleur";
		logger.entering(cname, mname, couleur);

		// On s'assure de la validité des paramêtres
		if (couleur == null)
			throw new ValidationException("L'objet Couleur est nulle");

		// On supprime l'objet de la base de données
		em.remove(em.merge(couleur));

		logger.exiting(cname, mname);
	}

	public Couleur majCouleur(final Couleur couleur) {
		final String mname = "mettre_à_jour_Manche";
		logger.entering(cname, mname, couleur);

		// On s'assure de la validité des paramêtres
		if (couleur == null)
			throw new ValidationException("l'objet Couleur n'existe pas");        

		// On modifie l'objet de la base de données
		em.merge(couleur);

		logger.exiting(cname, mname, couleur);
		return couleur;
	}

	public List<Couleur> trouverCouleurs() {
		final String mname = "trouverManches";
		logger.entering(cname, mname);

		Query query;
		List<Couleur> couleurs;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT c FROM Couleur c ORDER BY c.id");
		couleurs = query.getResultList();

		logger.exiting(cname, mname, couleurs.size());
		return couleurs;
	}


	// ======================================
	// =   Methodes publiques pour article  =
	// ======================================
	public Article createArticle( final Article article, final Marque marque, final Paiement paiement, final Remboursement remboursement) {
		final String mname = "createItem";
		logger.entering(cname, mname, article);

		// On s'assure de la validité des paramètres
		if (article.getCategorie().getName() == null){
			throw new ValidationException("Le type d'article n'est pas précisé.");
		}  else if (article.getCouleur1().getCouleur() == null){
			throw new ValidationException("La couleur 1 n'est pas précisée.");
		} else if (article.getCouleur2().getCouleur() == null){
			//throw new ValidationException("La couleur 2 n'est pas précisée.");
		}else if (article.getMontantDepot() == null || article.getMontantDepot().equals("")){
			throw new ValidationException("Le montant de l'article au dépôt n'est pas précisé.");
		} else if (article.getPrixVente() == null || article.getPrixVente().equals("")){
			throw new ValidationException("Le prix de vente de l'article n'est pas précisé.");
		} else if (article.getPourcentage() == null){		
		} 
		// L'objet est persisté en base de données
		
		article.setMarque(marque);
		article.setPaiement(paiement);
		article.setRemboursement(remboursement);
		
		em.persist(em.merge(article));		
		logger.exiting(cname, mname, article);
		return article;
	}

	public Article findArticle(final Long itemId) {
		final String mname = "findItem";
		logger.entering(cname, mname, itemId);

		// On s'assure de la validité des paramêtres


		Article article;

		// On recherche l'objet à partir de son identifiant
		article = em.find(Article.class, itemId);


		logger.exiting(cname, mname, article);
		return article;
	}

	public void deleteArticle(Long articleId) {
		final String mname = "deleteItem";
		logger.entering(cname, mname, articleId);
		//String s = String.valueOf(articleId);
		// Long l = Long.valueOf(s);
		// On s'assure de la validité des paramêtres
		System.out.println(" articleId : " + articleId);
		if (articleId == null)
			throw new ValidationException("Item object is null");
		Article article = new Article();
		article = em.find(Article.class, articleId);
		System.out.println(" Id de article : " + article.getId());
		// On supprime l'objet de la base de données
		em.remove(em.merge(article));

		logger.exiting(cname, mname);
	}

	public Article updateArticle(final Article article, final Marque marque, final Paiement paiement, final Remboursement remboursement) {
		final String mname = "updateItem";
		logger.entering(cname, mname, article);

		// On s'assure de la validité des paramêtres
		if (article == null)
			throw new ValidationException("L' article ne doit pas être nul.");
		if (marque == null)
			throw new ValidationException("La marque n'est pas précisée.");
		if(paiement == null)
			throw new ValidationException("Le paiement n'est pas précisé.");
		if(remboursement == null)
			throw new ValidationException("Le remboursment n'est pas précisé.");
		//  article.setProduct(product);
		article.setMarque(marque);
		article.setPaiement(paiement);
		article.setRemboursement(remboursement);
		// On modifie l'objet de la base de données
		//em.merge(article);
		em.merge(article);
		logger.exiting(cname, mname, article);
		return article;
	}

	public Article venteArticle(final Article article, final Paiement paiement) {
		final String mname = "venteArticle";
		logger.entering(cname, mname, article);
			if (article == null)
				throw new ValidationException("L' article ne doit pas être nul.");
			if(paiement == null)
				throw new ValidationException("Le paiement n'est pas précisé.");
			article.setPaiement(paiement);
		// On s'assure de la validité des paramêtres
			Article articleTmp;
			articleTmp = em.find(Article.class,article.getId());
			em.remove(em.merge(articleTmp.getPaiement()));
			article.setPaiement(paiement);
			em.merge(article);
			
		logger.exiting(cname, mname, article);
		return article;
	}

	public Article rembourserArticle(final Article article, final Remboursement remboursement) {
		final String mname = "updateItem";
		logger.entering(cname, mname, article);

		// On s'assure de la validité des paramêtres
		Article articleTmp;
		articleTmp = em.find(Article.class,article.getId());
		em.remove(em.merge(articleTmp.getRemboursement()));
		article.setRemboursement(remboursement);
		em.merge(article);
		logger.exiting(cname, mname, article);
		return article;
	}

	public List<Article> findArticles() {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT i FROM Article i ORDER BY i.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}

	public List<Article> findArticles(final Long depotId) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("select i from Article i where i.depot.id='" + depotId + "' order by i.id");
		articles = query.getResultList();
		for(Article article : articles){
			System.out.println(" eee / " + article.getPrixVente());
		}

		logger.exiting(cname, mname, articles.size());
		return articles;
	}

	public List<Article> findArticlesbySituation(final String situation) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("select i from Article i where i.situation.situation='" + situation + "' order by i.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}

	public List<Article> findArticlesbyMarque(final Long marqueId) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("select i from Article i where i.marque='" + marqueId + "' order by i.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}
	
	public List<Article> findArticlesbyMarqueAndCategorie(final Long marqueId, final Long categorieId) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("select i from Article i where i.marque.id='" + marqueId + "' and i.categorie.id='" + categorieId + "' order by i.marque.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}
	
	public List<Article> findArticlesbyCategorie(final Long categorieId) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("select i from Article i where i.categorie.id='" + categorieId + "' order by i.categorie.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}
	
	public List<Article> findArticlesbyCategorieAndMarque(final Long categorieId, final Long marqueId) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("select i from Article i where i.categorie.id='" + categorieId + "' and i.marque.id='" + marqueId + "' order by i.marque.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}
	
	public List<Article> findClientArticles(Long clientId) {
		final String mname = "findCategories";
		logger.entering(cname, mname);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT i FROM Article i WHERE i.depot.client.id=" + clientId + " ORDER BY i.depot.client.id");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}

	public List<Article> searchArticles(final String keyword) {
		final String mname = "searchItems";
		logger.entering(cname, mname, keyword);

		Query query;
		List<Article> articles;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT i FROM Article i WHERE UPPER(i.marque.id) LIKE :keyword OR UPPER(i.produit.id) LIKE :keyword ORDER BY i.produit.id");
		query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
		articles = query.getResultList();

		logger.exiting(cname, mname, articles.size());
		return articles;
	}

	// ======================================
	// =   Methodes publiques pour situation  =
	// ======================================

	public List<Situation> trouverSituations() {
		final String mname = "trouverSituations";
		logger.entering(cname, mname);

		Query query;
		List<Situation> situations;

		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT s FROM Situation s ORDER BY s.id");
		situations = query.getResultList();

		logger.exiting(cname, mname, situations.size());
		return situations;
	}

	// ======================================
	// =   Methodes publiques pour Type de paiement  =
	// ======================================

	public TypePaiement creerTypePaiement(final TypePaiement typePaiement) {
		final String mname = "creerTypePaiement";
		logger.entering(cname, mname, typePaiement);

		// On s'assure de la validité des paramêtres
		if (typePaiement == null)
			throw new ValidationException("L'objet TypeIdentite est nulle");

		em.persist(typePaiement);       

		logger.exiting(cname, mname, typePaiement);
		return typePaiement;
	}

	public TypePaiement trouverTypePaiement(final Long typePaiementId) {
		final String mname = "trouverCouleur";
		logger.entering(cname, mname, typePaiementId);

		// On s'assure de la validité des paramêtres
		if (typePaiementId == null)
			throw new ValidationException("Identifiant invalide ");

		TypePaiement typePaiement;

		// On recherche l'objet à partir de son identifiant
		typePaiement = em.find(TypePaiement.class, typePaiementId);

		logger.exiting(cname, mname, typePaiement);
		return typePaiement;
	}

	public void supprimerTypePaiement(final TypePaiement typePaiement) {
		final String mname = "supprimerTypePaiement";
		logger.entering(cname, mname, typePaiement);

		// On s'assure de la validité des paramêtres
		if (typePaiement == null)
			throw new ValidationException("L'objet Couleur est nulle");

		// On supprime l'objet de la base de données
		em.remove(em.merge(typePaiement));

		logger.exiting(cname, mname);
	}

	public TypePaiement majTypePaiement(final TypePaiement typePaiement) {
		final String mname = "majTypePaiement";
		logger.entering(cname, mname, typePaiement);

		// On s'assure de la validité des paramêtres
		if (typePaiement == null)
			throw new ValidationException("l'objet typePaiement n'existe pas");        

		// On modifie l'objet de la base de données
		em.merge(typePaiement);

		logger.exiting(cname, mname, typePaiement);
		return typePaiement;
	}


	public List<TypePaiement> trouverTypePaiements() {
		final String mname = "trouverTypePaiements";
		logger.entering(cname, mname);

		Query query;
		List<TypePaiement> typepaiements;
		System.out.println("trouverTypePaiements");
		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT t FROM TypePaiement t ORDER BY t.id");
		typepaiements = query.getResultList();

		logger.exiting(cname, mname, typepaiements.size());
		return typepaiements;
	}

	// ======================================
	// =   Methodes publiques pour Banque  =
	// ======================================

	public Banque creerBanque(final Banque banque) {
		final String mname = "creerBanque";
		logger.entering(cname, mname, banque);

		// On s'assure de la validité des paramêtres
		if (banque == null)
			throw new ValidationException("L'objet Banque est nulle");

		em.persist(banque);       

		logger.exiting(cname, mname, banque);
		return banque;
	}

	public Banque trouverBanque(final Long banqueId) {
		final String mname = "trouverBanque";
		logger.entering(cname, mname, banqueId);

		// On s'assure de la validité des paramêtres
		if (banqueId == null)
			throw new ValidationException("Identifiant invalide ");

		Banque banque;

		// On recherche l'objet à partir de son identifiant
		banque = em.find(Banque.class, banqueId);

		logger.exiting(cname, mname, banque);
		return banque;
	}

	public void supprimerBanque(final Banque banque) {
		final String mname = "supprimerBanque";
		logger.entering(cname, mname, banque);

		// On s'assure de la validité des paramêtres
		if (banque == null)
			throw new ValidationException("L'objet Couleur est nulle");

		// On supprime l'objet de la base de données
		em.remove(em.merge(banque));

		logger.exiting(cname, mname);
	}

	public Banque majBanque(final Banque banque) {
		final String mname = "majBanque";
		logger.entering(cname, mname, banque);

		// On s'assure de la validité des paramêtres
		if (banque == null)
			throw new ValidationException("l'objet banque n'existe pas");        

		// On modifie l'objet de la base de données
		em.merge(banque);

		logger.exiting(cname, mname, banque);
		return banque;
	}


	public List<Banque> trouverBanques() {
		final String mname = "trouverBanques";
		logger.entering(cname, mname);

		Query query;
		List<Banque> banques;
		System.out.println("trouverBanques");
		// On modifie l'objet de la base de données
		query = em.createQuery("SELECT b FROM Banque b ORDER BY b.id");
		banques = query.getResultList();

		logger.exiting(cname, mname, banques.size());
		return banques;
	}

	// ======================================
	// =           Methodes Privées         =
	// ======================================
}
