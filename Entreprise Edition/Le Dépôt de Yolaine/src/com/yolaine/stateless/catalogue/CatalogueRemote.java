package com.yolaine.stateless.catalogue;

import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.*;
import com.yolaine.entity.client.*;
import javax.ejb.Remote;
import java.util.List;

/**
 * @author Antonio Goncalves
 */
@Remote
public interface CatalogueRemote {

    // ======================================
    // =          Methodes publiques        = 
    // ======================================
    Categorie createCategory(Categorie category);

    Categorie findCategory(Long categoryId);

    void deleteCategory(Categorie category);

    Categorie updateCategory(Categorie category);

    List<Categorie> findCategories();

    //List<Categorie> findCategoriesbyMarque(String marqueId);
    
    Marque creerMarque(Marque product);

    Marque trouverMarque(Long productId);

    void supprimerMarque(Marque product);

    Marque majMarque(Marque marque);

    List<Marque> trouverMarques();
    
    //List <Marque> trouverMarquesByCategorie(String categorieId);

    Article createArticle(Article article, Marque marque,Paiement paiement, Remboursement remboursement);

    Article findArticle(Long itemId);

    void deleteArticle(Long articleId);

    Article updateArticle(Article article, Marque marque,Paiement paiement, Remboursement remboursement);

    Article venteArticle(Article article,Paiement paiement);
    
    Article rembourserArticle(Article article, Remboursement remboursement);
    
    List<Article> findArticles();
    
    List<Article> findArticles(Long depotId);
    
    List<Article> findArticlesbySituation(String situation);
    
    List<Article> findClientArticles(Long clientId);
    
    List<Article> findArticlesbyMarque(Long marqueId);
    
    List<Article> findArticlesbyCategorie(Long categorieId);
    
    List<Article> findArticlesbyMarqueAndCategorie(Long marqueId, Long categorieId);
    
    List<Article> findArticlesbyCategorieAndMarque(Long categorieId, Long marqueId);
    
    Manche creerManche(Manche manche);

    Manche trouverManche(Long productId);

    void supprimerManche(Manche product);

    Manche majManche(Manche manche);

    List<Manche> trouverManches();
    
    Couleur creerCouleur(Couleur couleur);

    Couleur trouverCouleur(Long couleurId);

    void supprimerCouleur(Couleur couleur);

    Couleur majCouleur(Couleur couleur);

    List<Couleur> trouverCouleurs();
    
    List<Situation> trouverSituations();
    
    TypePaiement creerTypePaiement(TypePaiement typePaiement);

    TypePaiement trouverTypePaiement(Long typePaiement);

    void supprimerTypePaiement(TypePaiement typePaiement);

    TypePaiement majTypePaiement(TypePaiement typePaiement);

    List<TypePaiement> trouverTypePaiements();
    
    Banque creerBanque(Banque banque);

    Banque trouverBanque(Long banque);

    void supprimerBanque(Banque banque);

    Banque majBanque(Banque banque);

    List<Banque> trouverBanques();
}