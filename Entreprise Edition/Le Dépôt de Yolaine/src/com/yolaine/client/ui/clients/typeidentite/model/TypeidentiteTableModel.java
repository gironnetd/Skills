package com.yolaine.client.ui.clients.typeidentite.model;


import java.util.ArrayList;
import java.util.List;

import com.yolaine.client.delegate.CatalogDelegate;
import com.yolaine.client.delegate.ClientDelegate;

import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.clients.typeidentite.TypeidentiteCrudFrame;
import com.yolaine.client.ui.clients.typeidentite.TypeidentitePane;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.catalogue.Manche;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public class TypeidentiteTableModel extends YolaineTableModel<TypeIdentite> {

    private static final long serialVersionUID = 319512146319841375L;


    @Override
    protected List<TypeIdentite> buildDataList(TypeIdentite typeidentite) {
       
    	 List<TypeIdentite> listeidentites = ClientDelegate.trouverTypeIdentites();
	        List<TypeIdentite> newList = new ArrayList<TypeIdentite>();
	        for (TypeIdentite identitetmp : listeidentites){
	        	if ( !identitetmp.getTypeIdentite().equals("")){
	        		newList.add(identitetmp);
	        }else{}
	        }
	        return newList;	
    	
    	
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][] {
        		{
        				"Type identité", String.class, 170
        		}
        		};
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TypeIdentite data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getTypeIdentite();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lister tous les types identité";
    }

    @Override
    public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
            YolaineViewType viewType) {
        TypeidentiteModel model = null;

        if (selectedRowIndex != null) {
            TypeIdentite typeidentite = ClientDelegate.trouverTypeIdentite(dataList.get(
                    selectedRowIndex).getId());

            model = new DefaultTypeidentiteModel(typeidentite);
        }

        TypeidentitePane component = new TypeidentitePane(model, viewType);
        YolaineCrudFrame frame = new TypeidentiteCrudFrame(component);
        frame.pack();

        return frame;
    }

	@Override
	public ArticleCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepotCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YolaineTableModel crudTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleTableModel crudArticleTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepotTableModel crudDepotTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
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