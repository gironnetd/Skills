package com.yolaine.client.delegate;

import com.yolaine.client.locator.ServiceLocator;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.*;
import com.yolaine.entity.client.*;
import com.yolaine.stateless.catalogue.CatalogueRemote;

import java.util.List;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CatalogBean class. Each method delegates the call to the
 * CatalogBean class
 */
public final class CatalogDelegate {

    // ======================================
    // =      Category Business methods     =
    // ======================================

    public static Categorie createCategory(Categorie category) {
        return getCatalogRemote().createCategory(category);
    }

    public static Categorie findCategory(Long categoryId) {
        return getCatalogRemote().findCategory(categoryId);
    }

    public static void deleteCategory(Categorie category) {
        getCatalogRemote().deleteCategory(category);
    }

    public static Categorie updateCategory(Categorie category) {
        return getCatalogRemote().updateCategory(category);
    }

    public static List<Categorie> findCategories() {
        return getCatalogRemote().findCategories();
    }

    // ======================================
    // =      Product Business methods     =
    // ======================================
   
    public static Marque creerMarque(Marque marque) {
        return getCatalogRemote().creerMarque(marque);
    }
    
    public static Marque trouverMarque(Long marqueId) {
        return getCatalogRemote().trouverMarque(marqueId);
    }

    public static void supprimerMarque(Marque product) {
        getCatalogRemote().supprimerMarque(product);
    }

    public static Marque majMarque(Marque product) {
        return getCatalogRemote().majMarque(product);
    }

    public static List<Marque> trouverMarques() {
        return getCatalogRemote().trouverMarques();
    }

    // ======================================
    // =        Item Business methods       =
    // ======================================

    public static Article createArticle(Article article, Marque marque,Paiement paiement, Remboursement remboursement) {
        return getCatalogRemote().createArticle(article, marque, paiement,remboursement);
    }

    public static Article findArticle(Long itemId) {
        return getCatalogRemote().findArticle(itemId);
    }

    public static void deleteArticle(Long articleId) {
        getCatalogRemote().deleteArticle(articleId);
    } 

    public static Article updateArticle(Article article, Marque marque,Paiement paiement, Remboursement remboursement) {
        return getCatalogRemote().updateArticle(article, marque,paiement,remboursement);
    }

    public static Article venteArticle(Article article,Paiement paiement) {
        return getCatalogRemote().venteArticle(article, paiement);
    }
    
    public static Article rembourserArticle(Article article, Remboursement remboursement) {
        return getCatalogRemote().rembourserArticle(article, remboursement);
    }
    
    public static List<Article> findArticles() {
        return getCatalogRemote().findArticles();
    }

    public static List<Article> findArticles(Long depotId) {
        return getCatalogRemote().findArticles(depotId);
    }
    
    public static List<Article> findArticlesbySituation(String situation) {
        return getCatalogRemote().findArticlesbySituation(situation);
    }
    
    public static List<Article> findArticlesbyMarque(Long marqueId) {
        return getCatalogRemote().findArticlesbyMarque(marqueId);
    }
    
    public static List<Article> findArticlesbyCategorie(Long categorieId) {
        return getCatalogRemote().findArticlesbyCategorie(categorieId);
    }
    
    public static List<Article> findArticlesbyMarqueAndCategorie(Long marqueId, Long categorieId) {
        return getCatalogRemote().findArticlesbyMarqueAndCategorie(marqueId, categorieId);
    }
    
    public static List<Article> findArticlesbyCategorieAndMarque(Long categorieId, Long marqueId) {
        return getCatalogRemote().findArticlesbyCategorieAndMarque(categorieId,marqueId);
    }
    
    public static List<Article> findClientArticles(Long clientId) {
        return getCatalogRemote().findClientArticles(clientId);
    }
    
    // ======================================
    // =  TypeIdentite   Business methods   =
    // ======================================

    public static Manche creerManche(Manche manche) {
        return getCatalogRemote().creerManche(manche);
    }

    public static Manche trouverManche(Long mancheId) {
        return getCatalogRemote().trouverManche(mancheId);
    }

    public static void supprimerManche(Manche manche) {
        getCatalogRemote().supprimerManche(manche);
    }

    public static Manche majManche(Manche manche) {
        return getCatalogRemote().majManche(manche);
    }

    public static List<Manche> trouverManches() {
        return getCatalogRemote().trouverManches();
    }

    // ======================================
    // =        Couleur   Business methods         =
    // ======================================

    public static Couleur creerCouleur(Couleur couleur) {
        return getCatalogRemote().creerCouleur(couleur);
    }

    public static Couleur trouverCouleur(Long couleurId) {
        return getCatalogRemote().trouverCouleur(couleurId);
    }

    public static void supprimerCouleur(Couleur couleur) {
        getCatalogRemote().supprimerCouleur(couleur);
    }

    public static Couleur majCouleur(Couleur couleur) {
        return getCatalogRemote().majCouleur(couleur);
    }

    public static List<Couleur> trouverCouleurs() {
        return getCatalogRemote().trouverCouleurs();
    }

    // ======================================
    // =        Situation   Business methods         =
    // ======================================
    
    public static List<Situation> trouverSituations() {
        return getCatalogRemote().trouverSituations();
    }
    
    // ======================================
    // =        TypePaiement   Business methods         =
    // ======================================
   
    public static TypePaiement creerTypePaiement(TypePaiement typePaiement) {
        return getCatalogRemote().creerTypePaiement(typePaiement);
    }

    public static TypePaiement trouverTypePaiement(Long typePaiement) {
        return getCatalogRemote().trouverTypePaiement(typePaiement);
    }

    public static void supprimerTypePaiement(TypePaiement typePaiement) {
        getCatalogRemote().supprimerTypePaiement(typePaiement);
    }

    public static TypePaiement majTypePaiement(TypePaiement typePaiement) {
        return getCatalogRemote().majTypePaiement(typePaiement);
    }
    
    public static List<TypePaiement> trouverTypePaiements() {
        return getCatalogRemote().trouverTypePaiements();
    }
    
 // ======================================
    // =       Banque   Business methods         =
    // ======================================
   
    public static Banque creerBanque(Banque banque) {
        return getCatalogRemote().creerBanque(banque);
    }

    public static Banque trouverBanque(Long banqueId) {
        return getCatalogRemote().trouverBanque(banqueId);
    }

    public static void supprimerBanque(Banque banque) {
        getCatalogRemote().supprimerBanque(banque);
    }

    public static Banque majBanque(Banque banque) {
        return getCatalogRemote().majBanque(banque);
    }
    
    public static List<Banque> trouverBanques() {
        return getCatalogRemote().trouverBanques();
    }    
    
    // ======================================
    // =            Private methods         =
    // ======================================
    private static CatalogueRemote getCatalogRemote() {
        CatalogueRemote catalogRemote;
        catalogRemote = (CatalogueRemote) ServiceLocator.getInstance().getRemoteInterface("ejb/stateless/Catalogue");
        return catalogRemote;
    }
}
