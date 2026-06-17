package com.yolaine.entity.transaction;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="t_type_paiement")
public class TypePaiement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type_paiement")
	private String typePaiement;

	public TypePaiement() {}

	public TypePaiement(String typePaiement) {
		this.typePaiement = typePaiement;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(String typePaiement) {
		this.typePaiement = typePaiement;
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

        TypePaiement typePaiement = (TypePaiement) o;

        if(!this.id.equals(typePaiement.id)) return false;
        if (!this.typePaiement.equals(typePaiement.typePaiement)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + typePaiement.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TypePaiement");
        sb.append("{id=").append(id);
        sb.append("{, typePaiement='").append(typePaiement).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
