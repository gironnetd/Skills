package com.yolaine.client.ui.articles.typearticle.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import com.yolaine.client.delegate.CatalogDelegate;

import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.TypearticleCrudFrame;
import com.yolaine.client.ui.articles.typearticle.TypearticlePane;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.client.ui.util.event.YolaineCrudActionAdapter;
import com.yolaine.client.ui.util.event.YolaineCrudActionListener;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Marque;
import com.yolaine.entity.client.Depot;

public class TypearticleTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1390218879148406609L;


	private List<Categorie> dataList; 
	Marque marque ;
	private String[] columnNames;
	private Class[] columnClasses;
	private int[] columnRatios;	
	String nomtmp;

	public TypearticleTableModel() {		
		buildColumnProperties();
		initDataList();	
	}    

	public TypearticleTableModel(Marque marque) {
		this.marque = new Marque();
		this.marque = marque;
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




	protected List<Categorie> buildDataList() {		

		if (marque == null){
			List<Categorie> categoriesTmp = CatalogDelegate.findCategories();
			List<Categorie> categories =  new ArrayList<Categorie>();
			for(Categorie categorie: categoriesTmp){
				if (!categorie.getName().equals("")){
					categories.add(categorie);
				}
			}
			return categories;
		}else if (marque.getName() != null|| !marque.getName().equals("")){	
			List<Article> listearticles = CatalogDelegate.findArticlesbyMarque(marque.getId());
			if(listearticles.size() == 0){
				return null;
			}else{
				nomtmp = listearticles.get(0).getMarque().getName();

				List<Categorie> categories = new ArrayList<Categorie>();

				for(Article article : listearticles){
					if (article.getMarque().getName().equals(marque.getName())){ 
						//System.out.println(article.getTypearticle().getName());
						
						categories.add(article.getCategorie());
						
					}else{

					}
				}

				List<Categorie> categoriestmp = new ArrayList<Categorie>();

				if (categories.size() == 0){
					return null;
				} else {
					String nomtmp1 = categories.get(0).getName();
					categories.get(0).setDescription("0");
					System.out.println(nomtmp1);
					for(Categorie categorie : categories){
						int i1 = 1;								
						if(categorie.getName().equals(nomtmp1)){
							int i2 = Integer.parseInt(categorie.getDescription());
							i2++;
							categoriestmp.remove(categorie);
							categorie.setDescription(String.valueOf(i2));
							categoriestmp.add(categorie);									
						}else if (!categorie.getName().equals(nomtmp1)){
							categorie.setDescription(String.valueOf(i1));
							categoriestmp.add(categorie);
							nomtmp1 = categorie.getName();									
						}else{

						}
					}							
					return categoriestmp;
				}
			}					
		}
		return null;			
	}


	protected Object[][] getColumnProperties() {
		if(marque == null){
			return new Object[][] {
					{
						"Nom", String.class, 320
					}, {
						"Description", String.class, 600
					}
			};
		}else if ( marque.getName() != null|| !marque.getName().equals("") ){
			return new Object[][] {
					{
						"Nom", String.class, 320
					},{
						"Nombre d'articles", Integer.class, 600
					}
			};
		} else {
			return new Object[][] {
					{
						"Nom", String.class, 320
					}, {
						"Description", String.class, 600
					}
			};
		}

	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Categorie data = dataList.get(rowIndex);
		if (marque == null){
			switch (columnIndex) {            
			case 0:
				return data.getName();
			case 1:
				return data.getDescription();
			default:
				return null;
			}
		} else if( marque.getName() != null|| !marque.getName().equals("") ){			

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
			case 2:				
				return data.getDescription();
			default:
				return null;
			}
		}		
	}



	public String getDefaultTitle() {
		String text ;

		if (marque == null){			
			text = "Liste de tous les types d'articles";
			return text;
		}else if ( marque.getName() != null|| !marque.getName().equals("") ) {
			text = "Liste de tous les types d'articles de la marque " + marque.getName();			
		} else {
			text = "Liste de tous les types d'articles";
			return text;
		}		
		return text;
	}


	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		TypearticleModel model = null;

		if (selectedRowIndex != null) {
			Categorie category = CatalogDelegate.findCategory(dataList.get(
					selectedRowIndex).getId());

			model = new DefaultTypearticleModel(category);
		}

		TypearticlePane component = new TypearticlePane(model, viewType);
		YolaineCrudFrame frame = new TypearticleCrudFrame(component);
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


	public TypearticleTableModel crudTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}


	public ArticleTableModel crudArticleTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		
		ArticleTableModel articleModel = null;
		Categorie categorie = new Categorie();
		if (selectedRowIndex != null) {
			categorie = CatalogDelegate.findCategory(dataList.get(selectedRowIndex).getId());
			System.out.println(categorie.getName());

		}	
		if (marque != null){
		articleModel =  new ArticleTableModel(marque,categorie); 
		}else {
			articleModel =  new ArticleTableModel(categorie); 
		}
		
		
		return articleModel;	
	}


	public DepotTableModel crudDepotTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {

		return null;
	}

	public TypearticleTableModel crudTypearticleTableFactory(
			int selectedRowIndex, YolaineViewType viewType) {

		return null;
	}

	public MarqueTableModel crudMarqueTableFactory(
			Integer selectedRowIndex, YolaineViewType viewType) {

		MarqueTableModel marqueModel = null;
		Categorie categorie = new Categorie();
		if (selectedRowIndex != null) {
			categorie = CatalogDelegate.findCategory(dataList.get(selectedRowIndex).getId());
			System.out.println(categorie.getName());

		}		

		marqueModel =  new MarqueTableModel(categorie); 			
		return marqueModel;	
	}

	public void addYolaineCrudActionListener(
			YolaineCrudActionAdapter listener) {
		listenerList.add(YolaineCrudActionListener.class, listener);
	}

}