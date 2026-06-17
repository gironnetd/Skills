package com.yolaine.entity.client;

import java.io.Serializable;

import javax.persistence.*;

import com.yolaine.entity.catalogue.Marque;

@Entity
@Table(name = "t_civilite")
public class Civilite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String civilite ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Civilite(String civilite) {
		super();
		this.civilite = civilite;
	}

	public Civilite() {
		super();
	}
	
	// ======================================
    // =           Méthodes Privées         =
    // ======================================

    // ======================================
    // =   Méthodes hash, equals, toString  =
    // ======================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Civilite civilite = (Civilite) o;

        if(!this.id.equals(civilite.id)) return false;
        if (!this.civilite.equals(civilite.civilite)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + civilite.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Civilite");
        sb.append("{id=").append(id);
        sb.append("{, civilite='").append(civilite).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
