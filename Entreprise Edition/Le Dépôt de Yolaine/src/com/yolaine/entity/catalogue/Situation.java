package com.yolaine.entity.catalogue;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="t_situation")
public class Situation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

	private String situation;

	public Situation() {
		
	}
	public Situation(String situation) {		
		this.situation = situation;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
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

        Situation situation = (Situation) o;

        if(!this.id.equals(situation.id)) return false;
        if(!this.situation.equals(situation.situation)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + situation.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Situation");
        sb.append("{id=").append(id);
        sb.append("{, situation='").append(situation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
