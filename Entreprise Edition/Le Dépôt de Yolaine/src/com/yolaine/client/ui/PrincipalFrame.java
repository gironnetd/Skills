package com.yolaine.client.ui;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jxl.Workbook; 
import jxl.format.Border;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour; 
import jxl.format.VerticalAlignment;
import jxl.write.Label; 
import jxl.write.WritableCellFormat; 
import jxl.write.WritableFont; 
import jxl.write.WritableSheet; 
import jxl.write.WritableWorkbook; 
import jxl.write.WriteException; 
import jxl.write.biff.RowsExceededException; 


import com.yolaine.client.delegate.ClientDelegate;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.couleur.model.CouleurTableModel;
import com.yolaine.client.ui.articles.manche.model.MancheTableModel;
import com.yolaine.client.ui.articles.marque.MarqueCrudFrame;
import com.yolaine.client.ui.articles.marque.MarquePane;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.TypearticleCrudFrame;
import com.yolaine.client.ui.articles.typearticle.TypearticlePane;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.clients.client.ClientCrudFrame;
import com.yolaine.client.ui.clients.client.ClientPane;
import com.yolaine.client.ui.clients.client.model.ClientTableModel;
import com.yolaine.client.ui.clients.deposant.DeposantCrudFrame;
import com.yolaine.client.ui.clients.deposant.DeposantPane;
import com.yolaine.client.ui.clients.deposant.model.DeposantTableModel;
import com.yolaine.client.ui.clients.typeidentite.model.TypeidentiteTableModel;
import com.yolaine.client.ui.transaction.banque.model.BanqueTableModel;
import com.yolaine.client.ui.transaction.typepaiement.model.TypePaiementTableModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;
import com.yolaine.client.ui.util.YolaineFrame;
import com.yolaine.client.ui.util.YolaineListFrame;
import com.yolaine.client.ui.util.YolaineUIConstants;
import static com.yolaine.client.ui.util.YolaineViewType.CHERCHER_OU_CREER;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite; 

/**
 * This class represents the main user interface that displays a menu from which
 * the employee can do some actions on the system.
 */
public class PrincipalFrame extends YolaineFrame {
    
    private static final long serialVersionUID = -6750600430514708219L;


    private static final String BACKGROUND_IMAGE_NAME = "src/com/yolaine/client/ui/fond.jpg";
    
    private static PrincipalFrame instance;


    // ======================================
    // = Attributs =
    // ======================================
    private int defaultWidth;
    private int defaultHeight;
    
    private int width;
    private int height;
    
    private JMenuBar menuBar = new JMenuBar();
    
    private JMenu menuFile = new JMenu();
    private JMenuItem menuItemExit = new JMenuItem();
    
    private JMenu menuClient = new JMenu();
    private JMenuItem menuItemManageClient = new JMenuItem();
    private JMenuItem menuItemManageDeposant = new JMenuItem();
    private JMenuItem menuListClient = new JMenuItem();
    private JMenuItem menuListDeposant = new JMenuItem();   
    private JMenuItem menuListTypeidentite = new JMenuItem();
    private JMenuItem menuClientImpression = new JMenuItem();
    private JMenuItem menuDeposantImpression = new JMenuItem();
    
    private JMenu menuCatalog = new JMenu();
    private JMenuItem menuItemManageCategory = new JMenuItem();
    private JMenuItem menuItemManageProduct = new JMenuItem();   
    private JMenuItem menuListCategory = new JMenuItem();
    private JMenuItem menuListProduct = new JMenuItem();
    private JMenuItem menuListCouleur = new JMenuItem();
    private JMenuItem menuListManche = new JMenuItem();
    private JMenuItem menuListItem = new JMenuItem();
    
    private JMenu menuArticles = new JMenu();   
    private JMenuItem menuListArticlesDepose = new JMenuItem();
    private JMenuItem menuListArticlesVendu = new JMenuItem();
    private JMenuItem menuListArticlesRembourse = new JMenuItem();
    private JMenuItem menuListArticlesRendu = new JMenuItem();    
    
    private JMenu menuDepot = new JMenu();
    private JMenuItem menuListDepot = new JMenuItem();
    
    private JMenu menuVente = new JMenu();
    private JMenuItem menuListBanque = new JMenuItem();
    private JMenuItem menuListTypepaiement = new JMenuItem();    
    
    private JMenu menuLookAndFeel = new JMenu();
    private JMenuItem menuItemMetal = new JMenuItem();
    private JMenuItem menuItemMotif = new JMenuItem();
    private JMenuItem menuItemWindows = new JMenuItem();
    
    private JDesktopPane desktopPane = new JDesktopPane();
    
