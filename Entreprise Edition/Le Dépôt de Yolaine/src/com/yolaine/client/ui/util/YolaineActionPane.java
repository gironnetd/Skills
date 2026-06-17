package com.yolaine.client.ui.util;


import static com.yolaine.client.ui.util.YolaineActionPane.YolaineActionPropertyName.*;
import static com.yolaine.client.ui.util.YolaineViewType.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import com.yolaine.client.ui.PrincipalFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineActionPane.YolaineActionPropertyName;
import com.yolaine.client.ui.util.event.YolaineCommonActionListener;
import com.yolaine.client.ui.util.event.YolaineCrudActionListener;
import com.yolaine.client.ui.util.event.YolaineFindActionListener;
import com.yolaine.entity.client.Client;


public class YolaineActionPane extends JPanel implements YolaineActionModel {

	private static final long serialVersionUID = -4950626416337689544L;

	protected static enum YolaineActionPropertyName {

		FIND_ACTION,
		LIST_ACTION,
		LIST_DEPOT_ACTION,
		LIST_ARTICLE_ACTION,
		LIST_ARTICLE_DEPOT_ACTION,
		LIST_CATEGORIES_MARQUE_ACTION,
		LIST_MARQUES_CATEGORIE_ACTION,
		LIST_ARTICLES_MARQUE_CATEGORIE_ACTION,
		LIST_ARTICLES_CATEGORIE_MARQUE_ACTION,
		READ_ACTION,
		READ_DEPOT_ACTION,
		READ_ARTICLE_ACTION,
		CREATE_ACTION,
		CREATE_DEPOT_ACTION,
		CREATE_ARTICLE_ACTION,
		CREATE_ARTICLE_DEPOT_ACTION,
		CREATE_FICHE_DEPOT_ACTION,
		UPDATE_ACTION,
		UPDATE_DEPOT_ACTION,
		DELETE_ACTION,
		RESET_ACTION,
		RESET_ARTICLE_ACTION,
		SALE_ACTION,
		REMBOURSEMENT_ACTION,
		CLOSE_ACTION        
	}    

	private final EventListenerList listenerList = new EventListenerList();
	private YolaineViewType viewType;

	private JButton findBt;
	private JButton createBt;
	private JButton createdeposantBt;
	private JButton createdepotBt;	
	private JButton listarticledepotBt;
	private JButton listCategoryMarqueBt;
	private JButton listMarqueCategorieBt;
	private JButton listArticlesCategoryMarqueBt;
	private JButton listArticlesMarqueCategorieBt;
	private JButton createarticleBt;
	private JButton createarticledepotBt;
	private JButton createfichedepotBt;
	private JButton readBt;
	private JButton updateBt;
	private JButton updatedepotBt;
	private JButton deleteBt;
	private JButton listarticleBt;
	private JButton listdepotBt;
	private JButton readarticleBt;
	private JButton readdepotBt;
	private JButton remboursementBt;
	private JButton resetBt;
	private JButton resetarticleBt;
	private JButton venteBt;
	private JButton closeBt;

	public YolaineActionPane(YolaineViewType viewType) {
		initComponent();

		this.viewType = viewType;
		initViewType();
	}

	public YolaineActionPane() {
		this(LIRE);
	}

	private void initComponent() {
		findBt = new JButton("Chercher");
		createBt = new JButton("Créer");
		createdeposantBt = new JButton("Créer un déposant");
		createdepotBt = new JButton("Créer un dépôt pour");		
		createarticleBt = new JButton("Ajouter un article");
		createarticledepotBt = new JButton("Ajouter un article au dépôt ");
		createfichedepotBt = new JButton("Imprimer une fiche pour ce dépôt");
		readBt = new JButton("Afficher");
		updateBt = new JButton("Mettre à jour");
		updatedepotBt = new JButton("Mettre à jour");
		listCategoryMarqueBt = new JButton("Lister les catégories de ");
		listMarqueCategorieBt = new JButton("Lister les marques de ");
		listArticlesCategoryMarqueBt = new JButton("Liste des articles de ");
		listArticlesMarqueCategorieBt = new JButton("Liste des articles de ");
		listarticledepotBt = new JButton("Liste des articles du depot");
		listarticleBt = new JButton("Liste des articles de ");
		listdepotBt = new JButton("Liste des dépôts de");
		readdepotBt = new JButton("Lire un dépôt");
		readarticleBt = new JButton("Lire un article");
		deleteBt = new JButton("Supprimer");
		resetBt = new JButton("Effacer");
		resetarticleBt = new JButton("Effacer l'article");
		venteBt = new JButton("Vente de l'article");
		closeBt = new JButton("Fermer");
		remboursementBt = new JButton("Rembourser l'article");

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());
		setOpaque(false);

