package com.yolaine.client.ui.transaction.typepaiement.model;


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
import com.yolaine.client.ui.transaction.typepaiement.TypePaiementCrudFrame;
import com.yolaine.client.ui.transaction.typepaiement.TypePaiementPane;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.transaction.TypePaiement;
import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.catalogue.Marque;

public class TypePaiementTableModel extends YolaineTableModel<TypePaiement> {

	private static final long serialVersionUID = -1390218879148406609L;


	@Override
	protected List<TypePaiement> buildDataList(TypePaiement couleur) {
		
		 List<TypePaiement> listetypepaiements = CatalogDelegate.trouverTypePaiements();
	        List<TypePaiement> newList = new ArrayList<TypePaiement>();
	        for (TypePaiement typepaiementtmp : listetypepaiements){
	        	if ( !typepaiementtmp.getTypePaiement().equals("")){
	        		newList.add(typepaiementtmp);
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
		TypePaiement data = dataList.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return data.getTypePaiement();
		default:
			return null;
		}
	}


	@Override
	public String getDefaultTitle() {
		return "Liste de tous les moyens de paiements";
	}

	@Override
	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		TypePaiementModel model = null;

		if (selectedRowIndex != null) {
			TypePaiement typePaiement = CatalogDelegate.trouverTypePaiement(dataList.get(selectedRowIndex).getId());

			model = new DefaultTypePaiementModel(typePaiement);
		}

		TypePaiementPane component = new TypePaiementPane(model, viewType);
		YolaineCrudFrame frame = new TypePaiementCrudFrame(component);
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