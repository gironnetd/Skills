package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.client.Civilite;  
 
public class CiviliteComboItem {
    
    private final Civilite civilite;   

	public CiviliteComboItem(final Civilite civilite) {		
		this.civilite = civilite;
	}	
	
	public Civilite getCivilite() {
		return civilite;
	}

	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((civilite == null) ? 0 : civilite.hashCode());
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
        final CiviliteComboItem other = (CiviliteComboItem) obj;
        if (civilite == null) {
            if (other.civilite != null)
                return false;
        } else if (!civilite.equals(other.civilite))
            return false;
        return true;
    }
    
    
    @Override
    public String toString() {
        return civilite == null ? " " : civilite.getCivilite();
    }
    
}