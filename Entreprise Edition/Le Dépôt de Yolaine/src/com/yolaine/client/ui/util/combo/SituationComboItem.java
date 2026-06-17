package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.catalogue.Situation;  
 
public class SituationComboItem {
    
    private final Situation situation;
    
    
    public SituationComboItem(final Situation situation) {
        this.situation = situation;
    }       
    
	public Situation getSituation() {
		return situation;
	}

	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((situation == null) ? 0 : situation.hashCode());
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
        final SituationComboItem other = (SituationComboItem) obj;
        if (situation == null) {
            if (other.situation != null)
                return false;
        } else if (!situation.equals(other.situation))
            return false;
        return true;
    }    
    
    @Override
    public String toString() {
        return situation == null ? "" : situation.getSituation();
    }

	
	
    
}