package com.yolaine.client.ui.depots;


import static com.yolaine.client.ui.util.YolaineViewType.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.depots.event.DepotAdapter;
import com.yolaine.client.ui.depots.event.DepotEventPropertyName;
import com.yolaine.client.ui.depots.model.DepotModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.catalogue.Situation;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;

public class DepotCrudFrame extends YolaineCrudFrame<DepotPane> {

	private static final long serialVersionUID = -1175876732476090807L;
	private int defaultWidth = 640;
	private int defaultHeight = 480;

	Depot depot = mainPane.getModel().getDepot();	
	
	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}


	public DepotCrudFrame(final DepotPane mainPane) {
		super(mainPane);

		mainPane.getModel().addXSListener(new DepotAdapter() {

			public void identifiantChanged(
					XSEvent<DepotEventPropertyName,Integer> evt) {
				initTitle(mainPane.getViewType());
			}

		}); 
	}


	public void findActionPerformed(EventObject evt) {
		final String actionName = "find";

		DepotModel model = mainPane.getModel();        

		try {
			Depot depot = ClientDelegate.trouverDepot(model.getIdentifier());

			if (depot == null) {
				JOptionPane.showMessageDialog(this,
						"Ce dépôt n'a pas été trouvé", "Attention",
						JOptionPane.WARNING_MESSAGE);
			} else {            	
				model.setDepot(depot);

				mainPane.setViewType(MISE_A_JOUR_OU_SUPPRIMER);
			}
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}


	public void createActionPerformed(EventObject evt) {
		final String actionName = "create";

		DepotModel model = mainPane.getModel();
		Depot depot = model.getDepot();        
		System.out.println(depot.getClient().getNom());
		try {        	
			depot = ClientDelegate.creerDepot(depot);            
			dispose();
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void readActionPerformed(EventObject evt) {
		DepotModel model = mainPane.getModel();
		Depot depot = model.getDepot();		
		PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new ArticleTableModel(depot)));
	}

	public void updateActionPerformed(EventObject evt) {
		final String actionName = "update";
		System.out.println("update");
		DepotModel model = mainPane.getModel();
		Depot depot = model.getDepot();

		try {
			depot = ClientDelegate.majDepot(depot);

			dispose();
			DepotTableModel depotable = new DepotTableModel(depot.getClient());
			YolaineListFrame frame = new YolaineListFrame(depotable);
			frame.setSize(defaultWidth + 250, defaultHeight);
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public void deleteActionPerformed(EventObject evt) {
		final String actionName = "delete";		
//		try {
//			DepotModel model = mainPane.getModel();
//			Depot depot = model.getDepot();
//			List<Article> listearticles = CatalogDelegate.findArticles(depot.getId());
//
//			if(listearticles.size() == 0){
//				System.out.println("depotId : " + depot.getId());
//				ClientDelegate.supprimerDepot(depot.getId());
//				dispose();
//				DepotTableModel depotable = new DepotTableModel(depot.getClient());
//				YolaineListFrame frame = new YolaineListFrame(depotable);
//				frame.setSize(defaultWidth + 250, defaultHeight);
//				PrincipalFrame.getInstance().addAndShowFrame(frame);
//			}else{
//				int i = JOptionPane.showOptionDialog(this,
//						"Ce dépôt contient des articles,\n Désirez-vous quand-même le supprimer ?",
//						"Attention",
//						JOptionPane.YES_NO_OPTION,
//						JOptionPane.WARNING_MESSAGE,
//						null, null, null);
//				if (i == JOptionPane.YES_OPTION){
//					for(Article article : listearticles){
//						System.out.println(article.getId());
//						CatalogDelegate.deleteArticle(article.getId());
//					}
//					ClientDelegate.supprimerDepot(depot.getId());
//					dispose();
//					DepotTableModel depotable = new DepotTableModel(depot.getClient());
//					YolaineListFrame frame = new YolaineListFrame(depotable);
//					frame.setSize(defaultWidth + 250, defaultHeight);
//					PrincipalFrame.getInstance().addAndShowFrame(frame);
//				}else {
//
//				}

//			}
//
//		} catch (Exception exc) {
//			displayException(className, actionName, exc);
//		}
	}    

	public void resetActionPerformed(EventObject evt) {
		DepotModel model = mainPane.getModel();	
		model.reset();
	}

	public void createDepotActionPerformed(EventObject evt) {
		final String actionName = "create";

		DepotModel model = mainPane.getModel();
		Depot depot = model.getDepot();	        

		try {        	
			depot = ClientDelegate.creerDepot(depot);

			dispose();
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}		
	}

	public void createArticleActionPerformed(EventObject evt) {		
		final String actionName = "update";
		System.out.println("créer  article");
		ArticleModel model = mainPane.getModel().getArticleModel();
		
		Article article = model.getArticle();
		try { 
			article.getPaiement().setLibellepaiement("paiement temporaire " + depot.getId() + "-" + article.getId());
			article.getRemboursement().setLibelleRemboursement("remboursement temporaire"  + depot.getId() + "-" + article.getId());
			if(model.getCouleur2().getCouleur() == null){
				article.setCouleur2(new Couleur(""));
			}else{
				article.setCouleur2(model.getCouleur2());
			}	
				article.setDateDepot(model.getDateDepot());
			if(model.getManche().getManche() == null){
				article.setManche(new Manche(""));
			}else{
				article.setManche(model.getManche());
			}

			if(model.getMarque().getName() == null){
				article.setMarque(new Marque(""));
			}else{
				article.setMarque(model.getMarque());
			}				
				
			article.setDepot(mainPane.getModel().getDepot());			
			try{
				Float f;
				if(model.getMontantDepot() == null || model.getMontantDepot().equals("")){
					
				}else{
					 f = Float.valueOf(model.getMontantDepot());
				}
				}catch(NumberFormatException e){

					JOptionPane.showMessageDialog(this,
							"Le montant de l'article au dépôt est invalide.", "Attention",
							JOptionPane.WARNING_MESSAGE);				
			}
				
			if (model.getTaille() == null){
				article.setTaille("");
			}else{
				article.setTaille(model.getTaille());
			}			
				
			article.setSituation(CatalogDelegate.trouverSituations().get(0));
			System.out.println(model.isSolde());
			
			if(article.isSolde() == true ){
				if(article.getPourcentage() == null || article.getPourcentage().equals("")){
					JOptionPane.showMessageDialog(this,
							"Vous devez précisé le pourcentage.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}else{
					List<Article> listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());			
					if(listearticles.size() == 0){
						//article.setId(mainPane.getModel().getIdentifier() + "-1");
						Paiement p = new Paiement();
						Remboursement r = new Remboursement();
						System.out.println("avant createArticle()");
						article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
						System.out.println("après createArticle()");
						model.reset();				
						//model.setId(2);
						model.getArticle().setMontantDepot("");				
					}else{
//						String idTmp = listearticles.get(0).getId();
//						String []st = idTmp.split("-");
						//String s = st[2];
						int i = 0 ;
						for (Article articleTmp : listearticles){
//							String []st1 = articleTmp.getId().split("-");
//							String s1 = st1[2];
//							if (s1.length() > s.length()){
//								s = s1;
//								i = Integer.parseInt(s1);
//							}else if (s1.length() == s.length()){
//								i = Integer.parseInt(s);
//								int i1 = Integer.parseInt(s1);
//
//								if (i1 > i){
//									i = i1;
//								}else{
//
//								}
//							}else{
//
//							}
						}
						
						i++;
						// s = String.valueOf(i);
//						article.setId(mainPane.getModel().getIdentifier() + "-" + s);
						article.setMontantDepot(model.getMontantDepot());									 
						
						article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
						
						listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());
						
						
						model.reset();					
						i++;
						String s2 = String.valueOf(i);
						//model.setId(s2);
						model.getArticle().setMontantDepot("");				
					}
				}
			}else{
				List<Article> listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());			
				if(listearticles.size() == 0){
//					article.setId(mainPane.getModel().getIdentifier() + "-1");
					Paiement p = new Paiement();
					Remboursement r = new Remboursement();
					System.out.println("avant createArticle()");
					article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
					System.out.println("après createArticle()");
					model.reset();				
					//model.setId(2);
					model.getArticle().setMontantDepot("");				
				}else{
//					String idTmp = listearticles.get(0).getId();
//					String []st = idTmp.split("-");
					//String s = st[2];
					int i = 0 ;
					for (Article articleTmp : listearticles){
//						String []st1 = articleTmp.getId().split("-");
//						String s1 = st1[2];
//						if (s1.length() > s.length()){
//							s = s1;
//							i = Integer.parseInt(s1);
//						}else if (s1.length() == s.length()){
//							i = Integer.parseInt(s);
//							int i1 = Integer.parseInt(s1);
//
//							if (i1 > i){
//								i = i1;
//							}else{
//
//							}
//						}else{
//
//						}
					}
					
					i++;
					// s = String.valueOf(i);
//					article.setId(mainPane.getModel().getIdentifier() + "-" + s);
					article.setMontantDepot(model.getMontantDepot());									 
					
					article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
					
					listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());
					
					
					model.reset();					
					i++;
					String s2 = String.valueOf(i);
					//model.setId(s2);
					model.getArticle().setMontantDepot("");				
				}
			}
			System.out.println(mainPane.getModel().getIdentifier());

		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}	
	}

	public void readDepotActionPerformed(EventObject evt) {		
		DepotModel model = mainPane.getModel();
		Depot depot = model.getDepot();		       
		PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(new ArticleTableModel(depot)));
	}

	public void readArticleActionPerformed(EventObject evt) {
	}

	public void listActionPerformed(EventObject evt) {	
	}

	public void listDepotActionPerformed(EventObject evt) {	
	}

	public void listArticleActionPerformed(EventObject evt) {		
		this.depot = mainPane.getModel().getDepot();
		ArticleTableModel articlemodel = new ArticleTableModel(depot);
		YolaineListFrame frame = new YolaineListFrame(articlemodel);
		frame.setSize(defaultWidth + 250, defaultHeight);
		PrincipalFrame.getInstance().addAndShowFrame(frame);
	}

	public void listArticleDepotActionPerformed(EventObject evt) {

		depot = mainPane.getModel().getDepot();		
		ArticleTableModel articlemodel = new ArticleTableModel(depot);
		YolaineListFrame frame = new YolaineListFrame(articlemodel);
		frame.setSize(defaultWidth + 250, defaultHeight);
		PrincipalFrame.getInstance().addAndShowFrame(frame);	
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
		final String actionName = "update";
		System.out.println("update depot");

		try { 
			ArticleModel model = mainPane.getModel().getArticleModel();
			Article article = model.getArticle();
			Categorie c = model.getCategorie();
			article.setClientArticle(model.getClientArticle());
		
			article.setCouleur1(model.getCouleur1());
			if(model.getCouleur2().getCouleur() == null){
				article.setCouleur2(new Couleur(""));
			}else{
				article.setCouleur2(model.getCouleur2());
			}	
			article.setDateDepot(model.getDateDepot());
			if(model.getManche().getManche() == null){
				article.setManche(new Manche(""));
			}else{
				article.setManche(model.getManche());
			}

			if(model.getMarque().getName() == null){
				article.setMarque(new Marque(""));
			}else{
				article.setMarque(model.getMarque());
			}							
			article.setCategorie(model.getCategorie());
			article.setDepot(mainPane.getModel().getDepot());			
			try{
				Float f;
				if(model.getMontantDepot() == null || model.getMontantDepot().equals("")){
					
				}else{
					 f = Float.valueOf(model.getMontantDepot());
				}
				}catch(NumberFormatException e){

					JOptionPane.showMessageDialog(this,
							"Le montant de l'article au dépôt est invalide.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				
			}
			article.setMontantDepot(model.getMontantDepot());
			article.setPourcentage(model.getPourcentage());
			article.setPrixVente(model.getPrixVente());
			article.setSituation(model.getSituation());
			article.setSolde(model.isSolde());
			//article.setNumeroBanque("");
			//article.setNumeroChequePaiement("");
			//article.setNumeroChequeRemboursement("");
			if (model.getTaille() == null){
				article.setTaille("");
			}else{
				article.setTaille(model.getTaille());
			}			
			article.setTexte(model.getTexte());
			article.setCategorie(model.getCategorie());
			article.setVersion(model.getVersion());
			article.setSituation(new Situation("déposé"));			
			System.out.println(model.isSolde());
			article.setSolde(model.isSolde());	
			//article.setDatevente("");
			//article.setBanquePaiement(new Banque(""));
			//article.setTypepaiement(new TypePaiement(""));
			//article.setDateremboursement("");
			//article.setBanqueRemboursement(new Banque(""));
			//article.setTyperemboursement(new TypePaiement(""));
			Categorie c1 = new Categorie();
			c1.getMarques().add(article.getMarque()); 
			c1.setDescription("");
			c1.setName(article.getCategorie().getName());
			article.getCategorie().getMarques().add(article.getMarque());

			System.out.println(article.getMarque().getName());
			System.out.println(article.getMarque().getDescription());
			c1 = CatalogDelegate.createCategory(c1);
			if(article.isSolde() == true ){
				if(article.getPourcentage() == null || article.getPourcentage().equals("")){
					JOptionPane.showMessageDialog(this,
							"Vous devez précisé le pourcentage.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}else{
					List<Article> listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());			
					if(listearticles.size() == 0){
//						article.setId(mainPane.getModel().getIdentifier() + "-1");

						article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				

						model.reset();				
						//model.setId(2);
						model.getArticle().setMontantDepot("");				
					}else{				
//						String [] ts = listearticles.get(listearticles.size() - 1).getId().split("-");
//						int i = Integer.parseInt(ts[2]);
//						i++;
						//String s = String.valueOf(i);
//						article.setId(mainPane.getModel().getIdentifier() + "-" + s);
						// c = CatalogDelegate.createCategory(article.getTypearticle());
						article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
						listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());
						model.reset();
//						ts = listearticles.get(listearticles.size() - 1).getId().split("-");
//						i = Integer.parseInt(ts[2]);
						//i++;
						//s = String.valueOf(i);
						//model.setId(s);
						model.getArticle().setMontantDepot("");				
					}				
				}
			}else{
				List<Article> listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());			
				if(listearticles.size() == 0){
//					article.setId(mainPane.getModel().getIdentifier() + "-1");
					article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
					model.reset();
					
					//model.setId(2);
					model.getArticle().setMontantDepot("");				
				}else{				
//					String [] ts = listearticles.get(listearticles.size() - 1).getId().split("-");
//					int i = Integer.parseInt(ts[2]);
//					i++;
					//String s = String.valueOf(i);
//					article.setId(mainPane.getModel().getIdentifier() + "-" + s);
					article.setMontantDepot(model.getMontantDepot());									 
					article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
					listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());
					model.reset();
//					ts = listearticles.get(listearticles.size() - 1).getId().split("-");
//					i = Integer.parseInt(ts[2]);
					//i++;
					//s = String.valueOf(i);
					//model.setId(s);
					model.getArticle().setMontantDepot("");				
				}
			}
			System.out.println(mainPane.getModel().getIdentifier());

		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}	
	}

	public void createArticleDepotActionPerformed(EventObject evt) {		
		final String actionName = "update";
		System.out.println("créer  article");
		ArticleModel model = mainPane.getModel().getArticleModel();
		
		Article article = model.getArticle();
		try { 				
			if(model.getCouleur2().getCouleur() == null){
				article.setCouleur2(new Couleur(""));
			}else{
				article.setCouleur2(model.getCouleur2());
			}	
				article.setDateDepot(model.getDateDepot());
			if(model.getManche().getManche() == null){
				article.setManche(new Manche(""));
			}else{
				article.setManche(model.getManche());
			}

			if(model.getMarque().getName() == null){
				article.setMarque(new Marque(""));
			}else{
				article.setMarque(model.getMarque());
			}				
				
			article.setDepot(mainPane.getModel().getDepot());			
			try{
				Float f;
				if(model.getMontantDepot() == null || model.getMontantDepot().equals("")){
					
				}else{
					 f = Float.valueOf(model.getMontantDepot());
				}
				}catch(NumberFormatException e){

					JOptionPane.showMessageDialog(this,
							"Le montant de l'article au dépôt est invalide.", "Attention",
							JOptionPane.WARNING_MESSAGE);				
			}
				
			if (model.getTaille() == null){
				article.setTaille("");
			}else{
				article.setTaille(model.getTaille());
			}			
				
			article.setSituation(new Situation("déposé"));			
			System.out.println(model.isSolde());
			
			if(article.isSolde() == true ){
				if(article.getPourcentage() == null || article.getPourcentage().equals("")){
					JOptionPane.showMessageDialog(this,
							"Vous devez précisé le pourcentage.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}else{
					List<Article> listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());			
					if(listearticles.size() == 0){
//						article.setId(mainPane.getModel().getIdentifier() + "-1");
						Paiement p = new Paiement();
						Remboursement r = new Remboursement();
						System.out.println("avant createArticle()");
						article = CatalogDelegate.createArticle(article, article.getMarque(),p, r);				
						System.out.println("après createArticle()");
						model.reset();				
						//model.setId(2);
						model.getArticle().setMontantDepot("");				
					}else{
//						String idTmp = listearticles.get(0).getId();
//						String []st = idTmp.split("-");
						//String s = st[2];
						int i = 0 ;
						for (Article articleTmp : listearticles){
//							String []st1 = articleTmp.getId().split("-");
//							String s1 = st1[2];
//							if (s1.length() > s.length()){
//								s = s1;
//								i = Integer.parseInt(s1);
//							}else if (s1.length() == s.length()){
//								i = Integer.parseInt(s);
//								int i1 = Integer.parseInt(s1);
//
//								if (i1 > i){
//									i = i1;
//								}else{
//
//								}
//							}else{
//
//							}
						}
						
						i++;
//						 s = String.valueOf(i);
//						article.setId(mainPane.getModel().getIdentifier() + "-" + s);
						article.setMontantDepot(model.getMontantDepot());									 
						
						article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
						
						listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());
						
						
						model.reset();					
						i++;
						String s2 = String.valueOf(i);
						//model.setId(s2);
						model.getArticle().setMontantDepot("");				
					}
				}
			}else{
				List<Article> listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());			
				if(listearticles.size() == 0){
//					article.setId(mainPane.getModel().getIdentifier() + "-1");
					Paiement p = new Paiement();
					Remboursement r = new Remboursement();
					System.out.println("avant createArticle()");
					article = CatalogDelegate.createArticle(article, article.getMarque(),p, r);				
					System.out.println("après createArticle()");
					model.reset();				
					//model.setId(2);
					model.getArticle().setMontantDepot("");				
				}else{
//					String idTmp = listearticles.get(0).getId();
//					String []st = idTmp.split("-");
					//String s = st[2];
					int i = 0 ;
					for (Article articleTmp : listearticles){
//						String []st1 = articleTmp.getId().split("-");
//						String s1 = st1[2];
//						if (s1.length() > s.length()){
//							s = s1;
//							i = Integer.parseInt(s1);
//						}else if (s1.length() == s.length()){
//							i = Integer.parseInt(s);
//							int i1 = Integer.parseInt(s1);
//
//							if (i1 > i){
//								i = i1;
//							}else{
//
//							}
//						}else{
//
//						}
					}
					
					i++;
//					 s = String.valueOf(i);
//					article.setId(mainPane.getModel().getIdentifier() + "-" + s);
					article.setMontantDepot(model.getMontantDepot());									 
					
					article = CatalogDelegate.createArticle(article, article.getMarque(),article.getPaiement(), article.getRemboursement());				
					
					listearticles = CatalogDelegate.findArticles(mainPane.getModel().getIdentifier());
					
					
					model.reset();					
					i++;
					String s2 = String.valueOf(i);
					//model.setId(s2);
					model.getArticle().setMontantDepot("");				
				}
			}
			System.out.println(mainPane.getModel().getIdentifier());

		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}	
	}

	public void createFicheDepotActionPerformed(EventObject evt) {
		DepotModel depotmodel = mainPane.getModel();
		Depot depot = depotmodel.getDepot();
		Document document = new Document();

		List<Article> listearticles = new ArrayList<Article>();
		String nomfiche = "depot" + mainPane.getModel().getIdentifier()+".pdf";	
		try {
			/* Associate the document with a PDF writer and an output stream */
			PdfWriter.getInstance(document, new FileOutputStream(nomfiche));

			/* Open the document (ready to add items) */
			document.open();
			Paragraph para = new Paragraph("Dépôt n° " + depot.getId() + "\n\n");
			para.setAlignment(para.ALIGN_CENTER);
			document.add(para);	
			Paragraph para1 = new Paragraph();
			para1.setAlignment(para1.ALIGN_RIGHT);
			para1.add(depot.getDateDepot()+ "\n");
			document.add(para1);
			Paragraph para2 = new Paragraph( depot.getClient().getCivilite().getCivilite() + " "
					+ depot.getClient().getPrenom()+ " "
					+ depot.getClient().getNom());
			para2.setAlignment(para2.ALIGN_LEFT);
			document.add(para2);

			Paragraph para3 = new Paragraph();
			para3.add( depot.getClient().getAdresse().getAdresse1()+ "\n"
					+ depot.getClient().getAdresse().getCodePostal()+ " "
					+ depot.getClient().getAdresse().getVille() + "\n\n\n");
			document.add(para3);

//			listearticles = CatalogDelegate.findArticles(depot.getId());
			PdfPTable   table = new PdfPTable(8);	 
			table.setWidthPercentage(100);	        
			table.setHorizontalAlignment(WIDTH);
			Font    headingStyle = new Font();
			headingStyle.setSize(8);		

			table.addCell(new Phrase("N°", headingStyle));
			table.addCell(new Phrase("Soldé", headingStyle));
			table.addCell(new Phrase("Type article", headingStyle));
			table.addCell(new Phrase("1ère couleur", headingStyle));
			table.addCell(new Phrase("2ème couleur", headingStyle));
			table.addCell(new Phrase("Marque", headingStyle));
			table.addCell(new Phrase("Taille", headingStyle));
			table.addCell(new Phrase("Montant de dépôt", headingStyle));
			for(Article article : listearticles){		    	
				String s ;
//				String [] st = article.getId().split("-");
				if (article.isSolde()){
					s = "oui";
				}else{
					s = "non";
				}     
//				table.addCell(new Phrase(st[2],headingStyle));
				table.addCell(new Phrase(s,headingStyle));
				table.addCell(new Phrase(article.getCategorie().getName(),headingStyle));
				table.addCell(new Phrase(article.getCouleur1().getCouleur(),headingStyle));
				table.addCell(new Phrase(article.getCouleur2().getCouleur(),headingStyle));
				table.addCell(new Phrase(article.getMarque().getName(),headingStyle));
				table.addCell(new Phrase(article.getTaille(),headingStyle));
				table.addCell(new Phrase(String.valueOf(article.getMontantDepot()),headingStyle));	               
			}
			document.add(table);		    
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			document.close();
		}
	}

	public void resetArticleActionPerformed(EventObject evt) {
		DepotModel depotmodel = mainPane.getModel();	
		depotmodel.getArticleModel().reset();
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
