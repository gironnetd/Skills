package com.yolaine.client.ui.depots.model;


import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.model.ArticleModel;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.article.model.DefaultArticleModel;
import com.yolaine.client.ui.clients.client.model.ClientModel;
import com.yolaine.client.ui.clients.client.model.DefaultClientModel;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.DepotPane;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;


public class DepotTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 319512146319841375L;

	private List<Depot> dataList;    
	private String[] columnNames;
	private Class[] columnClasses;
	private int[] columnRatios;
	public Client client = new Client();   
	public Depot depot = new Depot();
	private List<Depot> list = new ArrayList<Depot>() ;
	public String nom ; 
	private int defaultWidth = 640;
	private int defaultHeight = 480;

	public DepotTableModel() {		
		buildColumnProperties();
		initDataList();
	}	

	public DepotTableModel(Client client, Set<Depot> list) {
		super();		
		this.client.setId(client.getId());
		this.client.setCivilite(client.getCivilite());
		this.client.setDeposante(client.isDeposante());		
		this.nom = client.getNom();		
		this.client.setPrenom(client.getPrenom());
		this.client.setLogin(client.getLogin());
		this.client.setPassword(client.getPassword());
		this.client.setAdresse(client.getAdresse());
		this.client.setTelephonefixe(client.getTelephonefixe());
		this.client.setTelephoneportable(client.getTelephoneportable());
		this.client.setEmail(client.getEmail());
		this.client.setDatenaissance(client.getDatenaissance());
		this.client.setCommentaire(client.getCommentaire());
		this.client.setChampnumerique1(client.getChampnumerique1());
		this.client.setChampnumerique2(client.getChampnumerique2());
		this.client.setArticles(client.getArticles());
		this.client.setDepots(client.getDepots());
		System.out.println(this.client.getNom());
		client.setNom("eeeeeee");		
		System.out.println(client.getNom());		
		buildColumnProperties();
		initDataList();	
		
	}

	public DepotTableModel(Client client) {		
		this.client.setId(client.getId());
		this.client.setCivilite(client.getCivilite());
		this.client.setDeposante(client.isDeposante());
		this.client.setNom(client.getNom());		
		this.nom = client.getNom();		
		this.client.setPrenom(client.getPrenom());
		this.client.setLogin(client.getLogin());
		this.client.setPassword(client.getPassword());
		this.client.setAdresse(client.getAdresse());
		this.client.setTelephonefixe(client.getTelephonefixe());
		this.client.setTelephoneportable(client.getTelephoneportable());
		this.client.setEmail(client.getEmail());
		this.client.setDatenaissance(client.getDatenaissance());
		this.client.setCommentaire(client.getCommentaire());
		this.client.setChampnumerique1(client.getChampnumerique1());
		this.client.setChampnumerique2(client.getChampnumerique2());
		this.client.setArticles(client.getArticles());
		this.client.setDepots(client.getDepots());
		buildColumnProperties();
		initDataList();		
	}	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {		
		this.client = client;
	}	

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public void buildColumnProperties() {
		Object[][] columnProperties = getColumnProperties();

		columnNames = new String[columnProperties.length];
		columnClasses = new Class[columnProperties.length];
		columnRatios = new int[columnProperties.length];

		for (int i = 0; i < columnProperties.length; i++) {
			columnNames[i] = (String) columnProperties[i][0];
			columnClasses[i] = (Class) columnProperties[i][1];
			columnRatios[i] = (Integer) columnProperties[i][2];
		}
	}




	public void initDataList() {
		List<Depot > dataListtmp = nonNullList(buildDataList());
		List<Depot > dataListtmp1 = new ArrayList<Depot>();
		List<Depot > dataListtmp2 = new ArrayList<Depot>();
		List<Depot > dataListtmp3 = new ArrayList<Depot>();
		List<Depot > dataListtmp4 = new ArrayList<Depot>();
		int i = 0;
		String s = " ";		
		for(Depot depot : dataListtmp){
//			String [] st = depot.getId().split("-");
			
//			System.out.println(st[1]);
//			if(s.length() == st[1].length()){
//				System.out.println("i : " + i);
//				System.out.println("s : " +s);
//				System.out.println("s.length() == st.length()");
//				int i1 = Integer.parseInt(st[1]);
//				if(i1> i){
//					System.out.println("i1>i");
//					i=i1;
//					s = String.valueOf(i1);
//					System.out.println(" i : " +String.valueOf(i));
//				dataListtmp1.add(depot);
//				}
//				}else if(s.length() < st[1].length()){
//					System.out.println("i : " + i);
//					System.out.println("s : " + s);
//					System.out.println("st : " + st[1]);
//					System.out.println("s.length() < st.length()");
//					if(st[1].length()==2){
//						dataListtmp2.add(depot);
//					}else if (st[1].length()==3){
//						dataListtmp3.add(depot);
//					}else if (st[1].length()==4){
//						dataListtmp4.add(depot);
//					}
//					System.out.println("dataListtmp.add(depot)");
//
//					System.out.println("dataListtmp.remove(depot)");
//					System.out.println("s : " + s);
//				}
			}

			dataListtmp1.addAll(dataListtmp2);
			dataListtmp1.addAll(dataListtmp3);
			dataListtmp1.addAll(dataListtmp4);	
			
		this.dataList = dataListtmp1;
			fireTableStructureChanged();
		}   

		public void add(Depot depot) {
			dataList.add(depot);
			fireTableStructureChanged();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return dataList.size();
		} 

		private static <E> List<E> nonNullList(List<E> list) {
			return (list != null) ? list : new ArrayList<E>();
		}    

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}    
		public int[] getColumnRatios() {
			return columnRatios;
		}    

		public List<Depot> buildDataList() {	
			if (client.getNom() != null && !client.getNom().equals(""))	{
				return ClientDelegate.trouverDepots(this.client.getId());
			}else{
				return ClientDelegate.trouverTousDepots();
			}
		}

		public Object[][] getColumnProperties() {
			return new Object[][] {
					{
						"Numero de dépôt", Integer.class, 170
					},{
						"Client", String.class, 170
					},{
						"Date de dépôt", String.class, 170
					},{
						"Dépôt clôturé", Boolean.class, 170
					},{
						"Nombre d'articles",Integer.class, 130
					}

			};
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Depot data = dataList.get(rowIndex);
			List<Article> listeArticles = CatalogDelegate.findArticles((long) rowIndex);
			switch (columnIndex) {
			case 0:
				return rowIndex;
			case 1:
				return data.getClient().getNom();
			case 2:
				return data.getDateDepot();
			case 3:
				return data.isClotureDepot();
			case 4:
				if(listeArticles.isEmpty()){
					return listeArticles.size();
				}else{
					return listeArticles.size();
				}

				default:
				return null;
			}
		}


		public String getDefaultTitle() {
			String text = "Lister tous les dépôts";
			
			if (this.client.getPrenom() != null && !this.client.getPrenom().equals("")){
				text += " de - " + client.getPrenom();
				
				if (this.client.getNom() != null && !this.client.getNom().equals("")){
					text += " " + client.getNom() + " ";
					return text;					
				}
			}else if (this.client.getNom() != null && !this.client.getNom().equals("")){
				text += " de - " + client.getNom() + " ";
				return text;					
			}
			
			return text;
		}


		public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
				YolaineViewType viewType) {
			System.out.println("test");
			DepotModel model = null;
			ArticleModel articlemodel = null;
			if (selectedRowIndex != null) {
//				Depot depot = ClientDelegate.trouverDepot(dataList.get(
//						selectedRowIndex).getId());
				Client clienttmp = ClientDelegate.trouverClient(dataList.get(
						selectedRowIndex).getClient().getNom(),dataList.get(
								selectedRowIndex).getClient().getPrenom());
				depot.setClient(clienttmp);
				model = new DefaultDepotModel(depot);
				articlemodel = new DefaultArticleModel();
				articlemodel.setClientArticle(clienttmp);
				model.setArticleModel(articlemodel);
			}
			
			DepotPane component = new DepotPane(model, viewType);
			YolaineCrudFrame frame = new DepotCrudFrame(component);
			frame.pack();

			return frame;

		}

		public  YolaineCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
				YolaineViewType viewType){
			return null;
		}

		public  YolaineCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
				YolaineViewType viewType){
			DepotModel model = null;

			if (selectedRowIndex != null) {
//				Depot depot = ClientDelegate.trouverDepot(dataList.get(
//						selectedRowIndex).getId());

				model = new DefaultDepotModel(depot);
			}

			DepotPane component = new DepotPane(model, viewType);        
			YolaineCrudFrame frame = new DepotCrudFrame(component);
			frame.pack();

			return frame;
		}


		public ArticleTableModel crudArticleTableFactory(Integer selectedRowIndex) {

			DepotModel depotmodel = null;
			Depot depot = new Depot();
			if (selectedRowIndex != null) {
//				depot = ClientDelegate.trouverDepot(dataList.get(selectedRowIndex).getId());
				ArticleTableModel depotable = new ArticleTableModel(depot);
				depotable.crudArticleTableFactory(depot);
				YolaineListFrame frame = new YolaineListFrame(depotable);
				frame.setSize(defaultWidth + 500, defaultHeight + 150);
				PrincipalFrame.getInstance().addAndShowFrame(frame);
				return null;
			}    	
			DepotPane depotcomponent = new DepotPane(depotmodel);		
			ArticleTableModel depotable = new ArticleTableModel(depot);    	    	
			return null;
		}


	}