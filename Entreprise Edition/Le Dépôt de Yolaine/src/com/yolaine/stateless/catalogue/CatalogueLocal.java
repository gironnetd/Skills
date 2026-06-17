package com.yolaine.stateless.catalogue;

import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Antonio Goncalves
 */
@Local
public interface CatalogueLocal {

    // ======================================
    // =          Methodes publiques        =
    // ======================================
    Categorie findCategory(Long categoryId) ;

    Couleur trouverCouleur(Long couleurId);
    
    Manche trouverManche(Long productId);
    
    Marque trouverMarque(Long productId) ;

    Article findArticle(Long itemId) ;

    List<Article> searchArticles(String keyword) ;
}