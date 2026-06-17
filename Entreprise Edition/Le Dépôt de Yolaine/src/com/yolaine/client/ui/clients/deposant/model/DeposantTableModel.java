package com.yolaine.client.ui.clients.deposant.model;


import static com.yolaine.util.ExceptionUtils.getRootCause;
import static com.yolaine.util.ExceptionUtils.isApplicationException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;

import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.ArticlePane;
import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.article.model.DefaultArticleModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.clients.client.ClientCrudFrame;
import com.yolaine.client.ui.clients.client.ClientPane;
import com.yolaine.client.ui.clients.deposant.DeposantCrudFrame;
import com.yolaine.client.ui.clients.deposant.DeposantPane;
import com.yolaine.client.ui.transaction.paiement.model.DefaultPaiementModel;
import com.yolaine.client.ui.transaction.paiement.model.PaiementModel;
import com.yolaine.client.ui.transaction.remboursement.model.DefaultRemboursementModel;
import com.yolaine.client.ui.transaction.remboursement.model.RemboursementModel;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.DepotPane;
import com.yolaine.client.ui.depots.model.DefaultDepotModel;
import com.yolaine.client.ui.depots.model.DepotModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.Adresse;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.entity.client.TypeIdentite;


public class DeposantTableModel extends YolaineTableModel<Client> {

	private static final long serialVersionUID = 319512146319841375L;

	private int defaultWidth = 640;
    private int defaultHeight = 480;	
	
	@Override
	protected List<Client> buildDataList(Client client) {
		return ClientDelegate.trouverClients(true);
	}

