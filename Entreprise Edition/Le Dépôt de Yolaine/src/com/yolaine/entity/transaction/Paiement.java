package com.yolaine.entity.transaction;

import java.io.Serializable;

import javax.persistence.*;

import com.yolaine.entity.catalogue.Couleur;
import com.yolaine.entity.client.TypeIdentite;

@Entity
@Table(name = "t_paiement")
public class Paiement implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "libelle_paiement")
	private String libellepaiement;	
	
	@Column(name = "prix_vente_reel")
	private String prixVenteReel ;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "type_identite_fk", nullable=true)
	private TypeIdentite typeidentite ;
	
	@Column(name = "date_vente")
	private String datevente;
	
	@Column(name = "commentaire_vente")
	private String commentairevente;
	
	@Column(name = "numero_cheque_paiement")
	private String numeroChequePaiement;	
	
	@Column(name = "numero_banque")
	private String numeroBanque;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banque_paiement_fk")
	private Banque banquePaiement;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_paiement_fk")
	private TypePaiement typepaiement;	
	
	public Paiement() {		
		this.libellepaiement = "";
	}	
	
	public Paiement(String libellepaiement, String prixVenteReel,
			TypeIdentite typeidentite, String datevente,
			String commentairevente, String numeroChequePaiement,
			String numeroBanque, Banque banquePaiement,
			TypePaiement typepaiement) {		
		this.libellepaiement = libellepaiement;
		this.prixVenteReel = prixVenteReel;
		this.typeidentite = typeidentite;
		this.datevente = datevente;
		this.commentairevente = commentairevente;
		this.numeroChequePaiement = numeroChequePaiement;
		this.numeroBanque = numeroBanque;
		this.banquePaiement = banquePaiement;
		this.typepaiement = typepaiement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrixVenteReel() {
		return prixVenteReel;
	}


	public void setPrixVenteReel(String prixVenteReel) {
		this.prixVenteReel = prixVenteReel;
	}


	public TypeIdentite getTypeidentite() {
		return typeidentite;
	}


	public void setTypeidentite(TypeIdentite typeidentite) {
		this.typeidentite = typeidentite;
	}


	public String getDatevente() {
		return datevente;
	}


	public void setDatevente(String datevente) {
		this.datevente = datevente;
	}


	public String getCommentairevente() {
		return commentairevente;
	}


	public void setCommentairevente(String commentairevente) {
		this.commentairevente = commentairevente;
	}


	public String getNumeroChequePaiement() {
		return numeroChequePaiement;
	}


	public void setNumeroChequePaiement(String numeroChequePaiement) {
		this.numeroChequePaiement = numeroChequePaiement;
	}


	public String getNumeroBanque() {
		return numeroBanque;
	}


	public void setNumeroBanque(String numeroBanque) {
		this.numeroBanque = numeroBanque;
	}


	public Banque getBanquePaiement() {
		return banquePaiement;
	}


	public void setBanquePaiement(Banque banquePaiement) {
		this.banquePaiement = banquePaiement;
	}


	public TypePaiement getTypepaiement() {
		return typepaiement;
	}


	public void setTypepaiement(TypePaiement typepaiement) {
		this.typepaiement = typepaiement;
	}


	public String getLibellepaiement() {
		return libellepaiement;
	}


	public void setLibellepaiement(String libellepaiement) {
		this.libellepaiement = libellepaiement;
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

        Paiement paiement = (Paiement) o;
		if(this.id == null ||!this.id.equals(paiement.id)) return false;
        if (!this.libellepaiement.equals(paiement.libellepaiement)) return false;
        if (!this.prixVenteReel.equals(paiement.prixVenteReel)) return false;
        if (!this.datevente.equals(paiement.datevente)) return false;
        if (!this.commentairevente.equals(paiement.commentairevente)) return false;
        if (!this.numeroChequePaiement.equals(paiement.numeroChequePaiement)) return false;
        if (!this.numeroBanque.equals(paiement.numeroBanque)) return false;
          
        return true;
    }
    
    @Override
    public int hashCode() {
        int result;
		result = id.hashCode();
        result = 31 * result + libellepaiement.hashCode();
        result = 31 * result + prixVenteReel.hashCode(); 
        result = 31 * result + datevente.hashCode(); 
        result = 31 * result + commentairevente.hashCode(); 
        result = 31 * result + numeroChequePaiement.hashCode(); 
        result = 31 * result + numeroBanque.hashCode(); 
       
        return result;
    }  
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Paiement");
		sb.append("{id=").append(id);
		sb.append("{, libellepaiement='").append(libellepaiement).append('\'');
        sb.append(", prixVenteReel='").append(prixVenteReel).append('\'');        
        sb.append(", typeidentite=").append(typeidentite);   
        sb.append(", datevente='").append(datevente).append('\''); 
        sb.append(", commentairevente='").append(commentairevente).append('\''); 
        sb.append(", numeroChequePaiement='").append( numeroChequePaiement).append('\''); 
        sb.append(", numeroBanque='").append(numeroBanque).append('\'');
        sb.append(", banquePaiement=").append(banquePaiement);
        sb.append(", typepaiement=").append(typepaiement);        
        sb.append('}');
        return sb.toString();
    }
}





