package com.yolaine.client.ui.articles.couleur;


import static com.yolaine.client.ui.util.YolaineViewType.*;


import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ejb.EJBException;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.couleur.event.CouleurAdapter;
import com.yolaine.client.ui.articles.couleur.event.CouleurEventPropertyName;
import com.yolaine.client.ui.articles.couleur.model.CouleurModel;
import com.yolaine.client.ui.articles.couleur.model.CouleurTableModel;
import com.yolaine.client.ui.articles.manche.model.MancheTableModel;
import com.yolaine.client.ui.articles.typearticle.event.*;
import com.yolaine.client.ui.articles.couleur.model.CouleurModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;


public class CouleurCrudFrame extends YolaineCrudFrame<CouleurPane> {
    
    private static final long serialVersionUID = 7769342743144999626L;
    private int defaultWidth = 640;
	private int defaultHeight = 480;
    
    public CouleurCrudFrame(final CouleurPane mainPane) {
        super(mainPane);
        
        mainPane.getModel().addXSListener(new CouleurAdapter() {
            
            @Override
            public void couleurChanged(
                    XSEvent<CouleurEventPropertyName, String> evt) {
                initTitle(mainPane.getViewType());
            } 
               
        });
    }
    
    
    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";
        
        CouleurModel model = mainPane.getModel();
       
        
        if (model.getCouleur().getCouleur() == null || model.getCouleur().getCouleur().equals("")) {
        	 JOptionPane.showMessageDialog(this,
                     "Le champ \'couleur\' est vide.", "Attention",
                     JOptionPane.WARNING_MESSAGE);
            
            return;
        }
        
        try {
            Couleur couleur = CatalogDelegate.trouverCouleur(model.getCouleur().getId());
            
            if (couleur == null) {
                JOptionPane.showMessageDialog(this,
                        "Cette couleur n'a pas été trouvée.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setCouleur(couleur);
                
                mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    
    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";
        
        CouleurModel model = mainPane.getModel();
        Couleur couleur = model.getCouleur();
        
        try {
            couleur = CatalogDelegate.creerCouleur(couleur);
            
            dispose();
        } catch (Exception exc) {
            System.out.println("===============================");// TODO
                                                                    // virer les
                                                                    // traces
            exc.printStackTrace();
            System.out.println("===============================");
            EJBException e = (EJBException) exc;
            e.getCausedByException().printStackTrace();
            // displayException(className, actionName,
            // e.getCausedByException());//TODO jusque là
            displayException(className, actionName, exc);
        }
    }
    
    public void readActionPerformed(EventObject evt) {
    }
    
    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";
        
        CouleurModel model = mainPane.getModel();
        Couleur couleur = model.getCouleur();
        
        try {
            couleur = CatalogDelegate.majCouleur(couleur);
            
            dispose();
            CouleurTableModel depotable = new CouleurTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth, defaultHeight + 150);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";
        
        CouleurModel model = mainPane.getModel();
        Couleur couleur = model.getCouleur();
        List<Couleur> listecouleurs = new ArrayList<Couleur>();
        List<Article> listearticles = CatalogDelegate.findArticles();
        for(Article article: listearticles){
			if ( article.getCouleur1().getCouleur().equals(couleur.getCouleur())){        		
				listecouleurs.add(couleur);        		
			}else if ( article.getCouleur2().getCouleur().equals(couleur.getCouleur())){
				listecouleurs.add(couleur);   
			}else{}
		}
		 if (listecouleurs.size() == 1){
			 System.out.println(listecouleurs.size());
			JOptionPane.showMessageDialog(this,
					"Cette couleur ne peut pas être supprimée car \n elle précise celle d' un article.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listecouleurs.size() > 1){
			System.out.println(listecouleurs.size());
			JOptionPane.showMessageDialog(this,
					"Cette couleur ne peut pas être supprimée car \n elle précise celle de plusieurs articles.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			try {
	            CatalogDelegate.supprimerCouleur(couleur);	            
	            dispose();
	            CouleurTableModel depotable = new CouleurTableModel();    		
	    		YolaineListFrame frame = new YolaineListFrame(depotable);
	    		frame.setSize(defaultWidth, defaultHeight + 150);
	    		PrincipalFrame.getInstance().addAndShowFrame(frame);
	        } catch (Exception exc) {
	            displayException(className, actionName, exc);
	        }
		}        
    }
    
    
    public void resetActionPerformed(EventObject evt) {
        CouleurModel model = mainPane.getModel();
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