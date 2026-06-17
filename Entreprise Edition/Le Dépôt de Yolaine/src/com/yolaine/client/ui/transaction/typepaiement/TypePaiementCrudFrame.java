package com.yolaine.client.ui.transaction.typepaiement;


import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import javax.ejb.EJBException;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.couleur.model.CouleurTableModel;
import com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementAdapter;
import com.yolaine.client.ui.transaction.typepaiement.event.TypePaiementEventPropertyName;
import com.yolaine.client.ui.transaction.typepaiement.model.TypePaiementModel;
import com.yolaine.client.ui.transaction.typepaiement.model.TypePaiementTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.transaction.TypePaiement;
import com.yolaine.entity.catalogue.Article;

public class TypePaiementCrudFrame extends YolaineCrudFrame<TypePaiementPane> {
    
    private static final long serialVersionUID = 7769342743144999626L;
    private int defaultWidth = 640;
	private int defaultHeight = 480;
    
    public TypePaiementCrudFrame(final TypePaiementPane mainPane) {
        super(mainPane);
        
        mainPane.getModel().addXSListener(new TypePaiementAdapter() {
            
            @Override
            public void typePaiementChanged(
                    XSEvent<TypePaiementEventPropertyName, String> evt) {
                initTitle(mainPane.getViewType());
            } 
               
        });
    }
    
    
    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";
        
        TypePaiementModel model = mainPane.getModel();
       
        
        if (model.getTypePaiement().getTypePaiement() == null || model.getTypePaiement().getTypePaiement().equals("")) {
           
            JOptionPane.showMessageDialog(this,
                    "Le champ \'type paiement\' est vide.", "Attention",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
        	TypePaiement typePaiement = CatalogDelegate.trouverTypePaiement(model.getTypePaiement().getId());
            
            if (typePaiement == null) {
                JOptionPane.showMessageDialog(this,
                        "Ce type de paiement n'existe pas.", "Attention",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setTypePaiement(typePaiement);
                
                mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    
    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";
        
        TypePaiementModel model = mainPane.getModel();
        TypePaiement typePaiement = model.getTypePaiement();
        
        try {
        	typePaiement = CatalogDelegate.creerTypePaiement(typePaiement);
            
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
        
        TypePaiementModel model = mainPane.getModel();
        TypePaiement typePaiement = model.getTypePaiement();
        
        try {
        	typePaiement = CatalogDelegate.majTypePaiement(typePaiement);
            
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
        
        TypePaiementModel model = mainPane.getModel();
        TypePaiement typePaiement = model.getTypePaiement();
        List<TypePaiement> listetypePaiements = new ArrayList<TypePaiement>();
        List<Article> listearticles = CatalogDelegate.findArticles();
        for(Article article: listearticles){
		//	if ( article.getTypepaiement().getTypepaiement().equals(typePaiement.getTypepaiement())){        		
				listetypePaiements.add(typePaiement);       			
		//	}else{}
		}
		 if (listetypePaiements.size() == 1){
			 
			JOptionPane.showMessageDialog(this,
					"Cette couleur ne peut pas être supprimée car \n elle précise \n celle d' un article.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listetypePaiements.size() > 1){			
			JOptionPane.showMessageDialog(this,
					"Cette couleur ne peut pas être supprimée car \n elle précise \n celle de plusieurs articles.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			try {
	            CatalogDelegate.supprimerTypePaiement(typePaiement);	            
	            dispose();
	            TypePaiementTableModel typePaiementtable = new TypePaiementTableModel();    		
	    		YolaineListFrame frame = new YolaineListFrame(typePaiementtable);
	    		frame.setSize(defaultWidth, defaultHeight + 150);
	    		PrincipalFrame.getInstance().addAndShowFrame(frame);
	        } catch (Exception exc) {
	            displayException(className, actionName, exc);
	        }
		}        
    }
    
    
    public void resetActionPerformed(EventObject evt) {
    	TypePaiementModel model = mainPane.getModel();
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