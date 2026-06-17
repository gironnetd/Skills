package com.yolaine.entity.catalogue;

import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.exception.ValidationException;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un Produit du catalogue de la société YAPS.
 * Le catalogue se divise en categories, en produits puis en articles.
 *
 * @author Antonio Goncalves
 * @see Categorie
 */

@Entity
@Table(name = "t_marque")
public class Marque implements Serializable {

    // ======================================
    // =             Attributs              =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;
    @Column(nullable = false)
    private String description;
    
//    @ManyToMany(mappedBy="marques", fetch = FetchType.EAGER)
//    @JoinColumn(name = "marque_fk")
	private List<Categorie> categories = new ArrayList<Categorie>();

   // @OneToMany(mappedBy = "marque",  cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    //@OrderBy("marque ASC")
    //private List<Article> articles;

    // ======================================
    // =             Constantes             =
    // ======================================

    // ======================================
    // =            Constructeurs           =
    // ======================================
    public Marque() {    	
    }

    public Marque(String name) {
        this.name = name;       
    }

    public Marque(String name, String description) {
        this.name = name;
        this.description = description;
    }    

	// ======================================
    // =          Methodes publiques        =
    // ======================================
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
	
    public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	 public void addCategories(Categorie categorie){
			categories.add(categorie);
			categorie.getMarques().add(this);
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

        Marque product = (Marque) o;
        
       
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (categories != null ? !categories.equals(product.categories) : product.categories != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
       
        result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Marque");        
        sb.append("{name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');       
        sb.append(", categories=").append(categories == null ? 0 : categories.size());
        sb.append('}');
        return sb.toString();
    }
}