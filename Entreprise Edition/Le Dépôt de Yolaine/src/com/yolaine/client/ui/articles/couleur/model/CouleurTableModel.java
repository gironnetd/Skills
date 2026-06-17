package com.yolaine.client.ui.articles.couleur.model;


import java.util.ArrayList;
import java.util.List;

import com.yolaine.client.delegate.CatalogDelegate;

import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.couleur.CouleurCrudFrame;
import com.yolaine.client.ui.articles.couleur.CouleurPane;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.TypearticleCrudFrame;
import com.yolaine.client.ui.articles.typearticle.TypearticlePane;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Marque;

public class CouleurTableModel extends YolaineTableModel<Couleur> {

	private static final long serialVersionUID = -1390218879148406609L;


	@Override
	protected List<Couleur> buildDataList(Couleur couleur) {
		
		 List<Couleur> listecouleurs = CatalogDelegate.trouverCouleurs();
	        List<Couleur> newList = new ArrayList<Couleur>();
	        for (Couleur couleurtmp : listecouleurs){
	        	if ( !couleurtmp.getCouleur().equals("")){
	        		newList.add(couleurtmp);
	        }else{
	        		
	        	}
	        }
	        return newList;		
	}

	@Override
	protected Object[][] getColumnProperties() {
		return new Object[][] {
				{
					"Couleur", String.class, 80
				}
		};
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Couleur data = dataList.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return data.getCouleur();           
		default:
			return null;
		}
	}


	@Override
	public String getDefaultTitle() {
		return "Liste de toutes les couleurs";
	}

	@Override
	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		CouleurModel model = null;

		if (selectedRowIndex != null) {
			Couleur couleur = CatalogDelegate.trouverCouleur(dataList.get(selectedRowIndex).getId());

			model = new DefaultCouleurModel(couleur);
		}

		CouleurPane component = new CouleurPane(model, viewType);
		YolaineCrudFrame frame = new CouleurCrudFrame(component);
		frame.pack();

		return frame;
	}

	@Override
	public ArticleCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		
		return null;
	}

	@Override
	public DepotCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		
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
		
		return null;
	}

	@Override
	public DepotTableModel crudDepotTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		
		return null;
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