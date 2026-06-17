package com.yolaine.client.ui.util.combo;

 
import com.yolaine.entity.transaction.*;
 
public class TypePaiementComboItem {
    
    private final TypePaiement typepaiement;
    
    
    public TypePaiementComboItem(final TypePaiement typepaiement) {
        this.typepaiement = typepaiement;
    }       
    
	

	public TypePaiement getTypepaiement() {
		return typepaiement;
	}



	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((typepaiement == null) ? 0 : typepaiement.hashCode());
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
        final TypePaiementComboItem other = (TypePaiementComboItem) obj;
        if (typepaiement == null) {
            if (other.typepaiement != null)
                return false;
        } else if (!typepaiement.equals(other.typepaiement))
            return false;
        return true;
    }    
    
    @Override
    public String toString() {
        return typepaiement == null ? "" : typepaiement.getTypePaiement();
    }

	
	
    
}