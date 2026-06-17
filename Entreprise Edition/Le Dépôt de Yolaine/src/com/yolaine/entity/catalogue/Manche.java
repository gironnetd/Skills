package com.yolaine.entity.catalogue;

import java.io.Serializable;

import javax.persistence.*;

import com.yolaine.entity.client.Civilite;

@Entity
@Table(name="t_manche")
public class Manche implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

	private String manche;

	public Manche() {		
	}

	public Manche(String manche) {
		this.manche = manche;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getManche() {
		return manche;
	}

	public void setManche(String manche) {
		this.manche = manche;
	}	
	
	// ======================================
    // =           Methodes Privées         =
    // ======================================

    // ======================================
    // =   Methodes hash, equals, toString  =
    // ======================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manche manche = (Manche) o;

        if (!manche.equals(manche.manche)) return false;   

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = manche.hashCode();        
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Manche");
        sb.append("{manche='").append(manche).append('\'');        
        sb.append('}');
        return sb.toString();
    }
}
