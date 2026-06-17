package com.yolaine.client.ui.articles.marque.model;


import static com.yolaine.client.ui.articles.marque.event.MarqueEventPropertyName.*;


import com.yolaine.entity.catalogue.Categorie;
import com.yolaine.entity.catalogue.Marque;


public class DefaultMarqueModel extends AbstractMarqueModel {
    
    private static final long serialVersionUID = 270830383994219430L;
    

    private Marque marque;
    private Long identifierToFind;
    
    
    public DefaultMarqueModel() {
        setMarque(new Marque());
    }
    
    public DefaultMarqueModel(Marque product) {
        setMarque(product);
    }

    public Long getId() { return marque.getId(); }


    public Marque getMarque() {
        return marque;
    }
    
    public void setMarque(Marque product) {
        if (product == null) {
            throw new IllegalArgumentException("product must be non null");
        }
        
        Marque oldValue = this.marque;
        Marque newValue = product;
        
        this.marque = product;
        
        if (oldValue != null) {
           
            fireXSChanged(this, NAME_CHANGED, oldValue.getName(), newValue
                    .getName());
            fireXSChanged(this, DESCRIPTION_CHANGED, oldValue.getDescription(),
                    newValue.getDescription());
            //fireXSChanged(this, CATEGORY_CHANGED, oldValue.getCategorie(),
            //        newValue.getCategorie());
        }
    }
    
    
    public Long getIdentifierToFind() {
        return identifierToFind;
    }
    
    public void setIdentifierToFind(Long identifierToFind) {
        Object oldValue = this.identifierToFind;
        Object newValue = identifierToFind;
        
        this.identifierToFind = identifierToFind;
        
        fireXSChanged(this, IDENTIFIER_CHANGED, oldValue, newValue);
    }   
    
    public String getName() {
        return marque.getName();
    }
    
    public void setName(String name) {
        Object oldValue = marque.getName();
        Object newValue = name;
        
        marque.setName(name);
        
        fireXSChanged(this, NAME_CHANGED, oldValue, newValue);
    }
    
    public String getDescription() {
        return marque.getDescription();
    }
    
    public void setDescription(String description) {
        Object oldValue = marque.getDescription();
        Object newValue = description;
        
        marque.setDescription(description);
        
        fireXSChanged(this, DESCRIPTION_CHANGED, oldValue, newValue);
    }
    
   // public List<Categorie> getCategory() {
    //    return product.getCategorie();
   // }
    
   // public void setCategory(Categorie category) {
     //   Object oldValue = product.getCategorie();
     //   Object newValue = category;
        
     //   product.setCategorie(category);
        
     //   fireXSChanged(this, CATEGORY_CHANGED, oldValue, newValue);
   // }
    
    
    public void reset() {
        setName(null);
        setDescription(null);
        setCategory(null);
    }

	@Override
	public Categorie getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCategory(Categorie category) {
		// TODO Auto-generated method stub
		
	}
    
}