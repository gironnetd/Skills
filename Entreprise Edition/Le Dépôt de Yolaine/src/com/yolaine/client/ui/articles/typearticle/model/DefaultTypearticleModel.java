package com.yolaine.client.ui.articles.typearticle.model;


import static com.yolaine.client.ui.articles.typearticle.event.TypearticleEventPropertyName.*;


import com.yolaine.entity.catalogue.Categorie;


public class DefaultTypearticleModel extends AbstractTypearticleModel {
    
    private static final long serialVersionUID = 1602655452741754011L;
    

    private Categorie category;
    private Long identifierToFind;
    
    
    public DefaultTypearticleModel() {
        setCategory(new Categorie());
    }
    
    public DefaultTypearticleModel(Categorie category) {
        setCategory(category);
    }

    public Long getId() { return category.getId(); }

    public Categorie getCategory() {
        return category;
    }
    
    public void setCategory(Categorie category) {
        if (category == null) {
            throw new IllegalArgumentException("category must be non null");
        }
        
        Categorie oldValue = this.category;
        Categorie newValue = category;
        
        this.category = category;
        
        if (oldValue != null) {
           
            fireXSChanged(this, NAME_CHANGED, oldValue.getName(), newValue
                    .getName());
            fireXSChanged(this, DESCRIPTION_CHANGED, oldValue.getDescription(),
                    newValue.getDescription());
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
        return category.getName();
    }
    
    public void setName(String name) {
        Object oldValue = category.getName();
        Object newValue = name;
        
        category.setName(name);
        
        fireXSChanged(this, NAME_CHANGED, oldValue, newValue);
    }
    
    public String getDescription() {
        return category.getDescription();
    }
    
    public void setDescription(String description) {
        Object oldValue = category.getDescription();
        Object newValue = description;
        
        category.setDescription(description);
        
        fireXSChanged(this, DESCRIPTION_CHANGED, oldValue, newValue);
    }
    
    
    public void reset() {
        setName(null);
        setDescription(null);
    }
    
}