package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.client.TypeIdentite;  
 
public class IdentiteComboItem {
    
    private final TypeIdentite typeidentite;
    
    
    public IdentiteComboItem(final TypeIdentite typeidentite) {
        this.typeidentite = typeidentite;
    }   
    
    
    public TypeIdentite getTypeidentite() {
		return typeidentite;
	}


	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((typeidentite == null) ? 0 : typeidentite.hashCode());
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
        final IdentiteComboItem other = (IdentiteComboItem) obj;
        if (typeidentite == null) {
            if (other.typeidentite != null)
                return false;
        } else if (!typeidentite.equals(other.typeidentite))
            return false;
        return true;
    }
    
    
    @Override
    public String toString() {
        return typeidentite == null ? " " : typeidentite.getTypeIdentite();
    }
    
}