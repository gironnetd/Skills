package com.yolaine.entity.catalogue;

import java.io.Serializable;
import javax.persistence.*;
import com.yolaine.entity.transaction.*;
import com.yolaine.entity.client.*;

@Entity
@Table(name="t_article")
public class Article implements Serializable {

	@Id
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "article_seq"
//	)
//	@SequenceGenerator(
//			name = "article_seq",
//			sequenceName = "ARTICLE_SEQUENCE",  // Nom en majuscules pour Derby
//			initialValue = 1,
//			allocationSize = 1
//	)
	private Long id ;
	
	@Version
	private int version ;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name= "depot_fk",nullable = false)
	private Depot depot;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="client_fk",nullable=false)
	private Client clientArticle ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_fk", nullable = false )
	private Categorie categorie ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marque_fk")
	private Marque marque ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "couleur_1_fk", nullable = false)
	private Couleur couleur1 ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "couleur_2_fk")
	private Couleur couleur2 ;
	
	@Column	(length = 50)
	private String taille ;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manche_fk")
	private Manche manche ;
	
	@Column(name = "montant_depot", nullable = false)
	private String montantDepot;

	@Column (name = "prix_vente", nullable = false)
	private String prixVente ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "situation_fk")
	private Situation situation ;
	
	@Column (nullable = false)
	private boolean solde ;	
	
	@Column
	private String pourcentage ;
	
	@Column(length = 150)
	private String texte ;

	@Column(name = "date_depot")
	private String dateDepot;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "paiement_fk")
	private Paiement paiement;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "remboursement_fk")
	private Remboursement remboursement;

	// ======================================
    // =            Constructeurs           =
    // ======================================
		
	public Article() {		
	}
	
	public Article(Long id) {
		this.id = id;
	}	
	
	public Article(Long id, int version, Depot depot, Client clientarticle,
			Categorie categorie, Marque marque, Couleur couleur1,
			Couleur couleur2, String taille, Manche manche,
			String montantdepot, String prixvente,
			Situation situation, boolean solde, String pourcentage,
			String texte, String datedepot, Paiement paiement,
			Remboursement remboursement) {
		super();
		this.id = id;
		this.version = version;
		this.depot = depot;
		this.clientArticle = clientarticle;
		this.categorie = categorie;
		this.marque = marque;
		this.couleur1 = couleur1;
		this.couleur2 = couleur2;
		this.taille = taille;
		this.manche = manche;
		this.montantDepot = montantdepot;
		this.prixVente = prixvente;
		this.situation = situation;
		this.solde = solde;
		this.pourcentage = pourcentage;
		this.texte = texte;
		this.dateDepot = datedepot;
		this.paiement = paiement;
		this.remboursement = remboursement;
	}

	// ======================================
	// =             Accesseurs             = 
	// ======================================

    public void calculerPrixVente() {
        if (montantDepot == null) {
            prixVente = "0.0";
            return;
        }  
       Float f = (float) ((Float.valueOf(montantDepot))*2*1.2);
       float f1 = ((float)((int)(f*100)))/100; 
       prixVente = String.valueOf(f1);
    }
	
	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Client getClientArticle() {
		return clientArticle;
	}

	public void setClientArticle(Client clientArticle) {
		this.clientArticle = clientArticle;
	}		

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie Categorie) {
		this.categorie = Categorie;
	}			
	
	public String getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(String dateDepot) {
		this.dateDepot = dateDepot;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}		
	
	public Couleur getCouleur1() {
		return couleur1;
	}

	public void setCouleur1(Couleur couleur1) {
		this.couleur1 = couleur1;
	}

	public Couleur getCouleur2() {
		return couleur2;
	}

	public void setCouleur2(Couleur couleur2) {
		this.couleur2 = couleur2;
	}
	
	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public Manche getManche() {
		return manche;
	}

	public void setManche(Manche manche) {
		this.manche = manche;
	}

	public String getMontantDepot() {
		return montantDepot;
	}

	public void setMontantDepot(String montantDepot) {
		this.montantDepot = montantDepot;
	}

	public String getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(String prixVente) {
		this.prixVente = prixVente;
	}		

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}		

	public boolean isSolde() {
		return solde;
	}

	public void setSolde(boolean solde) {
		this.solde = solde;
	}

	public String getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

	public Remboursement getRemboursement() {
		return remboursement;
	}

	public void setRemboursement(Remboursement remboursement) {
		this.remboursement = remboursement;
	}

	 // ======================================
    // =           Methodes Privées         =
    // ======================================

    // ======================================
    // =   Méthodes hash, equals, toString  =
    // ======================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;
        
        if (id!=(article.id)) return false;        
        if (!article.equals(article.version)) return false;
        if (!article.equals(article.taille)) return false;
        if (!article.equals(article.montantDepot)) return false;
        if (!article.equals(article.prixVente)) return false;
        if (!article.equals(article.pourcentage)) return false;
        if (!article.equals(article.taille)) return false;
        if (!article.equals(article.texte)) return false;       
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + version;
        result = 31 * result + taille.hashCode();
        result = 31 * result + montantDepot.hashCode();
        result = 31 * result + prixVente.hashCode();
        result = 31 * result + pourcentage.hashCode();
        result = 31 * result + taille.hashCode();
        result = 31 * result + texte.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Article");
        sb.append("{id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", depot=").append(depot);        
        sb.append(", clientArticle=").append(clientArticle);
        sb.append(", categorie=").append(categorie);
        sb.append(", marque=").append(marque);
        sb.append(", couleur1=").append(couleur1);
        sb.append(", couleur2=").append(couleur2);        
        sb.append(", taille='").append(taille).append('\'');
        sb.append(", manche=").append(manche);
        sb.append(", montantDepot='").append(montantDepot).append('\'');
        sb.append(", prixVente='").append(prixVente).append('\'');
        sb.append(", situation=").append(situation);
        sb.append(", solde=").append(solde);
        sb.append(", pourcentage='").append(pourcentage).append('\'');
        sb.append(", texte='").append(texte).append('\'');
        sb.append(", dateDepot='").append(dateDepot).append('\'');
        sb.append(", paiement=").append(paiement);
        sb.append(", remboursement=").append(remboursement);   
        sb.append('}');
        return sb.toString();
    }
}
