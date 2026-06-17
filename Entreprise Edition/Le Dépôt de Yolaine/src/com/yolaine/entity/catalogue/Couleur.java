package com.yolaine.entity.catalogue;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="t_couleur")
public class Couleur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

	private String couleur;

	public Couleur() {		
	}

	public Couleur(String couleur) {
		this.couleur = couleur;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
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

        Couleur couleur = (Couleur) o;

        if (!couleur.equals(couleur.couleur)) return false;   

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = couleur.hashCode();        
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Couleur");
        sb.append("{couleur='").append(couleur).append('\'');        
        sb.append('}');
        return sb.toString();
    }
}
