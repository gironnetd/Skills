package com.yolaine.client.ui.articles.marque.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;

import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.MarqueCrudFrame;
import com.yolaine.client.ui.articles.marque.MarquePane;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.DepotPane;
import com.yolaine.client.ui.depots.model.DefaultDepotModel;
import com.yolaine.client.ui.depots.model.DepotModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.event.YolaineCrudActionAdapter;
import com.yolaine.client.ui.util.event.YolaineCrudActionListener;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.Depot;


public class MarqueTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 840315127122345076L;

	private List<Marque> dataList; 
	Categorie categorie ;
	private String[] columnNames;
	private Class[] columnClasses;
	private int[] columnRatios;
	List<Article> listearticles;
	String nomtmp;


	public MarqueTableModel() {		
		buildColumnProperties();
		initDataList();
	}    

	public MarqueTableModel(Categorie categorie) {		
		this.categorie = new Categorie();
		this.categorie = categorie;
		buildColumnProperties();
		initDataList();
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




	
	protected List<Marque> buildDataList() {		

		if (categorie == null){
			return CatalogDelegate.trouverMarques();
		}else if (categorie.getName() != null|| !categorie.getName().equals("")){	
			List<Article> listearticles = CatalogDelegate.findArticlesbyCategorie(categorie.getId());
			if(listearticles.size() == 0){
				return null;
			}else{
				nomtmp = listearticles.get(0).getCategorie().getName();

				List<Marque> marques = new ArrayList<Marque>();

				for(Article article : listearticles){
					if (article.getCategorie().getName().equals(categorie.getName())){
						System.out.println(article.getMarque().getName());
						marques.add(article.getMarque());					
					}else{

					}
				}

				List<Marque> marquestmp = new ArrayList<Marque>();

				if (marques.size() == 0){
					return null;
				} else {
					String nomtmp1 = marques.get(0).getName();
					marques.get(0).setDescription("0");
					System.out.println(nomtmp1);
					for(Marque marque : marques){
						int i1 = 1;								
						if(marque.getName().equals(nomtmp1)){
							int i2 = Integer.parseInt(marque.getDescription());
							i2++;
							marquestmp.remove(marque);
							marque.setDescription(String.valueOf(i2));
							marquestmp.add(marque);									
						}else if (!marque.getName().equals(nomtmp1)){
							marque.setDescription(String.valueOf(i1));
							marquestmp.add(marque);
							nomtmp1 = marque.getName();									
						}else{

						}
					}							
					return marquestmp;
				}
			}					
		}
		return null;			
	}


	protected Object[][] getColumnProperties() {
		if (categorie == null){
			return new Object[][] {
					{
						"Marque", Long.class, 300
					},{
						"Commentaire", String.class, 400
					}
			};
		} else if ( categorie.getName() != null|| !categorie.getName().equals("") ){
			return new Object[][] {
					{
						"Marque", Long.class, 300
					},{
						"Nombre d'articles", Integer.class, 400
					}
			};
		} else {
			return new Object[][] {
					{
						"Marque", Long.class, 300
					},{
						"Commentaire", String.class, 400
					}
			};
		}

	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Marque data = dataList.get(rowIndex);
		
		if (categorie == null){
			switch (columnIndex) {           
			case 0:
				return data.getName();
			case 1:
				return data.getDescription();            
			default:
				return null;
			}
		} else if ( categorie.getName() != null|| !categorie.getName().equals("") ){
			
			switch (columnIndex) {           
			case 0:				
				return data.getName();
			case 1:
				return data.getDescription();
						 
			default:
				return null;
			}
		} else {
			switch (columnIndex) {           
			case 0:
				return data.getName();
			case 1:
				return data.getDescription();            
			default:
				return null;
			}
		}        
	}

	public String getDefaultTitle() {
		String text;
		if(categorie == null){
			text = "Liste de toutes les marques";
			return text;
		} else if ( categorie.getName() != null|| !categorie.getName().equals("") ) {
			text =  "Liste de toutes les marques du type d'article " + categorie.getName();
		} else {
			text =  "Liste de toutes les marques";
			return text;
		}
		return text;
	}


	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		MarqueModel model = null;

		if (selectedRowIndex != null) {
			Marque product = CatalogDelegate.trouverMarque(dataList.get(
					selectedRowIndex).getId());

			model = new DefaultMarqueModel(product);
		}

		MarquePane component = new MarquePane(model, viewType);
		YolaineCrudFrame frame = new MarqueCrudFrame(component);
		frame.pack();

		return frame;
	}


	public ArticleCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}


	public DepotCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}


	public YolaineTableModel crudTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}


	public ArticleTableModel crudCategorieTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}


	public DepotTableModel crudDepotTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}


	public ArticleTableModel crudArticleTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		ArticleTableModel articleModel = null;
		Marque marque = new Marque();
		if (selectedRowIndex != null) {
			marque = CatalogDelegate.trouverMarque(dataList.get(selectedRowIndex).getId());

		}	
		if (categorie != null){
		articleModel =  new ArticleTableModel(marque,categorie); 
		}else {
			articleModel =  new ArticleTableModel(marque);
		}
		return articleModel;	
	}


	public TypearticleTableModel crudTypearticleTableFactory(
			Integer selectedRowIndex, YolaineViewType viewType) {
		TypearticleTableModel typeArticleModel = null;
		Marque marque = new Marque();
		if (selectedRowIndex != null) {
			marque = CatalogDelegate.trouverMarque(dataList.get(selectedRowIndex).getId());
			System.out.println(marque.getName());			
		}		

		typeArticleModel =  new TypearticleTableModel(marque); 
		
		return typeArticleModel;		
	}


	public MarqueTableModel crudMarqueTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}

	public void addYolaineCrudActionListener(
			YolaineCrudActionAdapter listener) {
		listenerList.add(YolaineCrudActionListener.class, listener);

	}

}