package com.yolaine.entity.catalogue;
 
import com.yolaine.entity.client.Depot;
import com.yolaine.exception.ValidationException;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une Categorie du catalogue de la société YAPS.
 * Le catalogue se divise en categories, en produits puis en articles.
 *
 * @author Antonio Goncalves
 * @see Marque
 */

@Entity
@Table(name = "t_categorie")
public class Categorie implements Serializable {

    // ======================================
    // =             Attributs              =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;
    @Column(nullable = false)
    private String description;    
    
    @ManyToMany( cascade ={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "t_categorie_marque",
            joinColumns= @JoinColumn(name = "categorie_fk"),
    		inverseJoinColumns = @JoinColumn(name = "marque_fk"))
    private List<Marque> marques = new ArrayList<Marque>();
    
//    @OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "categorie_fk", nullable=true)
//    private List<Article> articles;
    
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private List<Article> articles;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "marque_fk", nullable=true)
//	private Marque marque ;

    // ======================================
    // =             Constantes             =
    // ======================================

    // ======================================
    // =            Constructeurs           =
    // ======================================
    public Categorie() {
    	
    }

    public Categorie(String name, String description) {
        this.name = name;
        this.description = description;
    }    
    
    // ======================================
    // =     Methodes Lifecycle Callback    =
    // ======================================
    @PrePersist
    @PreUpdate
    private void validateData() {
        if (name == null || "".equals(name))
            throw new ValidationException("Invalid name");
       
    }

    // ======================================
    // =          Methodes publiques        =
    // ======================================

    // ======================================
    // =          Methodes Protégées        =
    // ======================================

    // ======================================
    // =             Accesseurs             =
    // ======================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }        
    
    public void addMarques(Marque marque){
		marques.add(marque);
		marque.getCategories().add(this);
	}
    
    //public Article getCategorie() {
	//	return Categorie;
	//}

	//public void setCategorie(Article Categorie) {
	//	this.Categorie = Categorie;
	//}

	
    
    public List<Marque> getMarques() {
		return marques;
	}

	public void setMarques(List<Marque> marques) {
		this.marques = marques;
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

        Categorie category = (Categorie) o;

       
        if (description != null ? !description.equals(category.description) : category.description != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;        
        result = 31 *  name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("catégorie");
        sb.append("{name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", marques=").append(marques == null ? 0 : marques.size());
        sb.append('}');
        return sb.toString();
    }
}
