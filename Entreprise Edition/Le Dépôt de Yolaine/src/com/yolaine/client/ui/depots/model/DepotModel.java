package com.yolaine.client.ui.depots.model;


import java.util.Set;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.commun.adresse.model.AdresseModel;
import com.yolaine.client.ui.depots.event.DepotEventPropertyName;
import com.yolaine.client.ui.depots.event.DepotListener;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;

public interface DepotModel extends
        XSModel<DepotListener, DepotEventPropertyName> {

	public Long getId();
    
    public Long getIdentifierToFind();
    
    public void setIdentifierToFind(Long identifier);
    
    public Long getIdentifier();
   
	public Depot getDepot();

	public void setDepot(Depot depot);

	public String getDatedepot();

	public void setDatedepot(String datedepot);

	//public Set<Article> getArticles();

	//public void setArticles(Set<Article> articles);

	public boolean isCloturedepot();

	public void setCloturedepot(boolean cloturedepot);	
    
	public ArticleModel getArticleModel();

	public void setArticleModel(ArticleModel articlemodel);
}