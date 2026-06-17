package com.yolaine.client.ui.articles.article;


import static com.yolaine.client.ui.util.YolaineViewType.*;
import java.util.EventObject;
import javax.swing.JOptionPane;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.event.*;
import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.transaction.paiement.model.DefaultPaiementModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.TypeIdentite;
import com.yolaine.exception.ValidationException;


public class ArticleCrudFrame extends YolaineCrudFrame<ArticlePane> {

	private static final long serialVersionUID = -5830165357963216102L;
	private int defaultWidth = 640;
	private int defaultHeight = 480;

	public ArticleCrudFrame(final ArticlePane mainPane) {
		super(mainPane);

		mainPane.getModel().addXSListener(new ArticleAdapter() {

			@Override
			public void identifiantChanged(
					XSEvent<ArticleEventPropertyName,String> evt) {
				initTitle(mainPane.getViewType());
			}

		});
	}


	public void findActionPerformed(EventObject evt) {
		final String actionName = "find";

		ArticleModel model = mainPane.getModel();
		Long identifier = model.getId();

		if (String.valueOf(identifier) == null) {
			displayWarning("Identifier must be non null");

			return;
		}

		try {
			Article article = CatalogDelegate.findArticle(identifier);

			if (article == null) {
				JOptionPane.showMessageDialog(this,
						"This item has not been found", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				model.setArticle(article);

				mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
			}
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}


	public void createActionPerformed(EventObject evt) {
		final String actionName = "create";

		ArticleModel model = mainPane.getModel();
		Article article = model.getArticle();
		article.setClientArticle(model.getClientArticle());
		article.setCouleur1(model.getCouleur1());
		article.setCouleur2(model.getCouleur2());
		article.setDateDepot(model.getDateDepot());
		article.setId(model.getId());
		Manche manche = new Manche();
		manche.setManche("");
		article.setManche(manche);
		Marque marque = new Marque();            
		article.setMarque(marque);
		article.setMontantDepot(model.getMontantDepot());
		article.setPourcentage(model.getPourcentage());
		article.setPrixVente(model.getPrixVente());
		article.setSituation(model.getSituation());
		article.setSolde(model.isSolde());
		article.setTaille(String.valueOf(model.getTaille()));
		article.setTexte(model.getTexte());
		article.setCategorie(model.getCategorie());
		article.setVersion(model.getVersion());
		article.setSolde(model.isSolde());
		try {
			Marque marquetmp = CatalogDelegate.trouverMarque(model.getMarque().getId());
			article = CatalogDelegate.createArticle(article,article.getMarque(),article.getPaiement(), article.getRemboursement());

			dispose();
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void readActionPerformed(EventObject evt) {
	}

	public void updateActionPerformed(EventObject evt) {
		final String actionName = "update";

		ArticleModel model = mainPane.getModel();
		Article article = model.getArticle();
//		article.setId(article.getDepot().getId() + "-" + article.getId());
		try {
			Marque marque = CatalogDelegate.trouverMarque(model.getMarque().getId());
			article = CatalogDelegate.updateArticle(article,article.getMarque(),article.getPaiement(),article.getRemboursement());

			dispose();
			ArticleTableModel depotable = new ArticleTableModel(article.getDepot());    		
			YolaineListFrame frame = new YolaineListFrame(depotable);
			frame.setSize(defaultWidth + 250, defaultHeight);
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void deleteActionPerformed(EventObject evt) {
		final String actionName = "delete";       
		ArticleModel model = mainPane.getModel();
		Article article = model.getArticle();
		try {			
			//article.setClientarticle(model.getClientarticle());
			//article.setCouleur1(model.getCouleur1());
			//article.setCouleur2(model.getCouleur2());
			//article.setDatedepot(model.getDatedepot());
			//article.setId(model.getId());           
			//article.setManche(model.getManche());            
			//article.setMarque(model.getMarque());
			//article.setMontantdepot(model.getMontantdepot());
			//article.setPourcentage(model.getPourcentage());
			//article.setPrixvente(model.getPrixvente());
			//article.setSituation(model.getSituation());
			//article.setSolde(model.isSolde());
			//article.setTaille(String.valueOf(model.getTaille()));
			//article.setTexte(model.getTexte());
			//article.setTypearticle(model.getTypearticle());
			//article.setVersion(model.getVersion());
			//article.setSolde(model.isSolde());   
			//article.setPaiement(model.getPaiement());
			//article.setRemboursement(model.getRemboursement());
//			CatalogDelegate.deleteArticle(article.getDepot().getId() + "-" + article.getId());

			dispose();
			ArticleTableModel depotable = new ArticleTableModel(article.getDepot());    		
			YolaineListFrame frame = new YolaineListFrame(depotable);
			frame.setSize(defaultWidth + 250, defaultHeight);
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}


	public void resetActionPerformed(EventObject evt) {
		ArticleModel model = mainPane.getModel();
		YolaineViewType viewType = mainPane.getViewType();       
		model.reset();
	}


	public void createDepotActionPerformed(EventObject evt) {


	}

	public void createArticleActionPerformed(EventObject evt) {
		final String actionName = "create";

		ArticleModel model = mainPane.getModel();
		Article article = model.getArticle();
		article.setClientArticle(model.getClientArticle());
		article.setCouleur1(model.getCouleur1());
		article.setCouleur2(model.getCouleur2());
		article.setDateDepot(model.getDateDepot());
		article.setId(model.getId());
		Manche manche = new Manche();
		manche.setManche(model.getManche().getManche());
		article.setManche(manche);	            
		article.setMarque(model.getMarque());
		article.setMontantDepot(model.getMontantDepot());
		article.setPourcentage(model.getPourcentage());
		article.setPrixVente(model.getPrixVente());
		article.setSituation(model.getSituation());
		article.setSolde(model.isSolde());
		article.setTaille(String.valueOf(model.getTaille()));
		article.setTexte(model.getTexte());
		article.setCategorie(model.getCategorie());
		article.setVersion(model.getVersion());
		article.setSolde(model.isSolde());
		try {
			Marque marquetmp = CatalogDelegate.trouverMarque(model.getMarque().getId());
			article = CatalogDelegate.createArticle(article,article.getMarque(),article.getPaiement(), article.getRemboursement());

			dispose();
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}		
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
		ArticleModel model = mainPane.getModel();
		
		Article article = model.getArticle();
			
//		article.setId(model.getDepot().getId() + "-" + model.getId());
		
		if ( !article.getSituation().getSituation().equals("déposé")){
			JOptionPane.showMessageDialog(this,
					"L' opération est impossible car l'article est déjé vendu.", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		article.getPaiement().setLibellepaiement(article.getId() + "-" + article.getPaiement().getTypepaiement().getTypePaiement());
		article.setSituation(new Situation("vendu"));
		
		if(article.getPaiement().getTypepaiement().getTypePaiement() == null
				|| article.getPaiement().getTypepaiement().getTypePaiement().equals("")){
			JOptionPane.showMessageDialog(this,
					"Le type de paiement n'est pas précisé.", "Attention",
					JOptionPane.WARNING_MESSAGE);

		}else if (article.getPaiement().getTypepaiement().getTypePaiement().equals("espéce")){
			
				article.getPaiement().setBanquePaiement(null);
				article = CatalogDelegate.venteArticle(article, article.getPaiement());
				
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("déposé");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			
		}else if (!article.getPaiement().getTypepaiement().getTypePaiement().equals("chéque")){
			
			if (article.getPaiement().getTypepaiement().getTypePaiement() == null || article.getPaiement().getTypepaiement().getTypePaiement().equals("")){
					JOptionPane.showMessageDialog(this,
							"La banque de paiement n'est pas précisée.", "Attention",
							JOptionPane.WARNING_MESSAGE); 							
			}else{
				article = CatalogDelegate.venteArticle(article,article.getPaiement());
				
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("déposé");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			}
			}else{
				article = CatalogDelegate.venteArticle(article,article.getPaiement());
				
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("déposé");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			}
		}	
			
	
	public void remboursementActionPerformed(EventObject evt) {
		ArticleModel model = mainPane.getModel();
		Article article = model.getArticle();
		//article.setId(model.getDepot().getId() + "-" + model.getId());
		article.setSituation(new Situation("remboursé"));
		article.setRemboursement(model.getRemboursementModel().getRemboursement());
		article.getRemboursement().setLibelleRemboursement(article.getId() + "-" + article.getRemboursement().getTyperemboursement().getTypePaiement());
		if ( article.getSituation().getSituation().equals("déposé")){
			JOptionPane.showMessageDialog(this,
					"L' opération est impossible car l'article n'est pas vendu.", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if(article.getRemboursement().getTyperemboursement().getTypePaiement() == null
				|| article.getRemboursement().getTyperemboursement().getTypePaiement().equals("")){
			JOptionPane.showMessageDialog(this,
					"Le type de remboursement n'est pas précisé.", "Attention",
					JOptionPane.WARNING_MESSAGE);

		}else if (article.getRemboursement().getTyperemboursement().getTypePaiement().equals("espéce")){
			
				article.getRemboursement().setBanqueRemboursement(null);
				article = CatalogDelegate.rembourserArticle(article, article.getRemboursement());
				
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("vendu");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			
		}else if (article.getPaiement().getTypepaiement().getTypePaiement().equals("chéque")){
			
			
				article = CatalogDelegate.rembourserArticle(article, article.getRemboursement());
				
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("vendu");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			
			}else{
				article = CatalogDelegate.rembourserArticle(article,article.getRemboursement());
				
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("déposé");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			}
		
		
		if(article.getRemboursement().getBanqueRemboursement().getBanque() == null){
			article.getRemboursement().setBanqueRemboursement(new Banque(""));
		}else{
			article.getRemboursement().setBanqueRemboursement(model.getRemboursementModel().getBanqueRemboursement());
		}	

		if(article.getRemboursement().getTyperemboursement().getTypePaiement() == null || article.getRemboursement().getTyperemboursement().getTypePaiement().equals("")){
			JOptionPane.showMessageDialog(this,
					"Le type de remboursement n'est pas précisé.", "Attention",
					JOptionPane.WARNING_MESSAGE);

		}else if (article.getRemboursement().getTyperemboursement().getTypePaiement().equals("espéce")){
			if (article.getRemboursement().getBanqueRemboursement().getBanque() == null || article.getRemboursement().getBanqueRemboursement().getBanque().equals("")){
				
				article = CatalogDelegate.venteArticle(article,article.getPaiement());
				dispose();
				//ArticleTableModel depotable = new ArticleTableModel("vendu");    		
				//YolaineListFrame frame = new YolaineListFrame(depotable);
				//frame.setSize(defaultWidth + 250, defaultHeight);
				//PrincipalFrame.getInstance().addAndShowFrame(frame);
			} else if( article.getRemboursement().getBanqueRemboursement().getBanque() != null && !article.getRemboursement().getBanqueRemboursement().getBanque().equals("")){
				JOptionPane.showMessageDialog(this,
						"Le type de remboursement \'espéce\' ne doét pas posséder de banque de paiement.", "Attention",
						JOptionPane.WARNING_MESSAGE);	
			}else{
	
			}
		}else{
			article = CatalogDelegate.venteArticle(article,article.getPaiement());
			dispose();
			//ArticleTableModel depotable = new ArticleTableModel("vendu");    		
			//YolaineListFrame frame = new YolaineListFrame(depotable);
			//frame.setSize(defaultWidth + 250, defaultHeight);
			//PrincipalFrame.getInstance().addAndShowFrame(frame);
		}
		
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