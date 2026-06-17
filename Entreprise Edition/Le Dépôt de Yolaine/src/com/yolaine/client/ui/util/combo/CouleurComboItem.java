package com.yolaine.client.ui.util.combo;

import com.yolaine.entity.catalogue.Couleur;
  
 
public class CouleurComboItem {
    
    private final Couleur couleur;
    
    
    public CouleurComboItem(final Couleur couleur) {
        this.couleur = couleur;
    }    
    
    public Couleur getCouleur() {
		return couleur;
	}

	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((couleur == null) ? 0 : couleur.hashCode());
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
        final CouleurComboItem other = (CouleurComboItem) obj;
        if (couleur == null) {
            if (other.couleur != null)
                return false;
        } else if (!couleur.equals(other.couleur))
            return false;
        return true;
    }    
    
    @Override
    public String toString() {
        return couleur == null ? "" : couleur.getCouleur();
    }

	
    
}