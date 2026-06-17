package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.catalogue.Manche;  
 
public class MancheComboItem {
    
    private final Manche manche;
    
    
    public MancheComboItem(final Manche manche) {
        this.manche = manche;
    }    
    
    public Manche getManche() {
		return manche;
	}

	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((manche == null) ? 0 : manche.hashCode());
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
        final MancheComboItem other = (MancheComboItem) obj;
        if (manche == null) {
            if (other.manche != null)
                return false;
        } else if (!manche.equals(other.manche))
            return false;
        return true;
    }
    
    
    @Override
    public String toString() {
        return manche == null ? "" : manche.getManche();
    }


	
    
}