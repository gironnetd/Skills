package com.yolaine.entity.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.yolaine.entity.catalogue.Article;
import com.yolaine.entity.catalogue.Couleur;

@Entity
@Table(name = "t_depot")
public class Depot implements Serializable {

	@Id
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "depot_seq"
//	)
//	@SequenceGenerator(
//			name = "depot_seq",
//			sequenceName = "DEPOT_SEQUENCE",  // Nom en majuscules pour Derby
//			initialValue = 1,
//			allocationSize = 1
//	)
	private Long id;
	
	@Version
	private int version;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_fk",nullable = false)
	private Client client;
	
	@Column(name = "date_depot")
	private String dateDepot;
	
	@OneToMany(mappedBy="depot", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Article> articles = new ArrayList<Article>();

	@Column(name = "cloture_depot")
	private boolean clotureDepot ;

	 // ======================================
    // =            Constructeurs           =
    // ======================================

	public Depot() {}
	
	public Depot(Long id) {
		this.id = id;
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
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(String dateDepot) {
		this.dateDepot = dateDepot;
	}	

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public boolean isClotureDepot() {
		return clotureDepot;
	}

	public void setClotureDepot(boolean clotureDepot) {
		this.clotureDepot = clotureDepot;
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

        Depot depot = (Depot) o;

        if (!depot.equals(depot.id)) return false;
        if (!depot.equals(depot.dateDepot)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + dateDepot.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Depot");
        sb.append("{id='").append(id).append('\''); 
        sb.append(", client=").append(client);
        sb.append(", dateDepot='").append(dateDepot).append('\'');
        sb.append(", articles=").append(articles == null ? 0 : articles.size());
        sb.append(", clotureDepot=").append(clotureDepot);
        sb.append('}');
        return sb.toString();
    }
	
	
}
