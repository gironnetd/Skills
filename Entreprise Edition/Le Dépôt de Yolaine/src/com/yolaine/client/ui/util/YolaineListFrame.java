package com.yolaine.client.ui.util;


import static com.yolaine.client.ui.util.YolaineUIConstants.DEFAULT_BG_COLOR;
import static com.yolaine.client.ui.util.YolaineViewType.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.couleur.model.CouleurTableModel;
import com.yolaine.client.ui.articles.manche.model.MancheTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.clients.client.model.ClientTableModel;
import com.yolaine.client.ui.clients.deposant.model.DeposantTableModel;
import com.yolaine.client.ui.clients.typeidentite.model.TypeidentiteTableModel;
import com.yolaine.client.ui.transaction.banque.model.BanqueTableModel;
import com.yolaine.client.ui.transaction.typepaiement.model.TypePaiementTableModel;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.event.YolaineCommonActionListener;
import com.yolaine.client.ui.util.event.YolaineCrudActionAdapter;
import com.yolaine.client.ui.util.event.YolaineCrudActionListener;
import com.yolaine.client.ui.util.event.YolaineFindActionListener;


public class YolaineListFrame extends YolaineInternalFrame implements
YolaineCrudActionListener, YolaineFindActionListener,
YolaineCommonActionListener {

	private static final long serialVersionUID = 1680009092050042538L;
	private int defaultWidth = 640;
	private int defaultHeight = 480;	

	protected YolaineTable table;
	protected YolaineActionPane actionPane;
	private int b ;

	public YolaineListFrame(YolaineTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER);
			b = 0;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(DepotTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_DEPOT);
			b = 1;
			initDepotComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(ArticleTableModel model) {
		final String actionName = "init";        
		try {
			table = new YolaineTable(model);            
			actionPane = new YolaineActionPane(LISTER_ARTICLE);            
			b = 2;
			initArticleComponent();            
			setSize(defaultWidth + 250, defaultHeight) ;           
			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(ArticleTableModel model,String situation) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			if (situation == "déposé"){
				actionPane = new YolaineActionPane(LISTER_ARTICLE);
			}else if (situation == "vendu"){
				actionPane = new YolaineActionPane(LISTER_ARTICLEVENDU);
			}else if (situation == "remboursé"){
				actionPane = new YolaineActionPane(LISTER_ARTICLEREMBOURSE);
			}else if (situation == "rendu"){
				actionPane = new YolaineActionPane(LISTER_ARTICLERENDU);
			}
			b = 2;
			initArticleComponent();

			setSize(defaultWidth + 250, defaultHeight) ; 

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(MarqueTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_MARQUE);
			b = 3;
			initMarqueComponent();

			setSize(defaultWidth + 300, defaultHeight + 150) ; 

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(TypearticleTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_CATEGORIE);
			b = 4;
			
			initCategorieComponent();
			setSize(defaultWidth + 300, defaultHeight + 150) ; 

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(TypeidentiteTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_TYPEIDENTITE);
			b = 5;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(CouleurTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_COULEUR);
			b = 6;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(MancheTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_MANCHE);
			b = 7;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(BanqueTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_BANQUE);
			b = 8;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(TypePaiementTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_TYPEPAIEMENT);
			b = 9;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	public YolaineListFrame(ClientTableModel model) {
		final String actionName = "init";

		try {
			table = new YolaineTable(model);
			actionPane = new YolaineActionPane(LISTER_CLIENT);
			b = 10;
			initComponent();

			setSize(getDefaultSize());

			actionPane.addYolaineCrudActionListener(this);
			actionPane.addYolaineFindActionListener(this);
			actionPane.addYolaineCommonActionListener(this);
		} catch (Exception exc) {
			displayException(className, actionName, exc);
		}
	}

	protected Dimension getDefaultSize() {
		return new Dimension(600, 500);
	}


	private void initComponent() {
		setTitle(table.getYolaineModel().getDefaultTitle());

		JPanel globalPane = new JPanel();
		globalPane.setLayout(new BorderLayout());

		JPanel mainBodyPane = new JPanel();
		mainBodyPane.setLayout(new BorderLayout());
		mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

		JPanel mainBorderedPane = new JPanel();
		mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
		mainBorderedPane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.add(scrollPane, BorderLayout.CENTER);

		mainBodyPane.add(mainBorderedPane, BorderLayout.CENTER);
		globalPane.add(mainBodyPane, BorderLayout.CENTER);

		JPanel actionBodyPane = new JPanel();
		actionBodyPane.setLayout(new BorderLayout());
		actionBodyPane.setBorder(BorderFactory
				.createLineBorder(Color.white, 10));
		actionBodyPane.add(actionPane, BorderLayout.SOUTH);

		globalPane.add(actionBodyPane, BorderLayout.SOUTH);

		setInnerPane(globalPane);
	}

	private void initArticleComponent() {
		setTitle(table.getYolaineArticleModel().getDefaultTitle());

		JPanel globalPane = new JPanel();
		globalPane.setLayout(new BorderLayout());

		JPanel mainBodyPane = new JPanel();
		mainBodyPane.setLayout(new BorderLayout());
		mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

		JPanel mainBorderedPane = new JPanel();
		mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
		mainBorderedPane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.add(scrollPane, BorderLayout.CENTER);

		mainBodyPane.add(mainBorderedPane, BorderLayout.CENTER);
		globalPane.add(mainBodyPane, BorderLayout.CENTER);

		JPanel actionBodyPane = new JPanel();
		actionBodyPane.setLayout(new BorderLayout());
		actionBodyPane.setBorder(BorderFactory
				.createLineBorder(Color.white, 10));
		actionBodyPane.add(actionPane, BorderLayout.SOUTH);

		globalPane.add(actionBodyPane, BorderLayout.SOUTH);

		setInnerPane(globalPane);
	}

	private void initDepotComponent() {
		setTitle(table.getYolaineDepotModel().getDefaultTitle());

		JPanel globalPane = new JPanel();
		globalPane.setLayout(new BorderLayout());

		JPanel mainBodyPane = new JPanel();
		mainBodyPane.setLayout(new BorderLayout());
		mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

		JPanel mainBorderedPane = new JPanel();
		mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
		mainBorderedPane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.add(scrollPane, BorderLayout.CENTER);

		mainBodyPane.add(mainBorderedPane, BorderLayout.CENTER);
		globalPane.add(mainBodyPane, BorderLayout.CENTER);

		JPanel actionBodyPane = new JPanel();
		actionBodyPane.setLayout(new BorderLayout());
		actionBodyPane.setBorder(BorderFactory
				.createLineBorder(Color.white, 10));
		actionBodyPane.add(actionPane, BorderLayout.SOUTH);

		globalPane.add(actionBodyPane, BorderLayout.SOUTH);

		setInnerPane(globalPane);
	}

	private void initMarqueComponent() {
		setTitle(table.getYolaineMarqueModel().getDefaultTitle());

		JPanel globalPane = new JPanel();
		globalPane.setLayout(new BorderLayout());

		JPanel mainBodyPane = new JPanel();
		mainBodyPane.setLayout(new BorderLayout());
		mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

		JPanel mainBorderedPane = new JPanel();
		mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
		mainBorderedPane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.add(scrollPane, BorderLayout.CENTER);

		mainBodyPane.add(mainBorderedPane, BorderLayout.CENTER);
		globalPane.add(mainBodyPane, BorderLayout.CENTER);

		JPanel actionBodyPane = new JPanel();
		actionBodyPane.setLayout(new BorderLayout());
		actionBodyPane.setBorder(BorderFactory
				.createLineBorder(Color.white, 10));
		actionBodyPane.add(actionPane, BorderLayout.SOUTH);

		globalPane.add(actionBodyPane, BorderLayout.SOUTH);

		setInnerPane(globalPane);
	}

	
	private void initCategorieComponent() {
		setTitle(table.getYolaineCategorieModel().getDefaultTitle());

		JPanel globalPane = new JPanel();
		globalPane.setLayout(new BorderLayout());

		JPanel mainBodyPane = new JPanel();
		mainBodyPane.setLayout(new BorderLayout());
		mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

		JPanel mainBorderedPane = new JPanel();
		mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
		mainBorderedPane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(DEFAULT_BG_COLOR);
		mainBorderedPane.add(scrollPane, BorderLayout.CENTER);

		mainBodyPane.add(mainBorderedPane, BorderLayout.CENTER);
		globalPane.add(mainBodyPane, BorderLayout.CENTER);

		JPanel actionBodyPane = new JPanel();
		actionBodyPane.setLayout(new BorderLayout());
		actionBodyPane.setBorder(BorderFactory
				.createLineBorder(Color.white, 10));
		actionBodyPane.add(actionPane, BorderLayout.SOUTH);

		globalPane.add(actionBodyPane, BorderLayout.SOUTH);

		setInnerPane(globalPane);
	}

	
	public void findActionPerformed(EventObject evt) {
		YolaineCrudFrame frame;
		if (b == 0){
			frame =  table.getYolaineModel().crudFrameFactory(null, CHERCHER);
			frame.setSize(defaultWidth + 250, defaultHeight + 150 );
			PrincipalFrame.getInstance().addAndShowFrame(
					frame);
		}else if (b == 10){
			frame = table.getYolaineModel().crudFrameFactory(null, CHERCHER);
			frame.setSize(defaultWidth + 250, defaultHeight + 150 );
			PrincipalFrame.getInstance().addAndShowFrame(frame);

		}else if (b == 2){
			frame = table.getYolaineArticleModel().crudFrameFactory(null, CHERCHER);
			frame.setSize(defaultWidth + 250, defaultHeight + 150 );
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		}else if (b == 3){
			frame = table.getYolaineMarqueModel().crudFrameFactory(null, CHERCHER);
			frame.setSize(defaultWidth, defaultHeight - 150 );
			PrincipalFrame.getInstance().addAndShowFrame(frame);

		}else if (b == 4){
			frame = table.getYolaineCategorieModel().crudFrameFactory(null, CHERCHER);
			frame.setSize(defaultWidth, defaultHeight - 150 );
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		}else {
			frame = table.getYolaineModel().crudFrameFactory(null, CHERCHER);
			frame.setSize(defaultWidth, defaultHeight - 150 );
			PrincipalFrame.getInstance().addAndShowFrame(frame);
		}
	}

	public void listActionPerformed(EventObject evt) {		
	}


	public void listDepotActionPerformed(EventObject evt) {			
		showFrame(LISTER_DEPOT);
	}

	public void listMarqueActionPerformed(EventObject evt) {
		showFrame(LISTER_MARQUE);		
	}

	public void listCategoryActionPerformed(EventObject evt) {
		showFrame(LISTER_CATEGORIE);
	}


	public void listTypeidentiteActionPerformed(EventObject evt) {	
		showFrame(LISTER_TYPEIDENTITE);
	}    

	public void listArticleActionPerformed(EventObject evt) {		
		showFrame(LISTER_ARTICLE);
	}

	public void listArticleDepotActionPerformed(EventObject evt) {		
		showFrame(LISTER_ARTICLEDEPOT);		
	}


	public void listCouleurActionPerformed(EventObject evt) {
		showFrame(LISTER_COULEUR);
	}

	public void venteActionPerformed(EventObject evt) {
		showFrame(VENTE_ARTICLE);
	}

	
	public void listCategorieMarqueActionPerformed(EventObject evt) {
		showFrame(LISTER_MARQUESCATEGORIE);
		}
	
	public void listMarqueCategorieActionPerformed(EventObject evt) {
		showFrame(LISTER_CATEGORIESMARQUE);
	}
	
	public void remboursementActionPerformed(EventObject evt) {
		showFrame(REMBOURSEMENT_ARTICLE);
	}
	
	public void listArticlesCategorieMarqueActionPerformed(EventObject evt) {
		showFrame(LISTER_ARTICLESCATEGORIEMARQUE);		
	}
	
	public void listArticlesMarqueCategorieActionPerformed(EventObject evt) {
		showFrame(LISTER_ARTICLESMARQUECATEGORIE);		
	}	
	
	public void createArticleDepotActionPerformed(EventObject evt) {		
	}

	public void createFicheDepotActionPerformed(EventObject evt) {	
	}

	public void resetArticleActionPerformed(EventObject evt) {	
	}		

	public void createActionPerformed(EventObject evt) {
		YolaineCrudFrame frame;
		if (b == 0){
			frame = table.getYolaineModel().crudFrameFactory(null,
					CREER);
			frame.setSize(defaultWidth + 250, defaultHeight + 150);	
		}else if (b == 10)
		{
			frame = table.getYolaineModel().crudFrameFactory(null,
					CREER);  
			frame.setSize(defaultWidth + 250, defaultHeight + 150);	
		}else if ( b == 2){
			frame = table.getYolaineArticleModel().crudFrameFactory(null,
					CREER);  
		} else if (b == 3){
			frame = table.getYolaineMarqueModel().crudFrameFactory(null,
					CREER); 
			frame.setSize(defaultWidth, defaultHeight - 150);	
		} else if (b == 4){
			frame = table.getYolaineCategorieModel().crudFrameFactory(null,
					CREER); 
			frame.setSize(defaultWidth, defaultHeight - 150);	
		}
		else{
			frame = table.getYolaineModel().crudFrameFactory(null,
					CREER); 
			frame.setSize(defaultWidth, defaultHeight - 150);	
		}
		frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

			@Override
			public void createActionPerformed(EventObject evt) {
				table.getYolaineModel().initDataList();
			}

		});

		PrincipalFrame.getInstance().addAndShowFrame(frame);
	}


	public void createDepotActionPerformed(EventObject evt) {		
		showFrame(CREER_DEPOT);
	}


	public void createArticleActionPerformed(EventObject evt) {

		showFrame(CREER_ARTICLE);
	}	

	public void readActionPerformed(EventObject evt) {
		showFrame(LIRE);
	}

	public void readDepotActionPerformed(EventObject evt) {    	
		showFrame(LIRE_DEPOT);    	
	}    

	public void readArticleActionPerformed(EventObject evt) {    	
		showFrame(LIRE_ARTICLE);    	
	}

	public void updateActionPerformed(EventObject evt) {
		showFrame(MISE_A_JOUR);

	}

	public void updateDepotActionPerformed(EventObject evt) {
		showFrame(MISE_A_JOUR_DEPOT);

	}

	public void deleteActionPerformed(EventObject evt) {
		showFrame(SUPPRIMER);

	}


	public void resetActionPerformed(EventObject evt) {
	}

	public void closeActionPerformed(EventObject evt) {
		dispose();
	}


	protected void showFrame(YolaineViewType viewType) {
		int[] selectedRows = table.getSelectedRows();

		for (int selectedRowIndex : selectedRows) {        	

			if (viewType == CREER_DEPOT) {
				DepotCrudFrame frame;
				frame = table.getYolaineModel().crudDepotFrameFactory(
						selectedRowIndex, viewType);        	
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void createDepotActionPerformed(EventObject evt) {                    
						table.getYolaineDepotModel().initDataList();                     	
					}                     
				});
				frame.setSize(defaultWidth - 250 , defaultHeight - 150);
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			}

			if (viewType == CREER_ARTICLE) {
				ArticleCrudFrame frame;
				frame = table.getYolaineModel().crudArticleFrameFactory(
						selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void createArticleActionPerformed(EventObject evt) {

						table.getYolaineArticleModel().initDataList();

					}                     
				});
				frame.setSize(defaultWidth + 500 , defaultHeight + 150 );
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			}

			if (viewType == MISE_A_JOUR) {
				
				YolaineCrudFrame frame; 
				
				if(b == 0){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, MISE_A_JOUR);
					frame.setSize(defaultWidth + 250 , defaultHeight + 150);
				}else if (b == 10){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex,MISE_A_JOUR);
					frame.setSize(defaultWidth + 250, defaultHeight + 150);
				}else if (b == 2){
					frame = table.getYolaineArticleModel().crudFrameFactory(
							selectedRowIndex,MISE_A_JOUR);
					frame.setSize(defaultWidth + 250, defaultHeight + 80);
				}else if (b == 1){
					frame = table.getYolaineDepotModel().crudFrameFactory(
							selectedRowIndex,MISE_A_JOUR);
					 frame.setSize(defaultWidth + 150, defaultHeight - 150);					 
				}else if (b == 3){
					frame = table.getYolaineMarqueModel().crudFrameFactory(
							selectedRowIndex,MISE_A_JOUR);
					frame.setSize(defaultWidth, defaultHeight - 150);
				}else if (b == 4){
					frame = table.getYolaineCategorieModel().crudFrameFactory(
							selectedRowIndex,MISE_A_JOUR);
					 frame.setSize(defaultWidth, defaultHeight - 150);					 
				}else{
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex,MISE_A_JOUR);
					 frame.setSize(defaultWidth, defaultHeight - 150);
				}
				
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void updateActionPerformed(EventObject evt) {
						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else if (b == 3){
							table.getYolaineMarqueModel().initDataList();
						}else if (b == 4){
							table.getYolaineCategorieModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}
					}                    
				});
				
				PrincipalFrame.getInstance().addAndShowFrame(frame);
				//dispose();
			}

			if (viewType == MISE_A_JOUR_DEPOT) {
				YolaineCrudFrame frame;
				if(b == 0){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, MISE_A_JOUR_DEPOT);

				}else if (b == 1){            		
					frame = table.getYolaineDepotModel().crudFrameFactory(
							selectedRowIndex, MISE_A_JOUR_DEPOT);

				}else if (b == 2){
					frame = table.getYolaineArticleModel().crudFrameFactory(
							selectedRowIndex, MISE_A_JOUR_DEPOT);            		
				}else {
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, MISE_A_JOUR_DEPOT);            		
				}
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void updateDepotActionPerformed(EventObject evt) {
						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}
					}                    
				});
				dispose();
				frame.setSize(defaultWidth + 450, defaultHeight + 150);
				PrincipalFrame.getInstance().addAndShowFrame(frame);

			}

			if (viewType == SUPPRIMER) {
				YolaineCrudFrame frame;            	
				if(b == 0){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, SUPPRIMER);
					frame.setSize(defaultWidth + 250 , defaultHeight + 150);
				}else if (b == 10){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, SUPPRIMER);
					frame.setSize(defaultWidth + 250, defaultHeight + 150);
				}else if (b == 2){
					frame = table.getYolaineArticleModel().crudFrameFactory(
							selectedRowIndex, SUPPRIMER);
					frame.setSize(defaultWidth + 250, defaultHeight + 80);
				}else if (b == 1){
					frame = table.getYolaineDepotModel().crudFrameFactory(
							selectedRowIndex, SUPPRIMER);
					 frame.setSize(defaultWidth + 150, defaultHeight - 150);					 
				} else if (b == 3){
					frame = table.getYolaineMarqueModel().crudFrameFactory(selectedRowIndex, SUPPRIMER); 
					frame.setSize(defaultWidth, defaultHeight - 150);	
				} else if (b == 4){
					frame = table.getYolaineCategorieModel().crudFrameFactory(selectedRowIndex, SUPPRIMER); 
					frame.setSize(defaultWidth, defaultHeight - 150);	
				}			
				else{
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, SUPPRIMER);
					 frame.setSize(defaultWidth, defaultHeight - 150);
				}
				
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void deleteActionPerformed(EventObject evt) {
						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else if (b == 3){
							table.getYolaineMarqueModel().initDataList();
						}else if (b == 4){
							table.getYolaineCategorieModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}
					}                    
				});
				//dispose();
				
				PrincipalFrame.getInstance().addAndShowFrame(frame);               
			}          

			if (viewType == LIRE) {
				
				YolaineCrudFrame frame;            	
				if(b == 0){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, LIRE);
					frame.setSize(defaultWidth + 250 , defaultHeight + 150);
				}else if (b == 10){
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, LIRE);
					frame.setSize(defaultWidth + 250, defaultHeight + 150);
				}else if (b == 2){
					frame = table.getYolaineArticleModel().crudFrameFactory(
							selectedRowIndex, LIRE);
					frame.setSize(defaultWidth + 250, defaultHeight + 80);
				}else if (b == 1){
					frame = table.getYolaineDepotModel().crudFrameFactory(
							selectedRowIndex, LIRE);
					 frame.setSize(defaultWidth + 150, defaultHeight - 150);					 
				}else if (b == 3){
					frame = table.getYolaineMarqueModel().crudFrameFactory(selectedRowIndex, LIRE); 
					frame.setSize(defaultWidth, defaultHeight - 150);	
				} else if (b == 4){
					frame = table.getYolaineCategorieModel().crudFrameFactory(selectedRowIndex, LIRE); 
					frame.setSize(defaultWidth, defaultHeight - 150);	
				} else {
					frame = table.getYolaineModel().crudFrameFactory(
							selectedRowIndex, LIRE);
					 frame.setSize(defaultWidth, defaultHeight - 150);
				}
				
				
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void readActionPerformed(EventObject evt) {
						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else if (b == 3){
							table.getYolaineMarqueModel().initDataList();
						}else if (b == 4){
							table.getYolaineCategorieModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}
					}                    
				});
				
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			}          

			if (viewType == LIRE_DEPOT) {
				DepotCrudFrame frame;
				frame = table.getYolaineModel().crudDepotFrameFactory(
						selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void readActionPerformed(EventObject evt) {                    	
						table.getYolaineDepotModel().initDataList();                    	
					} 

				});
				frame.setSize(defaultWidth + 500 ,defaultHeight  );
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			}     

			if (viewType == LIRE_TYPEIDENTITE) {
				YolaineCrudFrame frame;
				frame = table.getYolaineModel().crudFrameFactory(
						selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void readActionPerformed(EventObject evt) {

						table.getYolaineModel().initDataList();

					}

				});
				frame.setSize(defaultWidth + 500 , defaultHeight + 150 );
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			}     

			if (viewType == LISTER_DEPOT) {
				DepotTableModel frame;
				frame = table.getYolaineModel().crudDepotTableFactory(
						selectedRowIndex, viewType);                
			}  

			if (viewType == LISTER_ARTICLE) {
				ArticleTableModel frame;
				if (b == 3){
				frame = table.getYolaineMarqueModel().crudArticleTableFactory(
						selectedRowIndex, viewType);
				}else if (b == 4){
					frame = table.getYolaineCategorieModel().crudArticleTableFactory(
							selectedRowIndex, viewType);
				} else{
					frame = table.getYolaineModel().crudArticleTableFactory(
							selectedRowIndex, viewType);
				}
			}            

			if (viewType == LISTER_ARTICLEDEPOT) {
				ArticleTableModel frame;
				frame = table.getYolaineDepotModel().crudArticleTableFactory(
						selectedRowIndex);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listArticleDepotActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}

					}

				});               
				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
				//dispose();
			} 

			if (viewType == LISTER_TYPEIDENTITE) {
				ArticleTableModel frame;
				frame = table.getYolaineDepotModel().crudArticleTableFactory(
						selectedRowIndex);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listArticleDepotActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}

					}

				});

				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 

			if (viewType == LISTER_CATEGORIE) {
				ArticleTableModel frame;
				frame = table.getYolaineDepotModel().crudArticleTableFactory(
						selectedRowIndex);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}

					}

				});

				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 

			if (viewType == LISTER_MARQUESCATEGORIE) {
				TypearticleTableModel frame;
				frame = table.getYolaineMarqueModel().crudTypearticleTableFactory(selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listCategorieMarqueActionPerformed(EventObject evt) {

						//if(b == 0){
						//	table.getYolaineModel().initDataList();
						//}else if (b == 1){
						//	table.getYolaineDepotModel().initDataList();
						//}else if (b == 4){
							table.getYolaineCategorieModel().initDataList();
						//}else{
						//	table.getYolaineModel().initDataList();
						//}

					}

				});
				
				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 
			
			if (viewType == LISTER_ARTICLESMARQUECATEGORIE) {
				ArticleTableModel frame;
				frame = table.getYolaineCategorieModel().crudArticleTableFactory(selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listMarqueCategorieActionPerformed(EventObject evt) {

						//if(b == 0){
						//	table.getYolaineModel().initDataList();
						//}else if (b == 1){
						//	table.getYolaineDepotModel().initDataList();
						//}else if (b == 4){
							table.getYolaineArticleModel().initDataList();
						//}else{
						//	table.getYolaineModel().initDataList();
						//}

					}

				});
				
				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 
			
			if (viewType == LISTER_CATEGORIESMARQUE) {
				MarqueTableModel frame;
				frame = table.getYolaineCategorieModel().crudMarqueTableFactory(selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listMarqueCategorieActionPerformed(EventObject evt) {

						//if(b == 0){
						//	table.getYolaineModel().initDataList();
						//}else if (b == 1){
						//	table.getYolaineDepotModel().initDataList();
						//}else if (b == 3){
							table.getYolaineMarqueModel().initDataList();
						//}else{
						//	table.getYolaineModel().initDataList();
						//}

					}

				});

				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 
			
			if (viewType == LISTER_ARTICLESCATEGORIEMARQUE) {
				ArticleTableModel frame;
				frame = table.getYolaineMarqueModel().crudArticleTableFactory(selectedRowIndex, viewType);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listCategorieMarqueActionPerformed(EventObject evt) {

						//if(b == 0){
						//	table.getYolaineModel().initDataList();
						//}else if (b == 1){
						//	table.getYolaineDepotModel().initDataList();
						//}else if (b == 3){
							table.getYolaineArticleModel().initDataList();
						//}else{
						//	table.getYolaineModel().initDataList();
						//}

					}

				});

				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 
			
			
			
			if (viewType == LISTER_MARQUE) {
				ArticleTableModel frame;
				frame = table.getYolaineDepotModel().crudArticleTableFactory(
						selectedRowIndex);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listArticleDepotActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}                    	
					}                    
				});

				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			}

			if (viewType == LISTER_COULEUR) {
				ArticleTableModel frame;
				frame = table.getYolaineDepotModel().crudArticleTableFactory(
						selectedRowIndex);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void listCouleurActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}                    	
					}                    
				});

				PrincipalFrame.getInstance().addAndShowFrame(new YolaineListFrame(frame));
			} 

			if (viewType == VENTE_ARTICLE) {
				YolaineCrudFrame frame;
				frame = table.getYolaineArticleModel().crudArticleFrameFactory(selectedRowIndex, VENTE_ARTICLE);
				
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void venteActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}                    	
					}                    
				});
				//dispose();

				frame.setSize(defaultWidth + 250, defaultHeight );
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			} 

			if (viewType == REMBOURSEMENT_ARTICLE) {
				YolaineCrudFrame frame;
				frame = table.getYolaineArticleModel().crudArticleFrameFactory(selectedRowIndex, REMBOURSEMENT_ARTICLE);
				frame.addYolaineCrudActionListener(new YolaineCrudActionAdapter() {

					@Override
					public void remboursementActionPerformed(EventObject evt) {

						if(b == 0){
							table.getYolaineModel().initDataList();
						}else if (b == 1){
							table.getYolaineDepotModel().initDataList();
						}else if (b == 2){
							table.getYolaineArticleModel().initDataList();
						}else{
							table.getYolaineModel().initDataList();
						}                    	
					}                    
				});
				//dispose();
				frame.setSize(defaultWidth + 250, defaultHeight);
				PrincipalFrame.getInstance().addAndShowFrame(frame);
			} 

		}
	}

}