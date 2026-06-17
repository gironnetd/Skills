package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.catalogue.Marque; 
 
public class MarqueComboItem {
    
    private final Marque marque;
    
    
    public MarqueComboItem(final Marque marque) {
        this.marque = marque;
    }    
    
    public Marque getMarque() {
		return marque;
	}

	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((marque == null) ? 0 :marque.hashCode());
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
        final MarqueComboItem other = (MarqueComboItem) obj;
        if (marque == null) {
            if (other.marque!= null)
                return false;
        } else if (!marque.equals(other.marque))
            return false;
        return true;
    }
    
    
    @Override
    public String toString() {
        return marque == null ? "" : marque.getName();
    }

	
    
}