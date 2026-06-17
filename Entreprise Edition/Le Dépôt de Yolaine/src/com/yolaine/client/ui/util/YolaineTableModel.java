package com.yolaine.client.ui.util;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.yolaine.client.ui.articles.article.ArticleCrudFrame;
import com.yolaine.client.ui.articles.article.model.ArticleTableModel;
import com.yolaine.client.ui.articles.marque.model.MarqueTableModel;
import com.yolaine.client.ui.articles.typearticle.model.TypearticleTableModel;
import com.yolaine.client.ui.depots.DepotCrudFrame;
import com.yolaine.client.ui.depots.model.DepotTableModel;


public abstract class YolaineTableModel<E> extends AbstractTableModel {
    
	public E element;
    protected List<E> dataList;    
    protected String[] columnNames;
    protected Class[] columnClasses;
    protected int[] columnRatios;
    
    
    public YolaineTableModel() {
        buildColumnProperties();
        initDataList();
    }    
    
    public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	private void buildColumnProperties() {
        Object[][] columnProperties = getColumnProperties();
        
        columnNames = new String[columnProperties.length];
        columnClasses = new Class[columnProperties.length];
        columnRatios = new int[columnProperties.length];
        
        for (int i = 0; i < columnProperties.length; i++) {
            columnNames[i] = (String) columnProperties[i][0];
            columnClasses[i] = (Class) columnProperties[i][1];
            columnRatios[i] = (Integer) columnProperties[i][2];
        }
    }
    
    protected abstract Object[][] getColumnProperties();
    
    
    public void initDataList() {
        this.dataList = nonNullList(buildDataList(element));
        
        fireTableStructureChanged();
    }
    
    protected abstract List<E> buildDataList(E element);
    
    public void add(E element) {
        dataList.add(element);
        fireTableStructureChanged();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount() {
        return dataList.size();
    } 

	private static <E> List<E> nonNullList(List<E> list) {
        return (list != null) ? list : new ArrayList<E>();
    }    
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }    
    public int[] getColumnRatios() {
        return columnRatios;
    }    
    
    public abstract String getDefaultTitle();
    
    public abstract YolaineTableModel crudTableFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract ArticleTableModel crudArticleTableFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract TypearticleTableModel crudTypearticleTableFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract MarqueTableModel crudMarqueTableFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract DepotTableModel crudDepotTableFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract YolaineCrudFrame crudFrameFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract ArticleCrudFrame crudArticleFrameFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    public abstract DepotCrudFrame crudDepotFrameFactory(Integer selectedRowIndex,
            YolaineViewType viewType);
    
    
}