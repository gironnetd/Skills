package com.yolaine.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.yolaine.exception.ValidationException;

@Entity
@Table(name = "t_adresse")
public class Adresse implements Serializable {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@Column(name = "adresse_1", length = 50, nullable = false)
	private String adresse1 ;
	
	@Column (name = "adresse_2", length = 50)
	private String adresse2 ;
	
	@Column (name = "code_postal", length = 50, nullable = false)
	private String codePostal ;
	
	@Column(length = 50, nullable = false)
	private String ville ;

	 // ======================================
    // =             Constantes             =
    // ======================================

    // ======================================
    // =            Constructeurs           =
    // ======================================

	public Adresse() {}
	
	public Adresse(String adresse1, String adresse2,
			String codePostal, String ville) {
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	// ======================================
    // =     Methodes Lifecycle Callback    =
    // ======================================
   // @PrePersist
   // @PreUpdate
   // private void validateData() {
       // if (adresse1 == null || "".equals(adresse1))
        //    throw new ValidationException("Adresse invalide");        
       // if (codepostal == null || "".equals(codepostal))
       //     throw new ValidationException("Code postal invalide");
       // if (ville == null || "".equals(ville))
       //     throw new ValidationException("Ville invalide");       
   // }
	
	public Long getId() {
		return id;
	}		

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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

        Adresse adresse = (Adresse) o;
        
        if (!id.equals(adresse.id)) return false;
        if (!adresse1.equals(adresse.adresse1)) return false;
        if (adresse2 == null || !adresse2.equals(adresse.adresse2)) return false;
        if (!codePostal.equals(adresse.codePostal)) return false;
        if (!ville.equals(adresse.ville)) return false;    
        
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + adresse1.hashCode();
        result = 31 * result + adresse2.hashCode();
        result = 31 * result + ville.hashCode();        
        result = 31 * result + codePostal.hashCode();
        result = 31 * result + ville.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Adresse");
        sb.append("{id=").append(id);
        sb.append(", adresse1='").append(adresse1).append('\'');
        sb.append(", adresse2='").append(adresse2).append('\'');        
        sb.append(", codepostal='").append(codePostal).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
