package com.yolaine.client.ui.depots.model;


import static com.yolaine.client.ui.clients.deposant.event.DeposantEventPropertyName.PRENOM_CHANGED;
import static com.yolaine.client.ui.depots.event.DepotEventPropertyName.*;

import java.util.Date;
import java.util.Set;

//import com.yaps.petstore.client.ui.common.address.model.DefaultAddressModel;
//import com.yaps.petstore.client.ui.common.address.model.AddressModel;
import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.articles.article.model.DefaultArticleModel;
import com.yolaine.client.ui.commun.adresse.model.AdresseModel;
import com.yolaine.client.ui.commun.adresse.model.DefaultAdresseModel;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;

public class DefaultDepotModel extends AbstractDepotModel {
    
    private static final long serialVersionUID = 6117458605996580214L;    

    private Depot depot;
    private Long identifierToFind;
    private ArticleModel articlemodel;
    
    public DefaultDepotModel() {        
    	Depot depot = new Depot();
    	setDepot(depot);
    }
    
    public DefaultDepotModel(Depot depot) {
        setDepot(depot);
    }
        
    public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		 if (depot == null) {
	         throw new IllegalArgumentException("Le dépôt ne doît pas être nul");
	     }
		 
		 Depot oldValue = this.depot;
		 Depot newValue = depot;
		 
		 this.depot = depot;
		 
		 if (oldValue != null){
			 fireXSChanged(this, IDENTIFIANT_CHANGED, oldValue.getId(), newValue
                     .getId());
			 fireXSChanged(this, DATEDEPOT_CHANGED, oldValue.getDateDepot(),newValue
					 .getDateDepot());
			 fireXSChanged(this, CLOTUREDEPOT_CHANGED, oldValue.isClotureDepot(),newValue
					 .isClotureDepot());
			 
			 if (articlemodel == null) {
		            articlemodel = new DefaultArticleModel();
		        } else {
		        	//Long i = Integer.parseLong(articlemodel.getId());
		            //articlemodel.setArticle(depot.getArticles().get(Long.parseInt(articlemodel.getId())));
		        }
		 }
	}

	public Long getId() { return depot.getId(); }

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
        return depot.getId();
    }    
   
	public String getDatedepot(){
		return depot.getDateDepot();
	}

	public void setDatedepot(String datedepot){
		Object oldValue = depot.getDateDepot();
        Object newValue = datedepot;
        
        depot.setDateDepot(datedepot);
        
        fireXSChanged(this, DATEDEPOT_CHANGED, oldValue, newValue);
	}
	
	public boolean isCloturedepot(){
		return depot.isClotureDepot();
	}

	public void setCloturedepot(boolean cloturedepot){
		Object oldValue = depot.isClotureDepot();
        Object newValue = cloturedepot;
        
        depot.setClotureDepot(cloturedepot);
        
        fireXSChanged(this, DATEDEPOT_CHANGED, oldValue, newValue);
	}    
    
    public void reset() { 	
       setDatedepot(null);
       setCloturedepot(false);
       articlemodel.reset();
       
    }
	
	public ArticleModel getArticleModel() {		
		return articlemodel;
	}
	
	public void setArticleModel(ArticleModel articlemodel) {
		this.articlemodel = articlemodel;		
	}
	
	
	
}