    // ======================================
    // = Constructeurs =
    // ======================================
    private PrincipalFrame() {
        defaultWidth = 640;
        defaultHeight = 480;
        
        width = defaultWidth;
        height = defaultHeight;
        
        initComponents();
        
        setTitle("Le Dépôt de Yolaine");
        setJMenuBar(menuBar);
        setSize(defaultWidth, defaultHeight);              
        
        setExtendedState(MAXIMIZED_BOTH);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuItemMetalActionPerformed();
    }
    
    // ======================================
    // = Methodes publiques =
    // ======================================
    public static void main(String[] args) {
        PrincipalFrame yolaineFrame = getInstance();
        yolaineFrame.setVisible(true);
    }
    
    public static PrincipalFrame getInstance() {
        if (instance == null) {
            instance = new PrincipalFrame();
        }
        
        return instance;
    }
    
    // ======================================
    // ======================================
    private void initComponents() {
        // Menu File
        menuFile.setText("Menu");
        menuFile.setMnemonic('c');
      
        menuItemExit.setText("Sortie");
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemExitActionPerformed();
            }
        });
        
        menuFile.add(menuItemExit);
        
        menuBar.add(menuFile);
        
        // Menu Customer
        menuClient.setText("Clients");
        //menuClient.setMnemonic('c');
        menuListClient.setText("Liste de tous les clients");
        menuListClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListClientActionPerformed();
            }
        });
        menuClient.add(menuListClient);
        
        menuListDeposant.setText("Liste de tous les déposants");
        menuListDeposant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListDeposantActionPerformed();
            }
        });
        menuClient.add(menuListDeposant);
        
        menuListTypeidentite.setText("Liste de tous les types d'identité");
        menuListTypeidentite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListTypeIdentiteActionPerformed();
            }
        });
        menuClient.add(menuListTypeidentite);
        
        menuClient.addSeparator();
        
        menuItemManageClient.setText("Gestion des clients");
        menuItemManageClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageClientActionPerformed();
            }
        });
        
        menuClient.add(menuItemManageClient);
        
        menuItemManageDeposant.setText("Gestion des déposants");
        menuItemManageDeposant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageDeposantActionPerformed();
            }
        });
        menuClient.add(menuItemManageDeposant);
        
        menuClient.addSeparator();
        
        menuClientImpression.setText("Impression de tous les clients");
        menuClientImpression.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuImpressionClientActionPerformed();
            }
        });
        
        menuClient.add(menuClientImpression);
        
        menuDeposantImpression.setText("Impression de tous les déposants");
        menuDeposantImpression.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuImpressionDeposantActionPerformed();
            }
        });
        
        menuClient.add(menuDeposantImpression);
        
        menuBar.add(menuClient);
        
     // Menu Catalog
        menuCatalog.setText("Catalogue");
       // menuCatalog.setMnemonic('c');
        menuListCategory.setText("Liste de tous les catégories d'articles");
        menuListCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListCategoryActionPerformed();
            }
        });
        menuCatalog.add(menuListCategory);
        
        menuListProduct.setText("Liste de tous les marques");
        menuListProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListProductActionPerformed();
            }
        });
        menuCatalog.add(menuListProduct);
        
        menuListCouleur.setText("Liste de tous les couleurs");
        menuListCouleur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListCouleurActionPerformed();
            }
        });
        menuCatalog.add(menuListCouleur);
        
        menuListManche.setText("Liste de tous les manches");
        menuListManche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListMancheActionPerformed();
            }
        });
        menuCatalog.add(menuListManche);
        
        menuListItem.setText("Liste de tous les articles");
        menuListItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListArticleActionPerformed();
            }
        });
        menuCatalog.add(menuListItem);
        
        menuCatalog.addSeparator();
        
        menuItemManageCategory.setText("Gestion des catégories d'articles");
        menuItemManageCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageCategoryActionPerformed();
            }
        });
        menuCatalog.add(menuItemManageCategory);
        
        menuItemManageProduct.setText("Gestion des marques");
        menuItemManageProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageProductActionPerformed();
            }
        });
        menuCatalog.add(menuItemManageProduct);       
        
        menuBar.add(menuCatalog);        
        
        menuArticles.setText("Articles");
        
        menuListArticlesDepose.setText("Liste de tous les articles déposés");
        menuListArticlesDepose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListArticleDeposeActionPerformed();
            }
        });
        
        menuArticles.add(menuListArticlesDepose);

        menuListArticlesVendu.setText("Liste de tous les articles vendus");
        menuListArticlesVendu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListArticleVenduActionPerformed();
            }
        });
        
        menuArticles.add(menuListArticlesVendu);
    
        menuListArticlesRembourse.setText("Liste de tous les articles remboursés");
        menuListArticlesRembourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListArticleRembourseActionPerformed();
            }
        });
        
        menuArticles.add(menuListArticlesRembourse);
    
        menuListArticlesRendu.setText("Liste de tous les articles rendus");
        menuListArticlesRendu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListArticleRenduActionPerformed();
            }
        });
        
        menuArticles.add(menuListArticlesRendu);
        
        menuBar.add(menuArticles);
        menuDepot.setText("Dépôts");
        //menuDepot.setMnemonic('c');
        menuListDepot.setText("Liste de tous les dépôts");
        menuListDepot.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		menuListDepotActionPerformed();
        	}
        });       
        
        menuDepot.add(menuListDepot);
        menuBar.add(menuDepot);

        menuVente.setText("Ventes");
        menuListTypepaiement.setText("Liste de tous les moyens de paiement");
        menuListTypepaiement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListTypepaiementActionPerformed();
            }
        });
        menuVente.add(menuListTypepaiement);
        
        menuListBanque.setText("Liste de toutes les banques");
        menuListBanque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListBanqueActionPerformed();
            }
        });
        
        menuVente.add(menuListBanque);
        
        
        menuBar.add(menuVente);      
       
        // Menu Look & Feel
        menuLookAndFeel.setText("Style");

        menuItemMetal.setText("Metal");
        menuItemMetal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemMetalActionPerformed();
            }
        });

        menuLookAndFeel.add(menuItemMetal);
        menuItemMotif.setText("Motif");
        menuItemMotif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemMotifActionPerformed();
            }
        });
        
        menuLookAndFeel.add(menuItemMotif);
        menuItemWindows.setText("Windows");
        menuItemWindows.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemWindowsActionPerformed();
            }
        });
        
        menuLookAndFeel.add(menuItemWindows);
        menuBar.add(menuLookAndFeel);
        setName("frameMenu");
        
        // Body of the frame
        getContentPane().add(desktopPane);
        
        desktopPane.setBackground(new Color(211,211,211));
        
        ImageIcon imgIcon = new ImageIcon(BACKGROUND_IMAGE_NAME);
        
        final JLabel backgroundLabel = new JLabel(imgIcon);
        backgroundLabel.setSize(defaultWidth, defaultHeight);
        desktopPane.add(backgroundLabel);
        
        desktopPane.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent arg0) {
                width = (int) desktopPane.getSize().getWidth();
                height = (int) desktopPane.getSize().getHeight();

                backgroundLabel.setSize(width, height);
            }

        });
        
    }
    
    // This method opens the Manage Customer frame
    private void menuItemManageClientActionPerformed() {
        ClientPane component = new ClientPane(CHERCHER_OU_CREER);
        ClientCrudFrame frame = new ClientCrudFrame(component);
        //frame.pack();
        frame.setSize(defaultWidth + 250, defaultHeight + 150);
        addAndShowFrame(frame);
    }
    
    // This method opens the Manage Customer frame
    private void menuItemManageDeposantActionPerformed() {
       DeposantPane component = new DeposantPane(CHERCHER_OU_CREER);
       DeposantCrudFrame frame = new DeposantCrudFrame(component);
       frame.setSize(defaultWidth + 250, defaultHeight + 150);      
      
       
        addAndShowFrame(frame);
    }
    
    // Clicking on the 'Motif' menu changes the look and feel of the application
    private void menuItemMotifActionPerformed() {
        final String mname = "menuItemMotifActionPerformed";
       
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.throwing(className, mname, e);
        }
    }
    
    // Clicking on the 'Metal' menu changes the look and feel of the application
    private void menuItemMetalActionPerformed() {
        final String mname = "menuItemMetalActionPerformed";
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.throwing(className, mname, e);
        }
    }
    
    // Clicking on the 'Windows' menu changes the look and feel of the
    // application
    private void menuItemWindowsActionPerformed() {
        final String mname = "menuItemWindowsActionPerformed";
        
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.throwing(className, mname, e);
        }
    }
       
    // This method opens the Manage Category frame
    private void menuItemManageCategoryActionPerformed() {
        TypearticlePane component = new TypearticlePane(CHERCHER_OU_CREER);
        TypearticleCrudFrame frame = new TypearticleCrudFrame(component);
        frame.setSize(defaultWidth - 150, defaultHeight - 150);             
        addAndShowFrame(frame);
    }   
    
    
    // This method opens the Manage Product frame
    private void menuItemManageProductActionPerformed() {
        MarquePane component = new MarquePane(CHERCHER_OU_CREER);
        //component.setSize(new Dimension(100,30));
        MarqueCrudFrame frame = new MarqueCrudFrame(component);        
        frame.setSize(defaultWidth -150, defaultHeight - 150);
        addAndShowFrame(frame);
    }
    
   
 // This method opens the Deposant List frame
    private void menuListDeposantActionPerformed() {
        final String actionName = "menuListDeposantActionPerformed";
        
        try {
        	YolaineListFrame frame = new YolaineListFrame(new DeposantTableModel());
        	frame.setSize(defaultWidth + 500, defaultHeight);
            addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
  
 // This method opens the Deposant List frame
    private void menuListTypeIdentiteActionPerformed() {
        final String actionName = "menuListDeposantActionPerformed";
        
        try {
            YolaineListFrame frame = new YolaineListFrame(new TypeidentiteTableModel());
            frame.setSize(defaultWidth, defaultHeight + 150);
            addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Customer List frame
    private void menuListClientActionPerformed() {
        final String actionName = "menuListClientActionPerformed";
        
        try {
            YolaineListFrame frame  = new YolaineListFrame(new ClientTableModel());
            frame.setSize(defaultWidth + 250, defaultHeight);
            addAndShowFrame(frame);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Category List frame
    private void menuListCategoryActionPerformed() {
        final String actionName = "menuListCategoryActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new TypearticleTableModel());
            // frame.setSize(defaultWidth + 150, defaultHeight + 150);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    // This method opens the Product List frame
    private void menuListProductActionPerformed() {
        final String actionName = "menuListProductActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new MarqueTableModel());
             //frame.setSize(defaultWidth + 150, defaultHeight + 150);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    // This method opens the Product List frame
    private void menuListCouleurActionPerformed() {
        final String actionName = "menuListCouleurActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new CouleurTableModel());
             frame.setSize(defaultWidth + 150, defaultHeight + 150);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    private void menuListTypepaiementActionPerformed() {
        final String actionName = "menuListTypePaiementActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new TypePaiementTableModel());
             frame.setSize(defaultWidth + 150, defaultHeight + 150);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    private void menuListBanqueActionPerformed() {
        final String actionName = "menuListBanqueActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new BanqueTableModel());
             frame.setSize(defaultWidth + 150, defaultHeight + 150);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Product List frame
    private void menuListMancheActionPerformed() {
        final String actionName = "menuListMancheActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new MancheTableModel());
             frame.setSize(defaultWidth + 150 , defaultHeight + 150);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    // This method opens the Item List frame
    private void menuListArticleDeposeActionPerformed() {
        final String actionName = "menuListItemActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new ArticleTableModel("déposé"),"déposé");
             frame.setSize(defaultWidth + 250, defaultHeight);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Item List frame
    private void menuListArticleVenduActionPerformed() {
        final String actionName = "menuListItemActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new ArticleTableModel("vendu"),"vendu");
             frame.setSize(defaultWidth + 250, defaultHeight);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Item List frame
    private void menuListArticleRembourseActionPerformed() {
        final String actionName = "menuListItemActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new ArticleTableModel("remboursé"),"remboursé");
             frame.setSize(defaultWidth + 250, defaultHeight );
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Item List frame
    private void menuListArticleActionPerformed() {
        final String actionName = "menuListItemActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new ArticleTableModel());
             frame.setSize(defaultWidth + 250, defaultHeight);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
 // This method opens the Item List frame
    private void menuListArticleRenduActionPerformed() {
        final String actionName = "menuListItemActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new ArticleTableModel("rendu"),"rendu");
             frame.setSize(defaultWidth + 250, defaultHeight);
             addAndShowFrame(frame);            
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
    // This method opens the Item List frame
    private void menuListDepotActionPerformed() {
        final String actionName = "menuListDepotActionPerformed";
        
        try {
        	 YolaineListFrame frame  = new YolaineListFrame(new DepotTableModel());
             frame.setSize(defaultWidth + 250, defaultHeight);
             addAndShowFrame(frame);
           } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }
    
   
    
    // This method exits the application
    private void menuItemExitActionPerformed() {
        dispose();
    }
    
    private void menuImpressionClientActionPerformed(){
    	try{
    	WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\le dépôt de Yolaine\\generated\\sortieclient.xls"));
    	WritableSheet sheet = workbook.createSheet("Premier classeur", 0); 
    	//Crée le format d’une cellule 
    	WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);    	
    	
    	WritableCellFormat arial10format = new WritableCellFormat(arial10font); 
    	 arial10format.setBorder(Border.ALL, BorderLineStyle.THIN);
    	 arial10format.setVerticalAlignment(VerticalAlignment.CENTRE);
    	 arial10format.setAlignment(Alignment.CENTRE);
    	 
    	 WritableCellFormat arialformat = new WritableCellFormat(arial10font); 
    	 arialformat.setBorder(Border.ALL, BorderLineStyle.THIN);
    	 arialformat.setVerticalAlignment(VerticalAlignment.CENTRE);
    	 arialformat.setAlignment(Alignment.CENTRE);
    	 //arial10format.setAlignment(Alignment.GENERAL);
    	 arial10format.setBackground(Colour.LIGHT_ORANGE);
    	 //Crée un label à la ligne 0, colonne 0 avec le format spécifique 
    	//Label label = new Label(0, 0, "Arial 10 point label",arial10format);     	
    	//Crée un label à la ligne 2, colonne 0 sans style prédéfini 
    	sheet.setColumnView(0, 10);
    	sheet.setColumnView(1, 20);
    	sheet.setColumnView(2, 20);
    	sheet.setColumnView(3, 10);
    	sheet.setColumnView(4, 35);
    	sheet.setColumnView(5, 20);
    	sheet.setColumnView(6, 10);
    	sheet.setColumnView(7, 20);
    	sheet.setColumnView(8, 15);
    	sheet.setColumnView(9, 15);
    	sheet.setColumnView(10, 35);
    	sheet.setColumnView(11, 20);
    	sheet.setColumnView(12, 20);
    	sheet.setColumnView(13, 30);
    	sheet.setColumnView(14, 20);
    	sheet.setColumnView(15, 20);
    	sheet.setColumnView(16, 20);
    	sheet.setColumnView(17, 20);
    	sheet.setColumnView(18, 20);
    	sheet.setColumnView(19, 20);
    	Label label = new Label(0, 0,"Civilité",arial10format);    	
    	Label label1 = new Label(1,0,"Nom",arial10format);
    	Label label2 = new Label(2,0,"Prénom",arial10format);
    	Label label3 = new Label(3,0,"Déposant",arial10format);
    	Label label4 = new Label(4,0,"Adresse 1",arial10format);
    	Label label5 = new Label(5,0,"Adresse 2",arial10format);
    	Label label6 = new Label(6,0,"Code postal",arial10format);
    	Label label7 = new Label(7,0,"Ville",arial10format);
    	Label label8 = new Label(8,0,"Téléphone fixe",arial10format);
    	Label label9 = new Label(9,0,"Téléphone portable",arial10format);
    	Label label10 = new Label(10,0,"email",arial10format);
    	Label label11 = new Label(11,0,"Date de naissance",arial10format);
    	Label label12 = new Label(12,0,"Type identité",arial10format);
    	Label label13 = new Label(13,0,"Numéro identité",arial10format);
    	Label label14 = new Label(14,0,"Valeur 1",arial10format);
    	Label label15 = new Label(15,0,"Valeur 2",arial10format);
    	Label label16 = new Label(16,0,"Montant déposé",arial10format);
    	Label label17 = new Label(17,0,"Montant dû",arial10format);
    	Label label18 = new Label(18,0,"Nom utilisateur",arial10format);    	
    	Label label19 = new Label(19,0,"Mot de passe",arial10format);
    	
    	
    	//Ajout des cellules 
    	sheet.addCell(label); 
    	sheet.addCell(label1);
    	sheet.addCell(label2); 
    	sheet.addCell(label3); 
    	sheet.addCell(label4); 
    	sheet.addCell(label5); 
    	sheet.addCell(label6); 
    	sheet.addCell(label7); 
    	sheet.addCell(label8); 
    	sheet.addCell(label9); 
    	sheet.addCell(label10); 
    	sheet.addCell(label11); 
    	sheet.addCell(label12); 
    	sheet.addCell(label13); 
    	sheet.addCell(label14); 
    	sheet.addCell(label15); 
    	sheet.addCell(label16); 
    	sheet.addCell(label17); 
    	sheet.addCell(label18); 
    	sheet.addCell(label19);
    	
    	
    	List<Client> listClient = ClientDelegate.trouverClients(false);
    	int i = 0;
    	
    	for(Client client : listClient){
    		i++;
    		if (client.getCivilite().getCivilite() == null){
    			Civilite civilite = new Civilite();
    			civilite.setCivilite("");
    			client.setCivilite(civilite);
    		}
    		
    		if(client.getPrenom()==null){
    			client.setPrenom("");
    		}
    		
    		if (client.getAdresse().getAdresse1()== null){
    			client.getAdresse().setAdresse1("");
    		}
    		
    		if (client.getAdresse().getAdresse2()== null){
    			client.getAdresse().setAdresse2("");
    		}
    		
    		if (client.getAdresse().getCodePostal()== null){
    			client.getAdresse().setCodePostal("");
    		}
    		
    		if (client.getAdresse().getVille()== null){
    			client.getAdresse().setVille("");
    		}
    		
    		if (client.getTelephonefixe() == null){
    			client.setTelephonefixe("");
    		}
    		
    		if (client.getTelephoneportable() == null){
    			client.setTelephoneportable("");
    		}
    		
    		if (client.getEmail() == null){
    			client.setEmail("");
    		}
    		
    		if(client.getDatenaissance() == null){
    			client.setDatenaissance("");
    		}
    		
    		if (client.getTypeidentite().getTypeIdentite()== null){
    			TypeIdentite typeidentite = new TypeIdentite();
    			typeidentite.setTypeIdentite("");
    			client.setTypeidentite(typeidentite);
    		}
    		
    		if (client.getNumeroidentite() == null){
    			client.setNumeroidentite("");
    		}
    		
    		if (client.getChampnumerique1() == null){
    			client.setChampnumerique1("");
    		}
    		
    		if (client.getChampnumerique2() == null){
    			client.setChampnumerique2("");
    		}
    		
    		if (client.getMontantdepose()== null){
    			client.setMontantdepose("0.0");
    		}
    		
    		if (client.getMontantdu()== null){
    			client.setMontantdu("0.0");
    		}
    		
    		if (client.getLogin() == null){
    			client.setLogin("");
    		}
    		
    		if (client.getPassword() == null){
    			client.setPassword("");
    		}
    		
    		Label labelc = new Label(0,i,"" + client.getCivilite().getCivilite() + "",arialformat);    		
    		Label labelc2 = new Label(1,i,"" + client.getNom() + "",arialformat);    		
    		Label labelc3 = new Label(2,i,"" + client.getPrenom() + "",arialformat);
    		Label labelc1 = new Label(3,i,"" + client.isDeposante() + "",arialformat);
    		Label labelc4 = new Label(4,i,"" + client.getAdresse().getAdresse1() + "",arialformat);
    		Label labelc5 = new Label(5,i,"" + client.getAdresse().getAdresse2() + "",arialformat);
    		Label labelc6 = new Label(6,i,"" + client.getAdresse().getCodePostal() + "",arialformat);
    		Label labelc7 = new Label(7,i,"" + client.getAdresse().getVille() + "",arialformat);
    		Label labelc8 = new Label(8,i,"" + client.getTelephonefixe() + "",arialformat);
    		Label labelc9 = new Label(9,i,"" + client.getTelephoneportable() + "",arialformat);
    		Label labelc10 = new Label(10,i,"" + client.getEmail() + "",arialformat);
    		Label labelc11 = new Label(11,i,"" + client.getDatenaissance() + "",arialformat);
    		Label labelc12 = new Label(12,i,"" + client.getTypeidentite().getTypeIdentite() + "",arialformat);
    		Label labelc13 = new Label(13,i,"" + client.getNumeroidentite() + "",arialformat);
    		Label labelc14 = new Label(14,i,"" + client.getChampnumerique1() + "",arialformat);
    		Label labelc15 = new Label(15,i,"" + client.getChampnumerique2() + "",arialformat);
    		Label labelc16 = new Label(16,i,"" + client.getMontantdepose() + "",arialformat);
    		Label labelc17 = new Label(17,i,"" + client.getMontantdu() + "",arialformat);
    		Label labelc18 = new Label(18,i,"" + client.getLogin() + "",arialformat);
    		Label labelc19 = new Label(19,i,"" + client.getPassword() + "",arialformat);    		
    	
    		sheet.addCell(labelc); 
        	sheet.addCell(labelc1);
        	sheet.addCell(labelc2); 
        	sheet.addCell(labelc3); 
        	sheet.addCell(labelc4); 
        	sheet.addCell(labelc5); 
        	sheet.addCell(labelc6); 
        	sheet.addCell(labelc7); 
        	sheet.addCell(labelc8); 
        	sheet.addCell(labelc9); 
        	sheet.addCell(labelc10); 
        	sheet.addCell(labelc11); 
        	sheet.addCell(labelc12); 
        	sheet.addCell(labelc13); 
        	sheet.addCell(labelc14); 
        	sheet.addCell(labelc15); 
        	sheet.addCell(labelc16); 
        	sheet.addCell(labelc17); 
        	sheet.addCell(labelc18); 
        	sheet.addCell(labelc19);
    	}
    	
    	
    	workbook.write();    	
    	workbook.close(); 
    	} catch (RowsExceededException e1) { 
    	e1.printStackTrace(); 
    	} catch (WriteException e1) { 
    	e1.printStackTrace(); 
    	} catch (IOException e) { 
    	e.printStackTrace(); 
    	}finally{ 
    		 JOptionPane.showMessageDialog(this,
                     "Le fichier est généré", "",
                     JOptionPane.WARNING_MESSAGE);
    	System.out.println("Le fichier \"sortieclient.xls\" à été généré correctement."); 
    	} 
    }
    
    private void menuImpressionDeposantActionPerformed(){
    	try{
    	WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\le dépôt de Yolaine\\generated\\sortiedeposant.xls")); 
    	WritableSheet sheet = workbook.createSheet("Premier classeur", 0); 
    	//Crée le format d’une cellule 
    	WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);    	
    	
    	WritableCellFormat arial10format = new WritableCellFormat(arial10font); 
    	 arial10format.setBorder(Border.ALL, BorderLineStyle.THIN);
    	 arial10format.setVerticalAlignment(VerticalAlignment.CENTRE);
    	 arial10format.setAlignment(Alignment.CENTRE);
    	 
    	 WritableCellFormat arialformat = new WritableCellFormat(arial10font); 
    	 arialformat.setBorder(Border.ALL, BorderLineStyle.THIN);
    	 arialformat.setVerticalAlignment(VerticalAlignment.CENTRE);
    	 arialformat.setAlignment(Alignment.CENTRE);
    	 //arial10format.setAlignment(Alignment.GENERAL);
    	 arial10format.setBackground(Colour.LIGHT_ORANGE);
    	 //Crée un label à la ligne 0, colonne 0 avec le format spécifique 
    	//Label label = new Label(0, 0, "Arial 10 point label",arial10format);     	
    	//Crée un label à la ligne 2, colonne 0 sans style prédéfini 
    	sheet.setColumnView(0, 10);
    	sheet.setColumnView(1, 20);
    	sheet.setColumnView(2, 20);
    	sheet.setColumnView(3, 10);
    	sheet.setColumnView(4, 35);
    	sheet.setColumnView(5, 20);
    	sheet.setColumnView(6, 10);
    	sheet.setColumnView(7, 20);
    	sheet.setColumnView(8, 15);
    	sheet.setColumnView(9, 15);
    	sheet.setColumnView(10, 35);
    	sheet.setColumnView(11, 20);
    	sheet.setColumnView(12, 20);
    	sheet.setColumnView(13, 30);
    	sheet.setColumnView(14, 20);
    	sheet.setColumnView(15, 20);
    	sheet.setColumnView(16, 20);
    	sheet.setColumnView(17, 20);
    	sheet.setColumnView(18, 20);
    	sheet.setColumnView(19, 20);
    	Label label = new Label(0, 0,"Civilité",arial10format);    	
    	Label label1 = new Label(1,0,"Nom",arial10format);
    	Label label2 = new Label(2,0,"Prénom",arial10format);
    	Label label3 = new Label(3,0,"Déposant",arial10format);
    	Label label4 = new Label(4,0,"Adresse 1",arial10format);
    	Label label5 = new Label(5,0,"Adresse 2",arial10format);
    	Label label6 = new Label(6,0,"Code postal",arial10format);
    	Label label7 = new Label(7,0,"Ville",arial10format);
    	Label label8 = new Label(8,0,"Téléphone fixe",arial10format);
    	Label label9 = new Label(9,0,"Téléphone portable",arial10format);
    	Label label10 = new Label(10,0,"email",arial10format);
    	Label label11 = new Label(11,0,"Date de naissance",arial10format);
    	Label label12 = new Label(12,0,"Type identité",arial10format);
    	Label label13 = new Label(13,0,"Numéro identité",arial10format);
    	Label label14 = new Label(14,0,"Valeur 1",arial10format);
    	Label label15 = new Label(15,0,"Valeur 2",arial10format);
    	Label label16 = new Label(16,0,"Montant déposé",arial10format);
    	Label label17 = new Label(17,0,"Montant dû",arial10format);
    	Label label18 = new Label(18,0,"Nom utilisateur",arial10format);    	
    	Label label19 = new Label(19,0,"Mot de passe",arial10format);
    	
    	
    	//Ajout des cellules 
    	sheet.addCell(label); 
    	sheet.addCell(label1);
    	sheet.addCell(label2); 
    	sheet.addCell(label3); 
    	sheet.addCell(label4); 
    	sheet.addCell(label5); 
    	sheet.addCell(label6); 
    	sheet.addCell(label7); 
    	sheet.addCell(label8); 
    	sheet.addCell(label9); 
    	sheet.addCell(label10); 
    	sheet.addCell(label11); 
    	sheet.addCell(label12); 
    	sheet.addCell(label13); 
    	sheet.addCell(label14); 
    	sheet.addCell(label15); 
    	sheet.addCell(label16); 
    	sheet.addCell(label17); 
    	sheet.addCell(label18); 
    	sheet.addCell(label19);
    	
    	
    	List<Client> listClient = ClientDelegate.trouverClients(true);
    	int i = 0;
    	
    	for(Client client : listClient){
    		i++;
    		if (client.getCivilite().getCivilite() == null){
    			Civilite civilite = new Civilite();
    			civilite.setCivilite("");
    			client.setCivilite(civilite);
    		}
    		
    		if(client.getPrenom()==null){
    			client.setPrenom("");
    		}
    		
    		if (client.getAdresse().getAdresse1()== null){
    			client.getAdresse().setAdresse1("");
    		}
    		
    		if (client.getAdresse().getAdresse2()== null){
    			client.getAdresse().setAdresse2("");
    		}
    		
    		if (client.getAdresse().getCodePostal()== null){
    			client.getAdresse().setCodePostal("");
    		}
    		
    		if (client.getAdresse().getVille()== null){
    			client.getAdresse().setVille("");
    		}
    		
    		if (client.getTelephonefixe() == null){
    			client.setTelephonefixe("");
    		}
    		
    		if (client.getTelephoneportable() == null){
    			client.setTelephoneportable("");
    		}
    		
    		if (client.getEmail() == null){
    			client.setEmail("");
    		}
    		
    		if(client.getDatenaissance() == null){
    			client.setDatenaissance("");
    		}
    		
    		if (client.getTypeidentite().getTypeIdentite()== null){
    			TypeIdentite typeidentite = new TypeIdentite();
    			typeidentite.setTypeIdentite("");
    			client.setTypeidentite(typeidentite);
    		}
    		
    		if (client.getNumeroidentite() == null){
    			client.setNumeroidentite("");
    		}
    		
    		if (client.getChampnumerique1() == null){
    			client.setChampnumerique1("");
    		}
    		
    		if (client.getChampnumerique2() == null){
    			client.setChampnumerique2("");
    		}
    		
    		if (client.getMontantdepose()== null){
    			client.setMontantdepose("0.0");
    		}
    		
    		if (client.getMontantdu()== null){
    			client.setMontantdu("0.0");
    		}
    		
    		if (client.getLogin() == null){
    			client.setLogin("");
    		}
    		
    		if (client.getPassword() == null){
    			client.setPassword("");
    		}
    		
    		Label labelc = new Label(0,i,"" + client.getCivilite().getCivilite() + "",arialformat);    		
    		Label labelc2 = new Label(1,i,"" + client.getNom() + "",arialformat);    		
    		Label labelc3 = new Label(2,i,"" + client.getPrenom() + "",arialformat);
    		Label labelc1 = new Label(3,i,"" + client.isDeposante() + "",arialformat);
    		Label labelc4 = new Label(4,i,"" + client.getAdresse().getAdresse1() + "",arialformat);
    		Label labelc5 = new Label(5,i,"" + client.getAdresse().getAdresse2() + "",arialformat);
    		Label labelc6 = new Label(6,i,"" + client.getAdresse().getCodePostal() + "",arialformat);
    		Label labelc7 = new Label(7,i,"" + client.getAdresse().getVille() + "",arialformat);
    		Label labelc8 = new Label(8,i,"" + client.getTelephonefixe() + "",arialformat);
    		Label labelc9 = new Label(9,i,"" + client.getTelephoneportable() + "",arialformat);
    		Label labelc10 = new Label(10,i,"" + client.getEmail() + "",arialformat);
    		Label labelc11 = new Label(11,i,"" + client.getDatenaissance() + "",arialformat);
    		Label labelc12 = new Label(12,i,"" + client.getTypeidentite().getTypeIdentite() + "",arialformat);
    		Label labelc13 = new Label(13,i,"" + client.getNumeroidentite() + "",arialformat);
    		Label labelc14 = new Label(14,i,"" + client.getChampnumerique1() + "",arialformat);
    		Label labelc15 = new Label(15,i,"" + client.getChampnumerique2() + "",arialformat);
    		Label labelc16 = new Label(16,i,"" + client.getMontantdepose() + "",arialformat);
    		Label labelc17 = new Label(17,i,"" + client.getMontantdu() + "",arialformat);
    		Label labelc18 = new Label(18,i,"" + client.getLogin() + "",arialformat);
    		Label labelc19 = new Label(19,i,"" + client.getPassword() + "",arialformat);    		
    	
    		sheet.addCell(labelc); 
        	sheet.addCell(labelc1);
        	sheet.addCell(labelc2); 
        	sheet.addCell(labelc3); 
        	sheet.addCell(labelc4); 
        	sheet.addCell(labelc5); 
        	sheet.addCell(labelc6); 
        	sheet.addCell(labelc7); 
        	sheet.addCell(labelc8); 
        	sheet.addCell(labelc9); 
        	sheet.addCell(labelc10); 
        	sheet.addCell(labelc11); 
        	sheet.addCell(labelc12); 
        	sheet.addCell(labelc13); 
        	sheet.addCell(labelc14); 
        	sheet.addCell(labelc15); 
        	sheet.addCell(labelc16); 
        	sheet.addCell(labelc17); 
        	sheet.addCell(labelc18); 
        	sheet.addCell(labelc19);
    	}
    	
    	
    	workbook.write();    	
    	workbook.close(); 
    	} catch (RowsExceededException e1) { 
    	e1.printStackTrace(); 
    	} catch (WriteException e1) { 
    	e1.printStackTrace(); 
    	} catch (IOException e) { 
    	e.printStackTrace(); 
    	}finally{ 
    		 JOptionPane.showMessageDialog(this,
                     "Le fichier est généré", "",
                     JOptionPane.WARNING_MESSAGE);
    	System.out.println("Le fichier \"sortiedeposant.xls\" à été généré correctement."); 
    	} 
    }
    
    
    public void addAndShowFrame(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setLocation((width - frame.getWidth()) / 2, (height - frame
                .getHeight()) / 2);        
        frame.setVisible(true);
    }
    
   
}