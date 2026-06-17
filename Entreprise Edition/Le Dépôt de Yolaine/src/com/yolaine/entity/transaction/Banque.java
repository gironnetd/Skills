package com.yolaine.entity.transaction;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="t_banque")
public class Banque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String banque;

	public Banque() {
		
	}

	public Banque(String banque) {
		
		this.banque = banque;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
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

        Banque banque = (Banque) o;

        if(!this.id.equals(banque.id)) return false;
        if (!this.banque.equals(banque.banque)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + banque.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Banque");
        sb.append("{id=").append(id);
        sb.append("{, banque='").append(banque).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
