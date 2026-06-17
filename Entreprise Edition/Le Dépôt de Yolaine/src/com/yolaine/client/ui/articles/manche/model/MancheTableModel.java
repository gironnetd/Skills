package com.yolaine.client.ui.articles.manche.model;


import java.util.ArrayList;
import java.util.List;

import com.yolaine.client.delegate.CatalogDelegate;

import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.couleur.CouleurCrudFrame;
import com.yolaine.client.ui.articles.couleur.CouleurPane;
import com.yolaine.client.ui.articles.manche.MancheCrudFrame;
import com.yolaine.client.ui.articles.manche.ManchePane;
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
import com.yolaine.entity.catalogue.Manche;

public class MancheTableModel extends YolaineTableModel<Manche> {

	private static final long serialVersionUID = -1390218879148406609L;


	@Override
	protected List<Manche> buildDataList(Manche manche) {
		
		 List<Manche> listemanches = CatalogDelegate.trouverManches();
	        List<Manche> newList = new ArrayList<Manche>();
	        for (Manche manchetmp : listemanches){
	        	if ( !manchetmp.getManche().equals("")){
	        		newList.add(manchetmp);
	        }else{
	        		
	        	}
	        }
	        return newList;	
		
		
	}

	@Override
	protected Object[][] getColumnProperties() {
		return new Object[][] {
				{
					"Manche", String.class, 80
				}
		};
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Manche data = dataList.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return data.getManche();           
		default:
			return null;
		}
	}


	@Override
	public String getDefaultTitle() {
		return "Liste de toutes les types de manches";
	}

	@Override
	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		MancheModel model = null;

		if (selectedRowIndex != null) {
			Manche manche = CatalogDelegate.trouverManche(dataList.get(selectedRowIndex).getId());

			model = new DefaultMancheModel(manche);
		}

		ManchePane component = new ManchePane(model, viewType);
		YolaineCrudFrame frame = new MancheCrudFrame(component);
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

	
	public MarqueTableModel crudMarqueTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}