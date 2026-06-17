package com.yolaine.client.ui.articles.marque;


import static com.yolaine.client.ui.util.YolaineViewType.*;


import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.marque.event.*;
import com.yolaine.client.ui.articles.marque.model.MarqueModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Marque;


public class MarqueCrudFrame extends YolaineCrudFrame<MarquePane> {

	private static final long serialVersionUID = -4459715124049741934L;
	private int defaultWidth = 640;
	private int defaultHeight = 480;
	Marque marque = new Marque();

	public MarqueCrudFrame(final MarquePane mainPane) {
		super(mainPane);

		mainPane.getModel().addXSListener(new MarqueAdapter() {

			@Override
			public void identifierChanged(
					XSEvent<MarqueEventPropertyName, Long> evt) {
				initTitle(mainPane.getViewType());
			}

		});
	}


	public void findActionPerformed(EventObject evt) {
		final String actionName = "find";

		MarqueModel model = mainPane.getModel();
		

		System.out.println(model.getIdentifierToFind());
		System.out.println(model.getName());
		if (model.getName() == null || model.getName().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Le champ \'marque\' est vide.", "Attention",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		Marque marque = CatalogDelegate.trouverMarque(model.getId());
		
		
		try {
			Marque product = CatalogDelegate.trouverMarque(model.getId());

			if (product == null) {
				JOptionPane.showMessageDialog(this,
						"Cette marque n'a pas été trouvée.", "Attention",
						JOptionPane.WARNING_MESSAGE);
			} else {
				 model.setName(marque.getName());
				 model.setDescription(marque.getDescription());

				mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
			}
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}


	public void createActionPerformed(EventObject evt) {
		final String actionName = "create";

		MarqueModel model = mainPane.getModel();
		Marque marque = model.getMarque();

		try {
			
			marque = CatalogDelegate.creerMarque(marque);

			dispose();
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void readActionPerformed(EventObject evt) {
	}

	public void updateActionPerformed(EventObject evt) {
		final String actionName = "update";

		MarqueModel model = mainPane.getModel();
		Marque product = model.getMarque();

		try {
			
			product = CatalogDelegate.majMarque(product);

			dispose();
			MarqueTableModel depotable = new MarqueTableModel();    		
			YolaineListFrame frame = new YolaineListFrame(depotable);
			frame.setSize(defaultWidth, defaultHeight + 150);
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void deleteActionPerformed(EventObject evt) {
		final String actionName = "delete";

		MarqueModel model = mainPane.getModel();
		Marque marque = model.getMarque();
		List<Marque> listemarques = new ArrayList<Marque>();
		List<Article> listearticles = CatalogDelegate.findArticles();
		for(Article article: listearticles){
			if ( article.getMarque().getName().equals(marque.getName())){        		
				listemarques.add(marque);        		
			}else{}
		}
		if (listemarques.size() == 1){

			JOptionPane.showMessageDialog(this,
					"Cette marque ne peut pas être supprimée car \n elle précise celle d' un article.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listemarques.size() > 1){

			JOptionPane.showMessageDialog(this,
					"Cette marque ne peut pas être supprimée car \n elle précise celle de plusieurs articles.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			try {
				CatalogDelegate.supprimerMarque(marque);            
				dispose();
				MarqueTableModel depotable = new MarqueTableModel();    		
				YolaineListFrame frame = new YolaineListFrame(depotable);
				frame.setSize(defaultWidth, defaultHeight + 150);
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			} catch (Exception exc) {
				displayException(className, actionName, exc);
			}
		}       
	}


	public void resetActionPerformed(EventObject evt) {
		MarqueModel model = mainPane.getModel();
		YolaineViewType viewType = mainPane.getViewType();

		if (viewType != CHERCHER || viewType != CREER
				|| viewType != CHERCHER_OU_CREER) {
			model.setIdentifierToFind(null);
		}

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


	
	public void listCategorieMarqueActionPerformed(EventObject evt) {
		marque = mainPane.getModel().getMarque();		
		TypearticleTableModel articlemodel = new TypearticleTableModel(marque);
		YolaineListFrame frame = new YolaineListFrame(articlemodel);
		frame.setSize(defaultWidth + 250, defaultHeight);
		PrincipalFrame.getInstance().addAndShowFrame(frame);	
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