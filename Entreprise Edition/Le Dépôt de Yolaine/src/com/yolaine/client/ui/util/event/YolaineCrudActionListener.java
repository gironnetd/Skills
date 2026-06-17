package com.yolaine.client.ui.util.event;


import java.util.EventListener;
import java.util.EventObject;


public interface YolaineCrudActionListener extends EventListener {
    
	public void listActionPerformed(EventObject evt);
    
	public void venteActionPerformed(EventObject evt);
	
	public void remboursementActionPerformed(EventObject evt);
    
	public void listDepotActionPerformed(EventObject evt);
    
    public void listArticleDepotActionPerformed(EventObject evt);  
    
    public void listArticleActionPerformed(EventObject evt); 
    
    public void listCategorieMarqueActionPerformed(EventObject evt); 
    
    public void listMarqueCategorieActionPerformed(EventObject evt); 
    
    public void listArticlesCategorieMarqueActionPerformed(EventObject evt); 
    
    public void listArticlesMarqueCategorieActionPerformed(EventObject evt); 
    
    public void listMarqueActionPerformed(EventObject evt); 
    
    public void listCategoryActionPerformed(EventObject evt); 
    
    public void listTypeidentiteActionPerformed(EventObject evt); 
	
    public void listCouleurActionPerformed(EventObject evt); 
    
    public void createActionPerformed(EventObject evt);
    
    public void createDepotActionPerformed(EventObject evt);
    
    public void createFicheDepotActionPerformed(EventObject evt);
    
    public void createArticleActionPerformed(EventObject evt);  
    
    public void createArticleDepotActionPerformed(EventObject evt);
    
    public void readActionPerformed(EventObject evt);
    
    public void readDepotActionPerformed(EventObject evt); 
    
    public void readArticleActionPerformed(EventObject evt);
    
    public void updateActionPerformed(EventObject evt);
    
    public void updateDepotActionPerformed(EventObject evt);
    
    public void deleteActionPerformed(EventObject evt);
    
}