	@Override
	protected Object[][] getColumnProperties() {
		return new Object[][] {
				{
					"Nom", String.class, 170
				},{
					"Prénom", String.class, 170
				},{
					"Telephone fixe", String.class, 130
				},{
					"Telephone portable", String.class, 130
				},{
					"Code postal", String.class, 130
				},{
					"Ville", String.class, 130
				}
		};
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Client data = dataList.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return data.getNom();
		case 1:
			return data.getPrenom(); 		         
		case 2:
			return data.getTelephonefixe();
		case 3:
			return data.getTelephoneportable();           
		case 4:
			return data.getAdresse().getCodePostal();
		case 5:
			return data.getAdresse().getVille();            
		default:
			return null;
		}
	}

	protected void displayException(String sourceClass, String sourceMethod,
			Throwable throwable) {
		Throwable cause = getRootCause(throwable);
		if (isApplicationException(cause)) {
			displayWarning(cause.getMessage());
		} else {
			displayError(throwable.getMessage());
			
		}
	}

	protected void displayWarning(String message) {
		
	}

	protected void displayError(String message) {
		
	}

	@Override
	public String getDefaultTitle() {
		return "Lister tous les déposants";
	}

	@Override
	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		DeposantModel deposantmodel = null;
		
		if (selectedRowIndex != null) {
			Client client = ClientDelegate.trouverClient(dataList.get(
					selectedRowIndex).getNom(),dataList.get(
							selectedRowIndex).getPrenom());
			
			deposantmodel = new DefaultDeposantModel(client);
		}
		
		DeposantPane deposantcomponent = new DeposantPane(deposantmodel, viewType);
		YolaineCrudFrame deposantframe = new DeposantCrudFrame(deposantcomponent);
		
		deposantframe.pack();
		
		return deposantframe;
	}

	@Override
	public ArticleCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		ArticleModel articlemodel = null;
		if (selectedRowIndex != null) {
			Client client = ClientDelegate.trouverClient(dataList.get(
					selectedRowIndex).getNom(),dataList.get(
							selectedRowIndex).getPrenom());
			Article article = new Article();
			article.setClientArticle(client);
			articlemodel = new DefaultArticleModel(article);

		}

		ArticlePane articlecomponent = new ArticlePane(articlemodel,viewType);	       
		ArticleCrudFrame articleframe = new ArticleCrudFrame(articlecomponent); 

		articleframe.pack();
		return articleframe;	

	}

	@Override
	public DepotCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {		
		DepotModel depotmodel = null;
		ArticleModel articlemodel = null;
		if (selectedRowIndex != null) {
			Client client = ClientDelegate.trouverClient(dataList.get(
					selectedRowIndex).getNom(),dataList.get(
							selectedRowIndex).getPrenom());
			Depot depot = new Depot();
			depot.setClient(client);
			List<Depot> listedepot = ClientDelegate.trouverTousDepots();

			depot.setId((long) (listedepot.size() + 1));

			if (listedepot.isEmpty()){
				try { 
					String date  = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
					String [] split = date.split("/");
					String year = split[2];
					String result = year.substring(2,4);
					result += "-1";
					System.out.println(result);
//					depot.setId(result);
					depot.setDateDepot(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
					Client clienttmp = ClientDelegate.trouverClient(dataList.get(
							selectedRowIndex).getNom(),dataList.get(
									selectedRowIndex).getPrenom());
					depot.setClient(clienttmp);
					articlemodel = new DefaultArticleModel();
					//articlemodel.setId(1);
					System.out.println(" rrrrrrrrrrrr " + articlemodel.getId());
					depotmodel = new DefaultDepotModel(depot);
					depotmodel.setDepot(depot);					
					depotmodel.setIdentifierToFind(1L);
					depotmodel.setArticleModel(articlemodel);
					depotmodel.getArticleModel().setClientArticle(client);
					depotmodel.getDepot().setClient(clienttmp);
					System.out.println(depotmodel.getDepot().getClient().getNom());
					depot = ClientDelegate.creerDepot(depot);
					}
				catch (Exception exc) {
					System.out.println(exc.getMessage());
				}	
			} else {
				try { 										
					depot.setDateDepot(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
					Client clienttmp = ClientDelegate.trouverClient(dataList.get(
							selectedRowIndex).getNom(),dataList.get(
									selectedRowIndex).getPrenom());
					depot.setClient(clienttmp);
					String date  = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
					String [] split = date.split("/");
					String year = split[2];
					String result = year.substring(2,4);
//					String s = listedepot.get(0).getId();
//					s = s.substring(3, s.length());
					for(Depot depottmp : listedepot){
//						String st = depottmp.getId().substring(3,depottmp.getId().length());
//						if(st.length()> s.length()){
//							s = st;
//						}else if (st.length() == s.length()){
//							int s1 = Integer.parseInt(s);
////							int s2 = Integer.parseInt(depottmp.getId().substring(3, depottmp.getId().length()));
//							if (s2>s1){
//							s = String.valueOf(s2);
//						}else if(s2 == s1){
//							System.out.println("nouveau s "+s2);
//							s = String.valueOf(s2);
//						}else{
//						}
//						}else{
//						}
					}					
//					int s1 = Integer.parseInt(s);
//					s1++;
					//result += "-" + String.valueOf(s1) + "";
//					depot.setId((long) listedepot.size());
					articlemodel = new DefaultArticleModel();
					//articlemodel.setId(1);
					//articlemodel.setPaiementModel(new DefaultPaiementModel());
					//articlemodel.setRemboursementModel(new DefaultRemboursementModel());
					//articlemodel.setPaiement(new Paiement());
					//articlemodel.setRemboursement(new Remboursement());
					
					depotmodel = new DefaultDepotModel(depot);					
					depotmodel.setDepot(depot);
					depotmodel.setIdentifierToFind(1L);
					depotmodel.getDepot().setClient(clienttmp);
					depotmodel.setArticleModel(articlemodel);
					depotmodel.getArticleModel().setClientArticle(clienttmp);
					
					depot = ClientDelegate.creerDepot(depot);
				} catch (Exception exc) {
					System.out.println(exc.getMessage());
				}	
			}							
		}
		//PaiementModel paiementModel = new DefaultPaiementModel();
		//RemboursementModel remboursementModel = new DefaultRemboursementModel();
		//depotmodel.getArticleModel().setP
		
		System.out.println("rrr " +viewType.toString());
		
		DepotPane depotcomponent = new DepotPane(depotmodel);
		depotcomponent.setViewType(viewType);
		
		System.out.println("ttttt " +depotcomponent.getViewType().toString());
		DepotCrudFrame depotframe = new DepotCrudFrame(depotcomponent); 		
		depotframe.setSize(defaultWidth + 450, defaultHeight + 100);
		
		PrincipalFrame.getInstance().addAndShowFrame(depotframe);   
		return null;	       
	}

	@Override
	public YolaineTableModel crudTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}

	@Override
	public ArticleTableModel crudArticleTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		DepotModel depotmodel = null;
		Client client = new Client();
		if (selectedRowIndex != null) {
			client = ClientDelegate.trouverClient(dataList.get(
					selectedRowIndex).getNom(),dataList.get(
							selectedRowIndex).getPrenom());
			Depot depot = new Depot();
			depot.setClient(client);
			depotmodel = new DefaultDepotModel(depot);
		}

		DepotPane depotcomponent = new DepotPane(depotmodel,viewType);	       
		ArticleTableModel depotable = new ArticleTableModel(); 
		YolaineListFrame frame = new YolaineListFrame(new ArticleTableModel(client));
		frame.setSize(defaultWidth + 500, defaultHeight + 150);
		PrincipalFrame.getInstance().addAndShowFrame(frame);          
		
		return depotable;

	}

	@Override
	public DepotTableModel crudDepotTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		DepotModel depotmodel = null;
		Client client = new Client();
		if (selectedRowIndex != null) {
			client = ClientDelegate.trouverClient(dataList.get(
					selectedRowIndex).getNom(),dataList.get(
							selectedRowIndex).getPrenom());
			Depot depot = new Depot();
			depot.setClient(client);
			depotmodel = new DefaultDepotModel(depot);

		}

		DepotPane depotcomponent = new DepotPane(depotmodel,viewType);	       
		DepotTableModel depotable = new DepotTableModel(); 
		YolaineListFrame frame = new YolaineListFrame(new DepotTableModel(client));
		frame.setSize(defaultWidth + 500, defaultHeight + 150);
		PrincipalFrame.getInstance().addAndShowFrame(frame);		
		return depotable;

	}

	@Override
	public TypearticleTableModel crudTypearticleTableFactory(
			Integer selectedRowIndex, YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarqueTableModel crudMarqueTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}
}