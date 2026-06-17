package com.yolaine.client.ui.transaction.paiement;

import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import javax.ejb.EJBException;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.transaction.banque.event.BanqueAdapter;
import com.yolaine.client.ui.transaction.paiement.event.PaiementAdapter;
import com.yolaine.client.ui.transaction.paiement.event.PaiementEventPropertyName;
import com.yolaine.client.ui.transaction.paiement.model.PaiementModel;
import com.yolaine.client.ui.transaction.banque.model.BanqueModel;
import com.yolaine.client.ui.transaction.banque.model.BanqueTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.entity.transaction.Banque;
import com.yolaine.entity.catalogue.Article;

public class PaiementCrudFrame extends YolaineCrudFrame<PaiementPane> {

	private static final long serialVersionUID = 7769342743144999626L;
	private int defaultWidth = 640;
	private int defaultHeight = 480;

	public PaiementCrudFrame(final PaiementPane mainPane) {
		super(mainPane);

		mainPane.getModel().addXSListener(new PaiementAdapter() {

			@Override
			public void dateventeChanged(
					XSEvent<PaiementEventPropertyName, String> evt) {
				initTitle(mainPane.getViewType());
			} 

		});
	}


	public void findActionPerformed(EventObject evt) {
		final String actionName = "find";

		PaiementModel model = mainPane.getModel();


		if (model.getBanquePaiement().getBanque() == null || model.getBanquePaiement().getBanque().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Le champ \'banque\' est vide.", "Attention",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			Banque banque = CatalogDelegate.trouverBanque(model.getBanquePaiement().getId());

			if (banque == null) {
				JOptionPane.showMessageDialog(this,
						"Cette banque n'a pas été trouvée.", "Attention",
						JOptionPane.WARNING_MESSAGE);
			} else {
				model.setBanquePaiement(banque);

				mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
			}
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}


	public void createActionPerformed(EventObject evt) {
		final String actionName = "create";

		PaiementModel model = mainPane.getModel();
		Banque banque = model.getBanquePaiement();

		try {
			banque = CatalogDelegate.creerBanque(banque);

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
			// e.getCausedByException());jusque là
			displayException(className, actionName, exc);
		}
	}

	public void readActionPerformed(EventObject evt) {
	}

	public void updateActionPerformed(EventObject evt) {
		final String actionName = "update";

		PaiementModel model = mainPane.getModel();
		Banque banque = model.getBanquePaiement();

		try {
			banque = CatalogDelegate.majBanque(banque);

			dispose();
			BanqueTableModel depotable = new BanqueTableModel();    		
			YolaineListFrame frame = new YolaineListFrame(depotable);
			frame.setSize(defaultWidth, defaultHeight + 150);
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void deleteActionPerformed(EventObject evt) {
		final String actionName = "delete";

		PaiementModel model = mainPane.getModel();
		Banque banque = model.getBanquePaiement();
		List<Banque> listeBanques = new ArrayList<Banque>();
		List<Article> listearticles = CatalogDelegate.findArticles();
		for(Article article: listearticles){
		
		}
		 if (listeBanques.size() == 1){
			
			JOptionPane.showMessageDialog(this,
					"Ce type de manche ne peut pas être supprimé car \n il précise le type de manches d' un article.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listeBanques.size() > 1){
			
			JOptionPane.showMessageDialog(this,
					"Ce type de manche ne peut pas être supprimé car \n il précise le type de manches de plusieurs articles.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			System.out.println(listeBanques.size());
			try {
				
				CatalogDelegate.supprimerBanque(banque);            
				dispose();
				BanqueTableModel depotable = new BanqueTableModel();    		
				YolaineListFrame frame = new YolaineListFrame(depotable);
				frame.setSize(defaultWidth, defaultHeight + 150);
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			} catch (Exception exc) {
				displayException(className, actionName, exc);
			}
		}

	}

	public void resetActionPerformed(EventObject evt) {
		PaiementModel model = mainPane.getModel();		
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