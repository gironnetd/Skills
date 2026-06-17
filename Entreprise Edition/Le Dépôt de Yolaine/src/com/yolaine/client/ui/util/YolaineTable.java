package com.yolaine.client.ui.util;

import static com.yolaine.client.ui.util.YolaineUIConstants.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.depots.model.DepotTableModel;

public class YolaineTable extends JTable {
    
    private static final long serialVersionUID = 7626631528727042580L;
    
    
    public YolaineTable(YolaineTableModel model) {
        super(model);
        
        getTableHeader().setReorderingAllowed(false);
        
        for (int i = 0; i < model.getColumnRatios().length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnRatios()[i]);
            
            setPreferredScrollableViewportSize(new Dimension(600,70));
        }
    }
    
    public YolaineTable(DepotTableModel model) {
        super(model);
        
        getTableHeader().setReorderingAllowed(false);
        
        for (int i = 0; i < model.getColumnRatios().length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnRatios()[i]);
            
            setPreferredScrollableViewportSize(new Dimension(600,70));
        }
    }
    
    public YolaineTable(ArticleTableModel model) {
        super(model);
        
        getTableHeader().setReorderingAllowed(false);
        
        for (int i = 0; i < model.getColumnRatios().length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnRatios()[i]);
            
            setPreferredScrollableViewportSize(new Dimension(600,70));
        }
    }
    
    public YolaineTable(TypearticleTableModel model) {
        super(model);
        
        getTableHeader().setReorderingAllowed(false);
        
        for (int i = 0; i < model.getColumnRatios().length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnRatios()[i]);
            
            setPreferredScrollableViewportSize(new Dimension(600,70));
        }
    }
    
    public YolaineTable(MarqueTableModel model) {
        super(model);
        
        getTableHeader().setReorderingAllowed(false);
        
        for (int i = 0; i < model.getColumnRatios().length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnRatios()[i]);
            
            setPreferredScrollableViewportSize(new Dimension(600,70));
        }
    }
    
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row,
            int column) {
        Component innerTableCellRendererComponent = super.prepareRenderer(
                renderer, row, column);
        
        innerTableCellRendererComponent.setBackground(getCellBackground(row,
                column, isCellSelected(row, column), isRowSelected(row)));
        innerTableCellRendererComponent.setForeground(getCellForeground(row,
                column, isCellSelected(row, column), isRowSelected(row)));
        
        return innerTableCellRendererComponent;
    }
    
    
    protected Color getCellBackground(int row, int column, boolean isSelected,
            boolean hasFocus) {
        if (row == (row / 2) * 2) {
            return isSelected ? TABLE_ROW_SELECTED_COLOR_1 : TABLE_ROW_COLOR_1;
        }
        return isSelected ? TABLE_ROW_SELECTED_COLOR_2 : TABLE_ROW_COLOR_2;
    }
    
    protected Color getCellForeground(int row, int column, boolean isSelected,
            boolean hasFocus) {
        if (isSelected) {
            if (column == 0) {
                return Color.yellow;
            }
            return Color.white;
        }
        if (column == 0) {
            return Color.blue;
        }
        return Color.black;
    }    
    
    public YolaineTableModel getYolaineModel() {
        return (YolaineTableModel) getModel();
    }
    
    public DepotTableModel getYolaineDepotModel() {
        return (DepotTableModel) getModel();
    }
    
    public ArticleTableModel getYolaineArticleModel() {
        return (ArticleTableModel) getModel();
    }
    
    public TypearticleTableModel getYolaineCategorieModel() {
        return (TypearticleTableModel) getModel();
    }
    
    public MarqueTableModel getYolaineMarqueModel() {
        return (MarqueTableModel) getModel();
    }
    
}