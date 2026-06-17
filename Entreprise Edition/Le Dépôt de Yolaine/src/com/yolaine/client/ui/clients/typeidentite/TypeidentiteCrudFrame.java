package com.yolaine.client.ui.clients.typeidentite;

import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.clients.deposant.model.DeposantTableModel;
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteAdapter;
import com.yolaine.client.ui.clients.typeidentite.event.TypeidentiteEventPropertyName;
import com.yolaine.client.ui.clients.typeidentite.model.TypeidentiteModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;

public class TypeidentiteCrudFrame extends YolaineCrudFrame<TypeidentitePane> {
    
    private static final long serialVersionUID = -1175876732476090807L;
    private int defaultWidth = 640;
	private int defaultHeight = 480;
    
    public TypeidentiteCrudFrame(final TypeidentitePane mainPane) {
        super(mainPane);
        
        mainPane.getModel().addXSListener(new TypeidentiteAdapter() {
            
        	 public void typeidentiteChanged(
                     XSEvent<TypeidentiteEventPropertyName, TypeIdentite> evt) {
                 initTitle(mainPane.getViewType());
             }
                       
        }); 
    }
    
    
    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";
        
        TypeidentiteModel model = mainPane.getModel();             
       
        try {
           TypeIdentite typeidentite = ClientDelegate.trouverTypeIdentite(model.getTypeidentite().getId());
            
            if (typeidentite == null) {
                JOptionPane.showMessageDialog(this,
                        "Ce type d'identité n'a pas été trouvé.", "Attention",
                        JOptionPane.WARNING_MESSAGE);
            } else {            	
                model.getTypeidentite().setTypeIdentite(typeidentite.getTypeIdentite());
                
                mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    
    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";
        
        TypeidentiteModel model = mainPane.getModel();
        TypeIdentite typeidentite = model.getTypeidentite();        
       
        try {        	
            typeidentite = ClientDelegate.creerTypeIdentite(typeidentite);
            
            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void readActionPerformed(EventObject evt) {
    }
    
    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";
        
        TypeidentiteModel model = mainPane.getModel();
        TypeIdentite typeidentite = model.getTypeidentite();
        
        try {
            typeidentite = ClientDelegate.majTypeIdentite(typeidentite);            
            dispose();
            DeposantTableModel depotable = new DeposantTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth, defaultHeight + 150);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";
        
        TypeidentiteModel model = mainPane.getModel();
        TypeIdentite typeidentite = model.getTypeidentite();
        
        List<TypeIdentite> listetypeidentites = new ArrayList<TypeIdentite>();
        List<Client> listeclients = ClientDelegate.trouverTousclients();
        for(Client client: listeclients){
			if ( client.getTypeidentite().getTypeIdentite().equals(typeidentite.getTypeIdentite())){
				listetypeidentites.add(typeidentite);        		
			}else{}
		}
		 if (listetypeidentites.size() == 1){
			 System.out.println(listetypeidentites.size());
			JOptionPane.showMessageDialog(this,
					"Cet type d'identité ne peut pas être supprimé car \n elle précise celle d' un client ou déposant.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listetypeidentites.size() > 1){
			System.out.println(listetypeidentites.size());
			JOptionPane.showMessageDialog(this,
					"Cet type identité ne peut pas être supprimé car \n elle précise celle de plusieurs clients ou déposants.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			try {
	            ClientDelegate.supprimerTypeIdentite(typeidentite);            
	            dispose();
	            DeposantTableModel depotable = new DeposantTableModel();    		
	    		YolaineListFrame frame = new YolaineListFrame(depotable);
	    		frame.setSize(defaultWidth, defaultHeight + 150);
	    		PrincipalFrame.getInstance().addAndShowFrame(frame);
	        } catch (Exception exc) {
	            displayException(className, actionName, exc);
	        }
		}               
    }    
    
    public void resetActionPerformed(EventObject evt) {
        TypeidentiteModel model = mainPane.getModel();       
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