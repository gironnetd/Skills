package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.catalogue.Categorie;
  
 
public class CategoryComboItem {
    
    private final Categorie category;    
    
    public CategoryComboItem(final Categorie category) {
        this.category = category;
    }    
    
    public Categorie getCategory() {
        return category;
    }    
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((category == null) ? 0 : category.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CategoryComboItem other = (CategoryComboItem) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        return true;
    }    
    
    @Override
    public String toString() {
        return category == null ? "" : category.getName();
    }

	
    
}