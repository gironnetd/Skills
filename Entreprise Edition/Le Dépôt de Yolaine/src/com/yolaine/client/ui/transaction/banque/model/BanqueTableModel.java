package com.yolaine.client.ui.transaction.banque.model;

import java.util.ArrayList;
import java.util.List;
import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.transaction.banque.BanqueCrudFrame;
import com.yolaine.client.ui.transaction.banque.BanquePane;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.transaction.Banque;

public class BanqueTableModel extends YolaineTableModel<Banque> {

	private static final long serialVersionUID = -1390218879148406609L;


	@Override
	protected List<Banque> buildDataList(Banque banque) {
		
		 List<Banque> listeBanques = CatalogDelegate.trouverBanques();
	        List<Banque> newList = new ArrayList<Banque>();
	        for (Banque banquetmp : listeBanques){
	        	if ( !banquetmp.getBanque().equals("")){
	        		newList.add(banquetmp);
	        }else{
	        		
	        	}
	        }
	        return newList;	
		
		
	}

	@Override
	protected Object[][] getColumnProperties() {
		return new Object[][] {
				{
					"Banque", String.class, 80
				}
		};
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Banque data = dataList.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return data.getBanque();           
		default:
			return null;
		}
	}


	@Override
	public String getDefaultTitle() {
		return "Liste de toutes les banques";
	}

	@Override
	public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		BanqueModel model = null;

		if (selectedRowIndex != null) {
			Banque banque = CatalogDelegate.trouverBanque(dataList.get(selectedRowIndex).getId());

			model = new DefaultBanqueModel(banque);
		}

		BanquePane component = new BanquePane(model, viewType);
		YolaineCrudFrame frame = new BanqueCrudFrame(component);
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