package com.yolaine.entity.client;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.catalogue.Article;
import com.yolaine.exception.ValidationException;


@Entity
@Table(name="t_client")
public class Client implements Serializable { 
		
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private boolean deposante;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "civilite_fk", nullable = false)
	private Civilite civilite ;
	
	@Column(length = 20, unique = false)
	private String nom;
	
	@Column(length = 20 , unique = false)
	private String prenom;
	
	@Column (length = 30)
	private String login;
	
	@Column (length = 30)
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_fk")
	private Adresse adresse;
	
	@Column (name = "telephone_fixe", length = 50)
	private String telephonefixe;
	
	@Column (name = "telephone_portable",length = 50)
	private String telephoneportable;
	
	@Column (length = 50)
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_identite_fk", nullable=true)
	private TypeIdentite typeidentite;
	
	@Column (name = "numero_identite",length = 30)
	private String numeroidentite;
	
	@Column (name = "date_naissance",length = 30)
	private String datenaissance;

	@Column (length = 50)
	private String commentaire;
	
	@Column (name = "champ_numerique_1", length = 50)
	private String champnumerique1;
	
	@Column (name = "champ_numerique_2", length = 50)
	private String champnumerique2;
	
	@Column (name = "montant_depose", length = 50)
	private String montantdepose;
	
	@Column (name = "montant_du", length = 50)
	private String montantdu;
	
	@OneToMany(mappedBy = "client", cascade = {CascadeType.ALL})
	private List<Depot> depots = new ArrayList<Depot>();
	
	@OneToMany(mappedBy = "clientArticle", cascade = { CascadeType.ALL })
	private List<Article> articles = new ArrayList<Article>() ;

	// ======================================
    // =            Constructeurs           =
    // ======================================
    public Client() {
    }   

    public Client(Long id) {
		this.id = id;
	}	

	// ======================================
    // =     Methodes Lifecycle Callback    =
    // ======================================
    @PrePersist
    @PreUpdate
    private void validateData() {
        if (nom == null || "".equals(nom))
            throw new ValidationException("nom invalide");
        //if (prenom == null || "".equals(prenom))
        //    throw new ValidationException("prénom invalide");       
    }
	
	 // ======================================
    // =             Accesseurs             = 
    // ======================================
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public Civilite getCivilite() {
		return civilite;
	}	

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}	
	
	public boolean isDeposante() {
		return deposante;
	}

	public void setDeposante(boolean deposante) {
		this.deposante = deposante;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Adresse getAdresse() {
		return adresse;
	}
	
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}	
	
	public String getTelephonefixe() {
		return telephonefixe;
	}

	public void setTelephonefixe(String telephonefixe) {
		this.telephonefixe = telephonefixe;
	}

	public String getTelephoneportable() {
		return telephoneportable;
	}

	public void setTelephoneportable(String telephoneportable) {
		this.telephoneportable = telephoneportable;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public TypeIdentite getTypeidentite() {
		return typeidentite;
	}

	public void setTypeidentite(TypeIdentite typeidentite) {
		this.typeidentite = typeidentite;
	}

	public String getNumeroidentite() {
		return numeroidentite;
	}

	public void setNumeroidentite(String numeroidentite) {
		this.numeroidentite = numeroidentite;
	}
	
	public String getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(String datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getChampnumerique1() {
		return champnumerique1;
	}

	public void setChampnumerique1(String champnumerique1) {
		this.champnumerique1 = champnumerique1;
	}

	public String getChampnumerique2() {
		return champnumerique2;
	}

	public void setChampnumerique2(String champnumerique2) {
		this.champnumerique2 = champnumerique2;
	}	
	
	public String getMontantdepose() {
		
		return montantdepose;
	}

	public void setMontantdepose(String montantdepose) {
		this.montantdepose = montantdepose;
	}

	public String getMontantdu() {
		return montantdu;
	}

	public void setMontantdu(String montantdu) {
		this.montantdu = montantdu;
	}		
	
	public void addDepots(Depot depot){
		depots.add(depot);
		depot.setClient(this);
	}
	
	public void addArticles(Article article){
		articles.add(article);
		//article.setClientarticle(this).
		this.articles.add(article);
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article){
		articles.add(article);
		
		article.setClientArticle(this);
	}
	
	
	// ======================================
    // =   Methodes hash, equals, toString  =
    // ======================================
   
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (datenaissance != null ? !datenaissance.equals(client.datenaissance) : client.datenaissance != null) return false;
        if (civilite != null ? !civilite.equals(client.civilite) : client.civilite != null) return false;        
        if (nom != null ? !nom.equals(client.nom) : client.nom != null) return false;        
        if (prenom != null ? !prenom.equals(client.prenom) : client.prenom != null) return false;
        if (adresse != null ? !adresse.equals(client.adresse) : client.adresse != null) return false;
        if (login != null ? !login.equals(client.login) : client.login != null) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (telephonefixe != null ? !telephonefixe.equals(client.telephonefixe) : client.prenom != null) return false;
        if (telephoneportable != null ? !telephoneportable.equals(client.telephoneportable) : client.prenom != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (typeidentite != null ? !typeidentite.equals(client.typeidentite) : client.typeidentite != null) return false;
        if (numeroidentite != null ? !numeroidentite.equals(client.numeroidentite) : client.numeroidentite != null) return false;
        if (commentaire != null ? !commentaire.equals(client.commentaire) : client.commentaire != null) return false;
        if (champnumerique1 != null ? !champnumerique1.equals(client.champnumerique1) : client.champnumerique1 != null) return false;
        if (champnumerique2 != null ? !champnumerique2.equals(client.champnumerique2) : client.champnumerique2 != null) return false;
        if (montantdepose != null ? !montantdepose.equals(client.montantdepose) : client.montantdepose != null) return false;
        if (montantdu != null ? !montantdu.equals(client.montantdu) : client.montantdu != null) return false;
        //if (!datenaissance.equals(client.datenaissance)) return false;
        //if (!email.equals(client.email)) return false;
        //if (!nom.equals(client.nom)) return false;
        if (id!=(client.id)) return false;
        //if (!adresse.equals(client.adresse)) return false;
        //if (!prenom.equals(client.prenom)) return false;        
        //if (telephonefixe != null ? !telephonefixe.equals(client.telephonefixe) : client.telephonefixe != null) return false;
        //if (telephoneportable != null ? !telephoneportable.equals(client.telephoneportable) : client.telephoneportable != null) return false;
        //if (!montantdepose.equals(client.montantdepose)) return false;
        //if (!montantdu.equals(client.montantdu)) return false;
        //if (!champnumerique1.equals(client.champnumerique1)) return false;
        //if (!champnumerique2.equals(client.champnumerique2)) return false;
        //if (!commentaire.equals(client.commentaire)) return false;
        //if (!typeidentite.equals(client.typeidentite)) return false;
        //if (!numeroidentite.equals(client.numeroidentite)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + nom.hashCode();
        result = 31 * result + prenom.hashCode();        
        result = 31 * result +  telephonefixe.hashCode();
        result = 31 * result + telephoneportable.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + datenaissance.hashCode();
        result = 31 * result + montantdepose.hashCode();
        result = 31 * result + champnumerique1.hashCode();
        result = 31 * result + champnumerique2.hashCode();
        result = 31 * result + typeidentite.hashCode();
        result = 31 * result + numeroidentite.hashCode();
        result = 31 * result + montantdu.hashCode();
        result = 31 * result + commentaire.hashCode();
        result = 31 * result + adresse.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Client");
        sb.append("{id=").append(id);
        sb.append(", civilite=").append(civilite);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", adresse=").append(adresse);
        sb.append(", telephonefixe='").append(telephonefixe).append('\'');
        sb.append(", telephoneportable='").append(telephoneportable).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", typeidentite=").append(typeidentite);
        sb.append(", numeroidentite='").append(numeroidentite).append('\'');
        sb.append(", datenaissance='").append(datenaissance).append('\'');
        sb.append(", commentaire='").append(datenaissance).append('\'');
        sb.append(", champnumerique1='").append(champnumerique1).append('\'');
        sb.append(", champnumerique2='").append(champnumerique2).append('\'');
        sb.append(", montantdepose=").append(montantdepose);
        sb.append(", montantdu=").append(montantdu);
        sb.append('}');
        return sb.toString();
    }
}
