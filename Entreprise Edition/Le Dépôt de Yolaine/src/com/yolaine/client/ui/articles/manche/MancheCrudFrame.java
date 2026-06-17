package com.yolaine.client.ui.articles.manche;

import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import javax.ejb.EJBException;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.manche.event.MancheEventPropertyName;
import com.yolaine.client.ui.articles.manche.event.MancheAdapter;
import com.yolaine.client.ui.articles.manche.model.MancheModel;
import com.yolaine.client.ui.articles.manche.model.MancheTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Manche;

public class MancheCrudFrame extends YolaineCrudFrame<ManchePane> {

	private static final long serialVersionUID = 7769342743144999626L;
	private int defaultWidth = 640;
	private int defaultHeight = 480;

	public MancheCrudFrame(final ManchePane mainPane) {
		super(mainPane);

		mainPane.getModel().addXSListener(new MancheAdapter() {

			@Override
			public void mancheChanged(
					XSEvent<MancheEventPropertyName, String> evt) {
				initTitle(mainPane.getViewType());
			} 

		});
	}


	public void findActionPerformed(EventObject evt) {
		final String actionName = "find";

		MancheModel model = mainPane.getModel();


		if (model.getManche().getManche() == null || model.getManche().getManche().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Le champ \'manche\' est vide.", "Attention",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			Manche manche = CatalogDelegate.trouverManche(model.getManche().getId());

			if (manche == null) {
				JOptionPane.showMessageDialog(this,
						"Ce type de manches n'a pas été trouvé.", "Attention",
						JOptionPane.WARNING_MESSAGE);
			} else {
				model.setManche(manche);

				mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
			}
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}


	public void createActionPerformed(EventObject evt) {
		final String actionName = "create";

		MancheModel model = mainPane.getModel();
		Manche manche = model.getManche();

		try {
			manche = CatalogDelegate.creerManche(manche);

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

		MancheModel model = mainPane.getModel();
		Manche manche = model.getManche();

		try {
			manche = CatalogDelegate.majManche(manche);

			dispose();
			MancheTableModel depotable = new MancheTableModel();    		
			YolaineListFrame frame = new YolaineListFrame(depotable);
			frame.setSize(defaultWidth, defaultHeight + 150);
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void deleteActionPerformed(EventObject evt) {
		final String actionName = "delete";

		MancheModel model = mainPane.getModel();
		Manche manche = model.getManche();
		List<Manche> listemanches = new ArrayList<Manche>();
		List<Article> listearticles = CatalogDelegate.findArticles();
		for(Article article: listearticles){
			if ( article.getManche().getManche().equals(manche.getManche())){        		
				listemanches.add(article.getManche());        		
			}else{

			}

		}
		 if (listemanches.size() == 1){
			 System.out.println(listemanches.size());
			JOptionPane.showMessageDialog(this,
					"Ce type de manche ne peut pas être supprimé car \n il précise le type de manches d' un article.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else  if (listemanches.size() > 1){
			System.out.println(listemanches.size());
			JOptionPane.showMessageDialog(this,
					"Ce type de manche ne peut pas être supprimé car \n il précise le type de manches de plusieurs articles.", "Impossible",
					JOptionPane.WARNING_MESSAGE);
		}else{
			System.out.println(listemanches.size());
			try {
				System.out.println(manche);
				CatalogDelegate.supprimerManche(manche);            
				dispose();
				MancheTableModel depotable = new MancheTableModel();    		
				YolaineListFrame frame = new YolaineListFrame(depotable);
				frame.setSize(defaultWidth, defaultHeight + 150);
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			} catch (Exception exc) {
				displayException(className, actionName, exc);
			}
		}

	}


	public void resetActionPerformed(EventObject evt) {
		MancheModel model = mainPane.getModel();		

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