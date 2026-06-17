package com.yolaine.client.ui.articles.article.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import jxl.biff.drawing.ComboBox;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.ArticlePane;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.DepotPane;
import com.yolaine.client.ui.depots.model.DefaultDepotModel;
import com.yolaine.client.ui.depots.model.DepotModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.combo.CiviliteComboItem;
import com.yolaine.client.ui.util.event.YolaineCrudActionAdapter;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;
import com.yolaine.exception.ValidationException;


public class ArticleTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7491304099108274347L;

	private List<Article> dataList;
	public Depot depot = new Depot();
	private String situation;
	private String[] columnNames;
	private Class[] columnClasses;
	private int[] columnRatios;    
	private boolean byMarque = false;
	private boolean byCategorie = false;
	private Marque marque ;
	private Categorie categorie ;
	
	public ArticleTableModel() {
		buildColumnProperties();
		initDataList();
	}    

	public ArticleTableModel(String situation){
		this.situation = situation;
		buildColumnProperties();
		initDataList();
	}
	
	
	public ArticleTableModel(Depot depot) {		
		this.depot = depot;
		buildColumnProperties();
		initDataList();
	}

	public ArticleTableModel(Client client){
		this.depot.setClient(client);
		buildColumnProperties();
		initDataList();
	}	
	
	
	
	public ArticleTableModel(Marque marque) {
		this.marque = new Marque();
		this.marque = marque;
		buildColumnProperties();
		initDataList();
	}

	
	
	public ArticleTableModel(Categorie categorie) {		
		this.categorie = new Categorie();
		this.categorie = categorie;
		buildColumnProperties();
		initDataList();
	}

	public ArticleTableModel(Marque marque,Categorie categorie) {
		byMarque = true;
		this.marque = new Marque();
		this.categorie = new Categorie();		
		this.categorie = categorie;
		this.marque = marque;
		buildColumnProperties();
		initDataList();
	}	
	
	public ArticleTableModel(Categorie categorie,Marque marque) {
		byCategorie= true;
		this.categorie = new Categorie();
		this.marque = new Marque();
		this.marque = marque;
		this.categorie = categorie;
		buildColumnProperties();
		initDataList();
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
		this.dataList = nonNullList(buildDataList());			
	}   

	public void add(Article article) {
		dataList.add(article);
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


	
	public List<Article> buildDataList() {
		if (depot.getDateDepot() == null && depot.getClient() != null) {
			return CatalogDelegate.findClientArticles(depot.getClient().getId());
		}else if (depot.getDateDepot() != null  && !depot.getDateDepot().equals("") && depot.getClient() != null){
			return CatalogDelegate.findArticles(depot.getId());
		}else if (situation != null){
			return CatalogDelegate.findArticlesbySituation(situation);
		} else if (byMarque == true){
			return CatalogDelegate.findArticlesbyCategorieAndMarque(categorie.getId(), marque.getId());
		}else if (byCategorie == true){
			return CatalogDelegate.findArticlesbyMarqueAndCategorie(marque.getId(), categorie.getId());
		} else if (marque != null && byMarque == false){
			return CatalogDelegate.findArticlesbyMarque(marque.getId());
		} else if (categorie != null && byCategorie == false){
			return CatalogDelegate.findArticlesbyCategorie(categorie.getId());
		} else {
			return CatalogDelegate.findArticles();
		}
	}

	public Object[][] getColumnProperties() {
		if (depot.getDateDepot() == null && depot.getClient() != null){
			return new Object[][] {
					{
						"N° de l'article", Long.class, 80
					},{
						"Situation", String.class, 200
					},{
						"Type article", String.class, 300
					},{
						"Marque", String.class, 300
					},{
						"Prix de vente", String.class, 300
					},{
						"Prix déposé", String.class, 300
					}
			};
		}else if (depot.getDateDepot() != null  && !depot.getDateDepot().equals("")){
			return new Object[][] {
					{
						"N° de l'article", Long.class, 80
					},{
						"Situation", String.class, 200
					},{
						"Type d'artilcle", String.class, 300
					},{
						"Marque", String.class, 300
					},{
						"Prix de vente", String.class, 300
					},{
						"Prix déposé", String.class, 300
					}
			};			
		} else if (byMarque == true){
			return new Object[][] {
					{
						"N° de l'article", Long.class, 80
					},{
						"Situation", String.class, 200
					},{
						"Type d'artilcle", String.class, 300
					},{
						"Marque", String.class, 300
					},{
						"Prix de vente", String.class, 300
					},{
						"Prix déposé", String.class, 300
					}
			};
		}else if (byCategorie == true){
			return new Object[][] {
					{
						"N° de l'article", Long.class, 80
					},{
						"Situation", String.class, 200
					},{
						"Type d'artilcle", String.class, 300
					},{
						"Marque", String.class, 300
					},{
						"Prix de vente", String.class, 300
					},{
						"Prix déposé", String.class, 300
					}
			};
		}else{
			return new Object[][] {
					{
						"N° de l'article", Long.class, 200
					},{
						"N° du dépôt ", String.class,200
					},{
						"Nom du déposant", String.class, 200
					},{
						"Situation", String.class, 200
					},{
						"Type article", String.class, 200
					},{
						"Marque", String.class, 350
					},{
						"Prix déposé ", String.class, 200
					},{
						"Prix de vente ", String.class,200
					}
			};
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Article data = dataList.get(rowIndex);
		if (depot.getDateDepot() == null && depot.getClient() != null){
			switch (columnIndex) {
			case 0:
//				String [] ts = data.getId().split("-");
//				String s = ts[2];
				return data.getId(); //ts[2];
			case 1:
				return data.getSituation().getSituation();
			case 2:
				return data.getCategorie().getName();
			case 3:
				return data.getMarque().getName();
			case 4:
				return dataList.get(rowIndex).getPrixVente()+ " euros";
			case 5:
				return dataList.get(rowIndex).getMontantDepot()+ " euros";
			default:
				return null;
			}
		}else if (depot.getDateDepot() != null  && !depot.getDateDepot().equals("")){
			switch (columnIndex) {
			case 0:
//				String [] ts = data.getId().split("-");
//				String s = ts[2];
				return data.getId(); //ts[2];
			case 1:
				return data.getSituation().getSituation();
			case 2:
				return data.getCategorie().getName();
			case 3:
				return data.getMarque().getName();
			case 4:
				return dataList.get(rowIndex).getPrixVente()+ " euros";
			case 5:
				return dataList.get(rowIndex).getMontantDepot()+ " euros";
			default:
				return null;
			}
		} else if (byMarque == true){
			switch (columnIndex) {
			case 0:
//				String [] ts = data.getId().split("-");
//				String s = ts[2];
				return data.getId(); //ts[2];
			case 1:
				return data.getSituation().getSituation();
			case 2:
				return data.getCategorie().getName();
			case 3:
				return data.getMarque().getName();
			case 4:
				return dataList.get(rowIndex).getPrixVente()+ " euros";
			case 5:
				return dataList.get(rowIndex).getMontantDepot()+ " euros";
			default:
				return null;
			}
		}else if (byCategorie == true){
			switch (columnIndex) {
			case 0:
//				String [] ts = data.getId().split("-");
//				String s = ts[2];
				return data.getId(); //ts[2];
			case 1:
				return data.getSituation().getSituation();
			case 2:
				return data.getCategorie().getName();
			case 3:
				return data.getMarque().getName();
			case 4:
				return dataList.get(rowIndex).getPrixVente()+ " euros";
			case 5:
				return dataList.get(rowIndex).getMontantDepot()+ " euros";
			default:
				return null;
			}
		}
		
		else{
			switch (columnIndex) {
			case 0:
//				String [] ts = data.getId().split("-");
//				String s = ts[2];
				return data.getId(); //ts[2];
			case 1:
				return data.getDepot().getId();
			case 2:
				return data.getClientArticle().getNom();
			case 3:
				return data.getSituation().getSituation();
						
			case 4:
				return data.getCategorie().getName();
			case 5 : 
				return data.getMarque().getName();
			case 6:
				return dataList.get(rowIndex).getMontantDepot() + " euros";
			case 7:
				return dataList.get(rowIndex).getPrixVente() + " euros";
			default:
				return null;
			}
		}		
	}    

	
	public String getDefaultTitle() {
		String text;

		if (depot.getDateDepot() != null && !depot.getDateDepot().equals("")){
			text = "Liste des articles du dépôt n° " + depot.getId() +
			" de " + depot.getClient().getCivilite().getCivilite() + " "
			+ depot.getClient().getNom() + "";
			return text;    		
		}else if (depot.getDateDepot() == null  && depot.getClient() != null){
			text = "Liste de tous les articles de " + depot.getClient().getCivilite().getCivilite() + " ";
			if (depot.getClient().getPrenom() != null && !depot.getClient().getPrenom().equals("")){
				text += "" + depot.getClient().getPrenom() + " ";
			 if (depot.getClient().getNom() != null && !depot.getClient().getNom().equals("")){
				text +="" + depot.getClient().getNom() + "";
			}
			}else if (depot.getClient().getNom() != null && !depot.getClient().getNom().equals("")){
				text +="" + depot.getClient().getNom() + "";
			}
		}else if (this.situation != null ){
		return text = "Liste de tous les articles " + situation + "s";	
			} else if (byMarque == true){
				return text = "Liste de tous les articles de la marque " +
						"" + marque.getName() + " et de type " + categorie.getName();
			}else if (byCategorie == true){
				return text = "Liste de tous les articles de type " 
					+ categorie.getName() + " et de marque " + marque.getName();
			}  else if (marque != null && byMarque == false){
				return  text = "Liste de tous les articles de la marque '" + marque.getName() + "'";
			} else if (categorie != null && byCategorie == false){
				return text = "Liste de tous les articles de type " + categorie.getName() + "";
			} 		
		else {
				return text = "Liste de tous les articles";	
			}
	
		return text;
		}


public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
		YolaineViewType viewType) {
	ArticleModel model = null;

	if (selectedRowIndex != null) {
		Article article = CatalogDelegate.findArticle(dataList.get(selectedRowIndex)
				.getId());

		model = new DefaultArticleModel(article);
	}
	System.out.println( " articleTableModel crudframefactory viewType : " +viewType);
	//ArticlePane component = new ArticlePane(model, );
	System.out.println("aaaaa");
	YolaineCrudFrame frame = new ArticleCrudFrame(new ArticlePane(model,viewType));
	System.out.println("ttttt");
	frame.pack();
	System.out.println("eeee");

	return frame;
}

public  YolaineCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
        YolaineViewType viewType){
	
		ArticleModel model = null;

		if (selectedRowIndex != null) {
			Article article = CatalogDelegate.findArticle(dataList.get(selectedRowIndex)
					.getId());
			
			if (viewType == YolaineViewType.VENTE_ARTICLE && !article.getSituation().getSituation().equals("déposé")){
				//int i = JOptionPane.showOptionDialog(this, "", "", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
			}

			model = new DefaultArticleModel(article);
		}
		System.out.println( "  : " + viewType);
		
		ArticlePane component = new ArticlePane(model, viewType);
		YolaineCrudFrame frame = new ArticleCrudFrame(component);
		frame.pack();

		return frame;
}


//public  YolaineCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
   //     YolaineViewType viewType){
	// DepotModel model = null;

   //  if (selectedRowIndex != null) {
    //     Depot depot = ClientDelegate.trouverDepot(dataList.get(
    //             selectedRowIndex).getId());

    //     model = new DefaultDepotModel(depot);
    // }

   //  DepotPane component = new DepotPane(model, viewType);
   //  YolaineCrudFrame frame = new DepotCrudFrame(component);
    // frame.pack();

   //  return frame;
//}


public ArticleTableModel crudArticleTableFactory(Depot depot) {

	DepotModel depotmodel = null;
	List<Article> article = new ArrayList<Article>();
	
	article = CatalogDelegate.findArticles(depot.getId());
			       
	ArticleTableModel depotable = new ArticleTableModel(depot);
		
	return depotable;
	
}

public void addYolaineCrudActionListener(
		YolaineCrudActionAdapter yolaineCrudActionAdapter) {
	
	
}
}