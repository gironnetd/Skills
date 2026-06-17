package com.yolaine.client.ui.clients.client.model;


import java.util.List;

import com.yolaine.client.delegate.ClientDelegate;

import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.clients.client.ClientCrudFrame;
import com.yolaine.client.ui.clients.client.ClientPane;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineCrudFrame;
import com.yolaine.client.ui.util.YolaineTableModel;
import com.yolaine.client.ui.util.YolaineViewType;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;


public class ClientTableModel extends YolaineTableModel<Client> {

    private static final long serialVersionUID = 319512146319841375L;


    @Override
    protected List<Client> buildDataList(Client client) {
        return ClientDelegate.trouverClients(false);
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][] {
        		{
        				"Nom", String.class, 170
        		},{
                    	"Prénom", String.class, 170
        		},{
                        "Déposant", String.class, 170
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
                return data.isDeposante();           
            case 3:
                return data.getTelephonefixe();
            case 4:
            	return data.getTelephoneportable();           
            case 5:
            	return data.getAdresse().getCodePostal();
            case 6:
                return data.getAdresse().getVille();            
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lister tous les clients";
    }

    @Override
    public YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
            YolaineViewType viewType) {
        ClientModel model = null;

        if (selectedRowIndex != null) {
            Client client = ClientDelegate.trouverClient(dataList.get(
                    selectedRowIndex).getNom(),dataList.get(
                            selectedRowIndex).getPrenom());

            model = new DefaultClientModel(client);
        }

        ClientPane component = new ClientPane(model, viewType);
        YolaineCrudFrame frame = new ClientCrudFrame(component);
        frame.pack();

        return frame;
    }

	
	public ArticleCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public DepotCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public YolaineTableModel crudTableFactory(Integer selectedRowIndex,
			YolaineViewType viewType) {
		// TODO Auto-generated method stub
		return null;
	}

	
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