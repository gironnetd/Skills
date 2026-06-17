package com.yolaine.client.ui.util.combo;


import com.yolaine.entity.client.Client;  
 
public class PrenomComboItem {
    
    public Client client = new Client();
    
    
    public PrenomComboItem(final Client client) {
        this.client = client;
    }
        
    public Client getClient() {
		return client;
	}

	@Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((client == null) ? 0 : client.getNom().hashCode())
                + ((client == null) ? 0 : client.getPrenom().hashCode()) ;
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
        final PrenomComboItem other = (PrenomComboItem) obj;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        return true;
    }    
    
    @Override
    public String toString() { 
    	
        return client == null ? " " : client.getPrenom()  ;        	
    }
    
	    
}