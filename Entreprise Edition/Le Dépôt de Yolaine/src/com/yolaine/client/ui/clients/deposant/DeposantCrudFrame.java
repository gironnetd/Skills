package com.yolaine.client.ui.clients.deposant;

import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.EventObject;
import java.util.List;

import javax.ejb.EJBException;
import javax.persistence.EntityExistsException;
import javax.swing.JOptionPane;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.clients.deposant.model.DeposantModel;
import com.yolaine.client.ui.clients.deposant.model.DeposantTableModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;


public class DeposantCrudFrame extends YolaineCrudFrame<DeposantPane> {
    
    private static final long serialVersionUID = -1175876732476090807L;
    private int defaultWidth = 640;
	private int defaultHeight = 480;
    
    public DeposantCrudFrame(final DeposantPane mainPane) {
    	super(mainPane);       
    }    
     
    public void findActionPerformed(EventObject evt) {      
        
        DeposantModel model = mainPane.getModel();
        String nom = model.getNom();
        String prenom = model.getPrenom();       
        
        if (nom == null ) {
            displayWarning("Le nom ne doît pas être nulle");            
            return;
        }
        if(prenom == null){
        	prenom = "";
        }
        
        if(model.getCivilite().getCivilite()== null || model.getCivilite().getCivilite().equals("")){
        	Civilite civilite = new Civilite();
        	civilite.setCivilite("");
        	model.setCivilite(civilite);
        }
        if(model.getTypeidentite().getTypeIdentite()== null || model.getTypeidentite().getTypeIdentite().equals("")){
        	TypeIdentite typeidentite = new TypeIdentite();
        	typeidentite.setTypeIdentite("");
        	model.setTypeidentite(typeidentite);
        }
        try {
            Client client = ClientDelegate.trouverClient(nom,prenom);
            
            if (client == null) {
                JOptionPane.showMessageDialog(this,
                        "Ce client n'a pas été trouvé;", "Attention",
                        JOptionPane.WARNING_MESSAGE);
            } else {            	
                model.setClient(client);
                
                mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
            }
        } catch (EJBException exc) {
        	 JOptionPane.showMessageDialog(this,
                     "Ce client n'a pas été trouvé;", "Attention",
                     JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";
        
        DeposantModel model = mainPane.getModel();
        Client client = model.getClient();        
       // List<Client> listeclients = ClientDelegate.trouverClients(true);
       // int i = listeclients.get(listeclients.size() - 1).getAdresse().getId();
       // i++;
       // client.getAdresse().setId(i);
        try {
        	if(client.getCivilite().getCivilite()== null || client.getCivilite().getCivilite().equals("")){
        		
        		client.setCivilite(new Civilite(""));        		
        	}
        	
        	if(client.getTypeidentite().getTypeIdentite()== null|| client.getTypeidentite().getTypeIdentite().equals("")){
        		
        		client.setTypeidentite(new TypeIdentite(""));
        	}        	
        	if (client.getPrenom() == null){
        		client.setPrenom("");
        	}
        	String nomtmp =client.getNom().toUpperCase();
        	client.setNom(nomtmp);
            client = ClientDelegate.createClient(client,client.getCivilite(), client
                    .getAdresse(), client.getTypeidentite());
            
            dispose();
        } catch (EntityExistsException exc) {
            displayException(className, actionName, exc);
            client = ClientDelegate.createClient(client,client.getCivilite(), client
                    .getAdresse(), client.getTypeidentite());
        }
    }
    
    public void readActionPerformed(EventObject evt) {
    	DeposantModel model = mainPane.getModel();
        Client client = model.getClient();
        //System.out.println(client.getNom());
        //System.out.println(client.getId());        
        PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new DepotTableModel(client)));          
    }
    
    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";
        
        DeposantModel model = mainPane.getModel();
        Client client = model.getClient();
        System.out.println(client.getNom());
        System.out.println(client.getId());
        //PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new DepotTableModel(client)));
        try {
            client = ClientDelegate.majClient(client,client.getCivilite(), client
                    .getAdresse(), client.getTypeidentite());            
            dispose();
            DeposantTableModel depotable = new DeposantTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth + 500, defaultHeight);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";
        
        DeposantModel model = mainPane.getModel();
        Client client = model.getClient();
        
        try {
            ClientDelegate.supprimerClient(client);            
            dispose();
            DeposantTableModel depotable = new DeposantTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth + 500, defaultHeight);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    
    public void resetActionPerformed(EventObject evt) {
    	DeposantModel model = mainPane.getModel();
       
        model.setDeposante(false);
        model.setCivilite(new Civilite(""));
        model.setNom("");       
        model.setNomCombo(new Client());
        model.setPrenomCombo(new Client());
        model.setPrenom("");       
        model.setTelephonefixe("");
        model.setTelephoneportable("");
        model.setEmail("");
        model.setTypeidentite(new TypeIdentite(""));
        model.setNumeroidentite("");        
        model.setDatenaissance("");        
        model.setCommentaire("");
        model.setChampnumerique1("");
        model.setChampnumerique2("");
        model.setMontantdepose("");
        model.setMontantdu(""); 
        model.setLogin("");
        model.setPassword("");
        model.reset();
    }
    
	public void readAction1Performed(EventObject evt) {
		DeposantModel model = mainPane.getModel();
        Client client = model.getClient();              
        PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new ArticleTableModel(client)));          
		}
	
	public void readAction2Performed(EventObject evt) {
		DeposantModel model = mainPane.getModel();
        Client client = model.getClient();
             
        PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new ArticleTableModel(client)));          
	}

	@Override
	public void createDepotActionPerformed(EventObject evt) {
		 final String actionName = "create";	        
	        DeposantModel model = mainPane.getModel();
	        Depot depot = new Depot();	      
	        Client client = model.getClient();
	        depot.setId(1L);
	        depot.setClient(client);
	        try {        	
	        	 depot = ClientDelegate.creerDepot(depot);
	            
	            dispose();
	        } catch (Exception exc) {
	            displayException(className, actionName, exc);
	        }			
	}
	
	public void createArticleActionPerformed(EventObject evt) {	
	}
	
	public void readDepotActionPerformed(EventObject evt) {				
	}
	
	public void readArticleActionPerformed(EventObject evt) {
		DeposantModel model = mainPane.getModel();
        Client client = model.getClient();              
        PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new ArticleTableModel(client)));  
	}
	
	public void listActionPerformed(EventObject evt) {	
	}
	
	public void listDepotActionPerformed(EventObject evt) {		
	}
	
	public void listArticleActionPerformed(EventObject evt) {		
	}
	
	public void closeActionPerformed(EventObject evt) {		
		this.dispose();
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