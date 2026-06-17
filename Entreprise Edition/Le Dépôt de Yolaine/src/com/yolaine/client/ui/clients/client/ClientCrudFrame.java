package com.yolaine.client.ui.clients.client;


import static com.yolaine.client.ui.util.YolaineViewType.*;

import java.util.EventObject;
import javax.swing.JOptionPane;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.clients.client.model.ClientModel;
import com.yolaine.client.ui.clients.client.model.ClientTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;

import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;

public class ClientCrudFrame extends YolaineCrudFrame<ClientPane> {
    
    private static final long serialVersionUID = -1175876732476090807L;
    private int defaultWidth = 640;
	private int defaultHeight = 480;
    
    public ClientCrudFrame(final ClientPane mainPane) {
        super(mainPane);        
    }
    
     
    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";
        
        ClientModel model = mainPane.getModel();
        String nom = model.getNom();
        String prenom = model.getPrenom();       
        
        if (nom == null ) {
            displayWarning("Le nom ne doît pas être nulle.");            
            return;
        }
        if(prenom == null){
        	prenom = "";
        }
        
        if(model.getCivilite().getCivilite()== null 
        		|| model.getCivilite().getCivilite().equals("")){
        	Civilite civilite = new Civilite();
        	civilite.setCivilite("");
        	model.setCivilite(civilite);
        }
        if(model.getTypeidentite().getTypeIdentite()== null
        		|| model.getTypeidentite().getTypeIdentite().equals("")){
        	TypeIdentite typeidentite = new TypeIdentite();
        	typeidentite.setTypeIdentite("");
        	model.setTypeidentite(typeidentite);
        }
        try {
            Client client = ClientDelegate.trouverClient(nom,prenom);
            
            if (client == null) {
                JOptionPane.showMessageDialog(this,
                        "Ce client n'a pas été trouvé.", "Attention",
                        JOptionPane.WARNING_MESSAGE);
            } else {            	
                model.setClient(client);
                
                mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    
    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";
        
        ClientModel model = mainPane.getModel();
        Client client = model.getClient();
        
       
        try {
        	if(client.getCivilite()== null || client.getCivilite().getCivilite().equals("")){
        		Civilite civilite = new Civilite();
        		civilite.setCivilite("");
        		client.setCivilite(civilite);        		
        	}
        	
        	if(client.getTypeidentite().getTypeIdentite()== null|| client.getTypeidentite().getTypeIdentite().equals("")){
        		TypeIdentite typeidentite = new TypeIdentite();
        		typeidentite.setTypeIdentite("");
        		client.setTypeidentite(typeidentite);
        	}
        	
        	if (client.getPrenom() == null){
        		client.setPrenom("");
        	}
            client = ClientDelegate.createClient(client,client.getCivilite(), client
                    .getAdresse(), client.getTypeidentite());
            
            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void readActionPerformed(EventObject evt) {
    }
    
    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";
        
        ClientModel model = mainPane.getModel();
        Client client = model.getClient();
        
        try {
            client = ClientDelegate.majClient(client,client.getCivilite(), client
                    .getAdresse(), client.getTypeidentite());            
            dispose();
            ClientTableModel depotable = new ClientTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth + 250, defaultHeight + 150);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";
        
        ClientModel model = mainPane.getModel();
        Client client = model.getClient();
        
        try {
            ClientDelegate.supprimerClient(client);            
            dispose();
            ClientTableModel depotable = new ClientTableModel();    		
    		YolaineListFrame frame = new YolaineListFrame(depotable);
    		frame.setSize(defaultWidth + 250, defaultHeight);
    		PrincipalFrame.getInstance().addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }    
    
    public void resetActionPerformed(EventObject evt) {
        ClientModel model = mainPane.getModel();       
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
	
	public void resetArticleActionPerformed(EventObject evt) {	}
	
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