package com.yolaine.client.ui.util.combo;


import com.yolaine.entity.catalogue.Marque;


public class ProductComboItem {
    
    private final Marque product;
    
    
    public ProductComboItem(final Marque product) {
        this.product = product;
    }
    
    
    public Marque getProduct() {
        return product;
    }
    
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((product == null) ? 0 : product.hashCode());
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
        final ProductComboItem other = (ProductComboItem) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }
    
    
    @Override
    public String toString() {
        return product == null ? "Loading error ..." : product.getName();
    }
    
}