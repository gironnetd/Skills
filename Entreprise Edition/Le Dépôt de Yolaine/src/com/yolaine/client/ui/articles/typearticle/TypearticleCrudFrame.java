package com.yolaine.client.ui.articles.typearticle;

import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import javax.ejb.EJBException;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.typearticle.event.*;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;

public class TypearticleCrudFrame extends YolaineCrudFrame<TypearticlePane> {
    
    private static final long serialVersionUID = 7769342743144999626L;
    private int defaultWidth = 640;
	private int defaultHeight = 480;
    
    public TypearticleCrudFrame(final TypearticlePane mainPane) {
        super(mainPane);
        
        mainPane.getModel().addXSListener(new TypearticleAdapter() {
            
            @Override
            public void identifierChanged(
                    XSEvent<TypearticleEventPropertyName, Long> evt) {
                initTitle(mainPane.getViewType());
            }
            
        });
    }
    
    
    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";
        
        TypearticleModel model = mainPane.getModel();
       
        
        if (model.getCategory().getName() == null || model.getCategory().getName().equals("")) {
        	  JOptionPane.showMessageDialog(this,
                      "Le champ \'type article\' est vide.", "Attention",
                      JOptionPane.WARNING_MESSAGE);
            
            return;
        }
        
        try {
            Categorie category = CatalogDelegate.findCategory(model.getId());
            
            if (category == null) {
                JOptionPane.showMessageDialog(this,
                        "Ce type d'article n'a pas été trouvé.", "Attention",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setCategory(category);
                
                mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    
    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";
        
        TypearticleModel model = mainPane.getModel();
        Categorie category = model.getCategory();
        
        try {
            category = CatalogDelegate.createCategory(category);
            
            dispose();
        } catch (Exception exc) {
            System.out.println("===============================");
                                                                    // virer les
                                                                    // traces
            exc.printStackTrace();
            System.out.println("===============================");
            EJBException e = (EJBException) exc;
            e.getCausedByException().printStackTrace();
            // displayException(className, actionName,
            // e.getCausedByException()); jusque là
            displayException(className, actionName, exc);
        }
    }
    
    public void readActionPerformed(EventObject evt) {
    }
    
    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";
        
        TypearticleModel model = mainPane.getModel();
        Categorie category = model.getCategory();
        
        try {
            category = CatalogDelegate.updateCategory(category);            
            dispose();
            TypearticleTableModel depotable = new TypearticleTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth, defaultHeight + 150);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";
        
        TypearticleModel model = mainPane.getModel();
        Categorie categorie = model.getCategory();
        
        List<Categorie> listecategories = new ArrayList<Categorie>();
		List<Article> listearticles = CatalogDelegate.findArticles();
		for(Article article: listearticles){
			if ( article.getCategorie().getName().equals(categorie.getName())){
				listecategories.add(categorie);        		
			}else{}
		}
		if (listecategories.size() == 1){

			JOptionPane.showMessageDialog(this,
					"Cette catégorie d'articles ne peut pas être supprimée car \n elle précisen celle d' un article.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listecategories.size() > 1){

			JOptionPane.showMessageDialog(this,
					"Cette catégorie d'articles ne peut pas être supprimée car \n elle précise celle de plusieurs articles.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			try {
	            CatalogDelegate.deleteCategory(categorie);            
	            dispose();
	            TypearticleTableModel depotable = new TypearticleTableModel();    		
	    		YolaineListFrame frame = new YolaineListFrame(depotable);
	    		frame.setSize(defaultWidth + 500, defaultHeight + 150);
	    		PrincipalFrame.getInstance().addAndShowFrame(frame);
	        } catch (Exception exc) {
	            displayException(className, actionName, exc);
	        }
		}       
        
    }
    
    
    public void resetActionPerformed(EventObject evt) {
        TypearticleModel model = mainPane.getModel();
        YolaineViewType viewType = mainPane.getViewType();
        
        
        model.reset();
    }
	
	public void createDepotActionPerformed(EventObject evt) {	
	}
	
	public void createArticleActionPerformed(EventObject evt) {	
	}
	
	public void readDepotActionPerformed(EventObject evt) {	
	}
	
	public void readArticleActionPerformed(EventObject evt) {	
	}
	
	public void listActionPerformed(EventObject evt) {	
	}
	
	public void listDepotActionPerformed(EventObject evt) {	
	}
	
	public void listArticleActionPerformed(EventObject evt) {	
	}
	
	public void listArticleDepotActionPerformed(EventObject evt) {	
	}
	
	public void listMarqueActionPerformed(EventObject evt) {	
	}
	
	public void listCategoryActionPerformed(EventObject evt) {	
	}
	
	public void listTypeidentiteActionPerformed(EventObject evt) {	
	}
	
	public void listCouleurActionPerformed(EventObject evt) {	
	}
	
	public void updateDepotActionPerformed(EventObject evt) {	
	}
	
	public void createArticleDepotActionPerformed(EventObject evt) {	
	}
	
	public void createFicheDepotActionPerformed(EventObject evt) {	
	}
	
	public void resetArticleActionPerformed(EventObject evt) {	
	}
	
	public void venteActionPerformed(EventObject evt) {	
	}
	
	public void remboursementActionPerformed(EventObject evt) {	
	}


	@Override
	public void listCategorieMarqueActionPerformed(EventObject evt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void listMarqueCategorieActionPerformed(EventObject evt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void listArticlesCategorieMarqueActionPerformed(EventObject evt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void listArticlesMarqueCategorieActionPerformed(EventObject evt) {
		// TODO Auto-generated method stub
		
	}
    
}