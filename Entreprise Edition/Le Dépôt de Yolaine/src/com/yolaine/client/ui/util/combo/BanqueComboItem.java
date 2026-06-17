package com.yolaine.client.ui.util.combo;

 
import com.yolaine.entity.transaction.*;
 
public class BanqueComboItem {
    
    private final Banque banque;
    
    
    public BanqueComboItem(final Banque banque) {
        this.banque = banque;
    }       
    
	

	public Banque getBanque() {
		return banque;
	}



	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((banque == null) ? 0 : banque.hashCode());
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
        final BanqueComboItem other = (BanqueComboItem) obj;
        if (banque == null) {
            if (other.banque != null)
                return false;
        } else if (!banque.equals(other.banque))
            return false;
        return true;
    }    
    
    @Override
    public String toString() {
        return banque == null ? "" : banque.getBanque();
    }

	
	
    
}