		JPanel leftPane = new JPanel();
		JPanel centerPane = new JPanel();
		JPanel rightPane = new JPanel();

		leftPane.setOpaque(false);
		leftPane.add(findBt);
		add(leftPane, BorderLayout.WEST);

		centerPane.setOpaque(false);
		centerPane.add(createBt);
		centerPane.add(createdeposantBt);
		centerPane.add(createdepotBt);		
		centerPane.add(createarticleBt);
		centerPane.add(createarticledepotBt);		
		centerPane.add(readBt);
		centerPane.add(listCategoryMarqueBt);
		centerPane.add(listMarqueCategorieBt);
		centerPane.add(listArticlesCategoryMarqueBt);
		centerPane.add(listArticlesMarqueCategorieBt);
		centerPane.add(listarticledepotBt);
		centerPane.add(createfichedepotBt);
		centerPane.add(listarticleBt);
		centerPane.add(listdepotBt);
		centerPane.add(updateBt);
		centerPane.add(updatedepotBt);
		centerPane.add(readdepotBt);
		centerPane.add(readarticleBt);
		centerPane.add(venteBt);
		centerPane.add(remboursementBt);
		centerPane.add(deleteBt);
		add(centerPane, BorderLayout.CENTER);

		rightPane.setOpaque(false);

		rightPane.add(resetarticleBt);
		rightPane.add(resetBt);
		rightPane.add(closeBt);
		add(rightPane, BorderLayout.EAST);		

		findBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(FIND_ACTION);
			}
		});

		createBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CREATE_ACTION);
			}
		});

		createdeposantBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CREATE_ACTION);
			}
		});

		createarticledepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CREATE_ARTICLE_DEPOT_ACTION);
			}
		});

		createdepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CREATE_DEPOT_ACTION);
			}
		});

		createfichedepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CREATE_FICHE_DEPOT_ACTION);
			}
		});

		listarticledepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(LIST_ARTICLE_DEPOT_ACTION);
			}
		});

		listCategoryMarqueBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(LIST_CATEGORIES_MARQUE_ACTION);
			}
		});

		listMarqueCategorieBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(LIST_MARQUES_CATEGORIE_ACTION);
			}
		});

		listArticlesCategoryMarqueBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(LIST_ARTICLES_CATEGORIE_MARQUE_ACTION);
			}
		});

		listArticlesMarqueCategorieBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(LIST_ARTICLES_MARQUE_CATEGORIE_ACTION);
			}
		});
		
		createarticleBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CREATE_ARTICLE_ACTION);
			}
		});

		readBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(READ_ACTION);
			}
		});

		updateBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(UPDATE_ACTION);
			}
		});

		updatedepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(UPDATE_DEPOT_ACTION);
			}
		});

		listdepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {				
				fireActionPerformed(LIST_DEPOT_ACTION);
			}
		});

		listarticleBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {			
				fireActionPerformed(LIST_ARTICLE_ACTION);
			}
		});

		readdepotBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {				
				fireActionPerformed(READ_DEPOT_ACTION);
			}
		});

		readarticleBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {					
				fireActionPerformed(READ_ARTICLE_ACTION);
			}
		});

		deleteBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(DELETE_ACTION);
			}
		});

		resetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(RESET_ACTION);
			}
		});

		resetarticleBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(RESET_ARTICLE_ACTION);
			}
		});

		venteBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(SALE_ACTION);
			}
		});


		closeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(CLOSE_ACTION);
			}
		});

		remboursementBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fireActionPerformed(REMBOURSEMENT_ACTION);
			}
		});
	}


	public void addYolaineCrudActionListener(YolaineCrudActionListener listener) {
		listenerList.add(YolaineCrudActionListener.class, listener);
	}

	public void addYolaineFindActionListener(YolaineFindActionListener listener) {
		listenerList.add(YolaineFindActionListener.class, listener);
	}

	public void addYolaineCommonActionListener(YolaineCommonActionListener listener) {
		listenerList.add(YolaineCommonActionListener.class, listener);
	}

	public void removeYolaineCrudActionListener(YolaineCrudActionListener listener) {
		listenerList.remove(YolaineCrudActionListener.class, listener);
	}

	public void removeYolaineFindActionListener(YolaineFindActionListener listener) {
		listenerList.remove(YolaineFindActionListener.class, listener);
	}

	public void removeYolaineCommonActionListener(YolaineCommonActionListener listener) {
		listenerList.remove(YolaineCommonActionListener.class, listener);
	}

	public YolaineCrudActionListener[] getYolaineCrudActionListeners() {
		return listenerList.getListeners(YolaineCrudActionListener.class);
	}

	public YolaineFindActionListener[] getYolaineFindActionListeners() {
		return listenerList.getListeners(YolaineFindActionListener.class);
	}

	public YolaineCommonActionListener[] getYolaineCommonActionListeners() {
		return listenerList.getListeners(YolaineCommonActionListener.class);
	}

	private void fireActionPerformed(YolaineActionPropertyName actionPropertyName) {
		Object[] listeners = listenerList.getListenerList();
		EventObject evt = null;

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (evt == null) {
				evt = new EventObject(this);
			}

			//if (listeners[i] == YolaineCrudActionListener.class) {
				if (actionPropertyName == YolaineActionPropertyName.CREATE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.createActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.CREATE_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.createDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.CREATE_FICHE_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.createFicheDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.CREATE_ARTICLE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.createArticleActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.CREATE_ARTICLE_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.createArticleDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.READ_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.readActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.READ_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.readDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.READ_ARTICLE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.readArticleActionPerformed(evt);
				}  else if (actionPropertyName == YolaineActionPropertyName.LIST_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listActionPerformed(evt);					
				}  else if (actionPropertyName == YolaineActionPropertyName.LIST_CATEGORIES_MARQUE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listCategorieMarqueActionPerformed(evt);
				}  else if (actionPropertyName == YolaineActionPropertyName.LIST_MARQUES_CATEGORIE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listMarqueCategorieActionPerformed(evt);
				}  else if (actionPropertyName == YolaineActionPropertyName.LIST_ARTICLES_CATEGORIE_MARQUE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listArticlesCategorieMarqueActionPerformed(evt);
				}  else if (actionPropertyName == YolaineActionPropertyName.LIST_ARTICLES_MARQUE_CATEGORIE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listArticlesMarqueCategorieActionPerformed(evt);
				}  else if (actionPropertyName == YolaineActionPropertyName.LIST_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.LIST_ARTICLE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listArticleActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.LIST_ARTICLE_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.listArticleDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.UPDATE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.updateActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.UPDATE_DEPOT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.updateDepotActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.DELETE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.deleteActionPerformed(evt);				
				} else if (actionPropertyName == YolaineActionPropertyName.SALE_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.venteActionPerformed(evt);				
				} else if (actionPropertyName == YolaineActionPropertyName.REMBOURSEMENT_ACTION) {
					((YolaineCrudActionListener) listeners[i + 1])
					.remboursementActionPerformed(evt);				
				} 
			//}
			else // if (listeners[i] == YolaineFindActionListener.class) {
				if (actionPropertyName == YolaineActionPropertyName.FIND_ACTION) {
					((YolaineFindActionListener) listeners[i + 1])
					.findActionPerformed(evt);
				// }
			} else // if (listeners[i] == YolaineCommonActionListener.class)
				{
				if (actionPropertyName == YolaineActionPropertyName.RESET_ACTION) {
					((YolaineCommonActionListener) listeners[i + 1])
					.resetActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.CLOSE_ACTION) {
					((YolaineCommonActionListener) listeners[i + 1])
					.closeActionPerformed(evt);
				} else if (actionPropertyName == YolaineActionPropertyName.RESET_ARTICLE_ACTION) {
					((YolaineCommonActionListener) listeners[i + 1])
					.resetArticleActionPerformed(evt);
				}
			}
		}
	}

	public YolaineViewType getViewType() {
		return viewType;
	}

	public void setViewType(YolaineViewType viewType) {
		this.viewType = viewType;

		initViewType();
	}

	private void initViewType() {
		if (viewType == LISTER) {
			readBt.setVisible(true);
			createBt.setVisible(false);	
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdepotBt.setVisible(true);
			createdeposantBt.setVisible(true);			
			createfichedepotBt.setVisible(false);
			resetBt.setVisible(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			venteBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
			return;
		}

		readBt.setVisible(false);
		closeBt.setEnabled(true);
		readdepotBt.setVisible(false);			

		if (viewType == CHERCHER) {
			findBt.setEnabled(true);
			createBt.setVisible(false);
			createdepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);
			readdepotBt.setVisible(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			deleteBt.setVisible(false);
			venteBt.setVisible(false);
			resetBt.setEnabled(true);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == CREER) {
			findBt.setVisible(false);
			findBt.setEnabled(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			createBt.setEnabled(true);
			createdepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			listarticleBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			readBt.setVisible(false);			
			readarticleBt.setVisible(false);
			readdepotBt.setVisible(false);			
			updateBt.setEnabled(false);			
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			venteBt.setVisible(false);
			resetBt.setEnabled(true);
			remboursementBt.setVisible(false);
			resetarticleBt.setVisible(false);
		} else if (viewType == CREER_DEPOT) {
			findBt.setVisible(false);			
			createBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createdepotBt.setVisible(false);			
			createarticleBt.setVisible(true);
			createarticleBt.setEnabled(true);
			createarticledepotBt.setVisible(false);
			createfichedepotBt.setVisible(true);
			listarticledepotBt.setVisible(true);
			listarticledepotBt.setEnabled(true);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			readdepotBt.setVisible(false);
			readBt.setEnabled(true);
			updateBt.setVisible(false);				
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setVisible(false);			
			resetBt.setVisible(false);
			resetarticleBt.setVisible(true);
			resetarticleBt.setEnabled(true);
			remboursementBt.setVisible(false);
		} else if (viewType == CREER_ARTICLE) {
			findBt.setEnabled(false);			
			createdeposantBt.setEnabled(false);
			createarticleBt.setVisible(true);
			createarticleBt.setEnabled(true);
			createfichedepotBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setEnabled(true);
			updateBt.setEnabled(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(true);
			deleteBt.setEnabled(false);
			venteBt.setVisible(false);
			resetBt.setEnabled(true);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
		}   else if (viewType == CHERCHER_OU_CREER) {
			findBt.setEnabled(true);
			listarticledepotBt.setVisible(false);				
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createarticleBt.setVisible(false);
			createdepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setEnabled(true);						
			readarticleBt.setVisible(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setEnabled(true);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
			
		} else if (viewType == LIRE) {
			findBt.setVisible(false);
			createBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);			
			readBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listarticledepotBt.setVisible(false);	
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			updateBt.setVisible(false);			
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			readarticleBt.setEnabled(true);
			venteBt.setVisible(false);
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LIRE_DEPOT) {
			findBt.setVisible(false);
			createBt.setVisible(false);			
			createdeposantBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(false);			
			updateBt.setVisible(false);			
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(true);
			readarticleBt.setEnabled(false);
			venteBt.setVisible(false);
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			resetBt.setVisible(false);
			remboursementBt.setVisible(false);
			resetarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
		} else if (viewType == LIRE_TYPEIDENTITE) {
			findBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			readBt.setVisible(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
		}  else if (viewType == LIRE_ARTICLE) {
			findBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdeposantBt.setVisible(false);			
			createfichedepotBt.setVisible(false);
			readBt.setVisible(false);			
			updateBt.setVisible(false);			
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
		}   else if (viewType == MISE_A_JOUR) {
			findBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			createBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);			
			updateBt.setVisible(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			venteBt.setVisible(false);				
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
		}   else if (viewType == MISE_A_JOUR_DEPOT) {
			findBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(true);
			createfichedepotBt.setVisible(true);
			createdepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);			
			updateBt.setVisible(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			venteBt.setVisible(false);				
			deleteBt.setVisible(false);
			deleteBt.setEnabled(false);
			resetBt.setVisible(false);
			resetarticleBt.setVisible(true);
			remboursementBt.setVisible(false);
		}  else if (viewType == VENTE_ARTICLE) {
			findBt.setVisible(false);
			listarticleBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);	
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			venteBt.setVisible(true);
			venteBt.setEnabled(true);
			deleteBt.setVisible(false);				
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}   else if (viewType == REMBOURSEMENT_ARTICLE) {
			findBt.setVisible(false);
			listarticleBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);	
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			venteBt.setVisible(false);				
			deleteBt.setVisible(false);				
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(true);
		}   else if (viewType == SUPPRIMER) {
			findBt.setVisible(false);
			listarticleBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);
			updateBt.setVisible(false);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			deleteBt.setVisible(true);	
			deleteBt.setEnabled(true);
			venteBt.setVisible(false);
			venteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == MISE_A_JOUR_OU_SUPPRIMER) {
			findBt.setVisible(true);
			findBt.setEnabled(true);
			createBt.setVisible(false);
			createdepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			createdeposantBt.setVisible(false);			
			createfichedepotBt.setVisible(false);
			readBt.setVisible(false);
			readarticleBt.setVisible(false);
			readdepotBt.setVisible(false);
			updateBt.setVisible(true);
			updateBt.setEnabled(true);			
			updatedepotBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setVisible(true);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			remboursementBt.setVisible(false);
			resetarticleBt.setVisible(false);
		} else if (viewType == LISTER_ARTICLE) {
			findBt.setVisible(false);
			readBt.setVisible(true);
			createBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(true);
			venteBt.setEnabled(true);				
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(true);
		} else if (viewType == LISTER_ARTICLEVENDU) {
			findBt.setVisible(false);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			venteBt.setEnabled(true);				
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(true);
		}else if (viewType == LISTER_ARTICLEREMBOURSE) {
			findBt.setVisible(false);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			venteBt.setEnabled(true);				
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}else if (viewType == LISTER_ARTICLERENDU) {
			findBt.setVisible(false);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			venteBt.setEnabled(true);				
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LISTER_TYPEPAIEMENT) {
			findBt.setVisible(true);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);				
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}  else if (viewType == LISTER_BANQUE) {
			findBt.setVisible(true);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);				
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}   else if (viewType == LISTER_COULEUR) {
			findBt.setVisible(true);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}   else if (viewType == LISTER_MANCHE) {
			findBt.setVisible(true);
			readBt.setVisible(true);			
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(false);			
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LISTER_DEPOT) {
			findBt.setVisible(false);
			readBt.setVisible(false);
			createBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			listdepotBt.setVisible(false);
			listarticledepotBt.setVisible(true);
			listarticledepotBt.setEnabled(true);
			listarticleBt.setVisible(false);	
			listarticleBt.setEnabled(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdeposantBt.setVisible(false);						
			createarticleBt.setVisible(false);			
			updateBt.setVisible(false);
			readdepotBt.setVisible(false);
			readdepotBt.setEnabled(false);
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LISTER_TYPEIDENTITE) {
			findBt.setEnabled(true);
			listarticledepotBt.setVisible(false);
			listarticledepotBt.setEnabled(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(true);
			readBt.setEnabled(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readarticleBt.setEnabled(true);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);				
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LISTER_MARQUE) {
			findBt.setEnabled(true);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listCategoryMarqueBt.setVisible(true);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(true);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(true);
			readBt.setEnabled(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readarticleBt.setEnabled(true);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LISTER_ARTICLESMARQUECATEGORIE) {
			findBt.setEnabled(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listarticledepotBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(true);
			listArticlesMarqueCategorieBt.setVisible(false);
			listCategoryMarqueBt.setVisible(true);
			listMarqueCategorieBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(true);
			readBt.setEnabled(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readarticleBt.setEnabled(true);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}  else if (viewType == LISTER_CATEGORIE) {
			findBt.setEnabled(true);
			listarticledepotBt.setVisible(false);
			listarticledepotBt.setEnabled(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(true);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(true);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(true);
			readBt.setEnabled(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readarticleBt.setEnabled(true);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} else if (viewType == LISTER_ARTICLESCATEGORIEMARQUE) {
			findBt.setEnabled(false);
			listarticledepotBt.setVisible(false);
			listarticledepotBt.setEnabled(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(true);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(true);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(true);
			readBt.setEnabled(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readarticleBt.setEnabled(true);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		}  else if (viewType == LISTER_CLIENT) {
			findBt.setEnabled(true);
			listarticledepotBt.setVisible(false);
			listarticledepotBt.setEnabled(false);
			listdepotBt.setVisible(false);
			listarticleBt.setVisible(false);
			listCategoryMarqueBt.setVisible(false);
			listMarqueCategorieBt.setVisible(false);
			listArticlesCategoryMarqueBt.setVisible(false);
			listArticlesMarqueCategorieBt.setVisible(false);
			createdepotBt.setVisible(false);
			createfichedepotBt.setVisible(false);
			createdeposantBt.setVisible(false);
			createarticleBt.setVisible(false);
			createarticledepotBt.setVisible(false);
			readBt.setVisible(true);
			readBt.setEnabled(true);
			updateBt.setEnabled(true);
			updatedepotBt.setVisible(false);
			readarticleBt.setEnabled(true);
			readdepotBt.setVisible(false);			
			readarticleBt.setVisible(false);
			venteBt.setVisible(false);
			deleteBt.setEnabled(true);
			resetBt.setVisible(false);
			resetBt.setEnabled(false);
			resetarticleBt.setVisible(false);
			remboursementBt.setVisible(false);
		} 
	}

}