package com.yolaine.entity.transaction;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "t_remboursement")
public class Remboursement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "libelle_remboursement")
	private String libelleRemboursement;
	
	@Column(name = "montant_rembourse")
	private String montantRembourse ;
	
	@Column(name = "date_remboursement")
	private String dateremboursement;		
	
	@Column(name = "numero_cheque_remboursement")
	private String numeroChequeRemboursement;		
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_remboursement_fk")
	private TypePaiement typeremboursement;	
	
	@Column(name = "commentaire_remboursement")
	private String commentaireremboursement;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banque_remboursement_fk")
	private Banque banqueRemboursement;

	public Remboursement() {
		this.libelleRemboursement ="";
	}		

	public Remboursement(String montantRembourse, String dateremboursement,
			String numeroChequeRemboursement, TypePaiement typeremboursement,
			String commentaireremboursement, Banque banqueRemboursement) {
		
		this.montantRembourse = montantRembourse;
		this.dateremboursement = dateremboursement;
		this.numeroChequeRemboursement = numeroChequeRemboursement;
		this.typeremboursement = typeremboursement;
		this.commentaireremboursement = commentaireremboursement;
		this.banqueRemboursement = banqueRemboursement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLibelleRemboursement() {
		return libelleRemboursement;
	}

	public void setLibelleRemboursement(String libelleRemboursement) {
		this.libelleRemboursement = libelleRemboursement;
	}

	public String getMontantRembourse() {
		return montantRembourse;
	}

	public void setMontantRembourse(String montantRembourse) {
		this.montantRembourse = montantRembourse;
	}

	public String getDateremboursement() {
		return dateremboursement;
	}

	public void setDateremboursement(String dateremboursement) {
		this.dateremboursement = dateremboursement;
	}

	public String getNumeroChequeRemboursement() {
		return numeroChequeRemboursement;
	}

	public void setNumeroChequeRemboursement(String numeroChequeRemboursement) {
		this.numeroChequeRemboursement = numeroChequeRemboursement;
	}

	public TypePaiement getTyperemboursement() {
		return typeremboursement;
	}

	public void setTyperemboursement(TypePaiement typeremboursement) {
		this.typeremboursement = typeremboursement;
	}

	public String getCommentaireremboursement() {
		return commentaireremboursement;
	}

	public void setCommentaireremboursement(String commentaireremboursement) {
		this.commentaireremboursement = commentaireremboursement;
	}

	public Banque getBanqueRemboursement() {
		return banqueRemboursement;
	}

	public void setBanqueRemboursement(Banque banqueRemboursement) {
		this.banqueRemboursement = banqueRemboursement;
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

        Remboursement remboursement = (Remboursement) o;

		if(this.id == null ||!this.id.equals(remboursement.id)) return false;
        if (!this.libelleRemboursement.equals(remboursement.libelleRemboursement)) return false;
        if (!this.montantRembourse.equals(remboursement.montantRembourse)) return false;
        if (!this.dateremboursement.equals(remboursement.dateremboursement)) return false;
        if (!this.numeroChequeRemboursement.equals(remboursement.numeroChequeRemboursement)) return false;
        if (!this.commentaireremboursement.equals(remboursement.commentaireremboursement)) return false;
       
        return true;
    }   
    
    @Override
    public int hashCode() {
        int result;
		result = id.hashCode();
		result = 31 * result + libelleRemboursement.hashCode();
        result = 31 * result + montantRembourse.hashCode(); 
        result = 31 * result + dateremboursement.hashCode(); 
        result = 31 * result + numeroChequeRemboursement.hashCode(); 
        result = 31 * result + commentaireremboursement.hashCode(); 
       
        return result;
    }    
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Remboursement");
		sb.append("{id=").append(id);
		sb.append("{, libelleRemboursement='").append(libelleRemboursement).append('\'');
        sb.append("{, montantRembourse='").append(montantRembourse).append('\'');
        sb.append(", dateremboursement='").append(dateremboursement).append('\''); 
        sb.append(", numeroChequeRemboursement='").append(numeroChequeRemboursement).append('\''); 
        sb.append(", typeremboursement=").append(typeremboursement);
        sb.append(", commentaireremboursement='").append(commentaireremboursement).append('\''); 
        sb.append(", banqueRemboursement=").append(banqueRemboursement);        
        sb.append('}');
        return sb.toString();
    }
	
}
