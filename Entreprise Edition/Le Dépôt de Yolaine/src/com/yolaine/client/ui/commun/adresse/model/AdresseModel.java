package com.yolaine.client.ui.commun.adresse.model;


import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import com.yolaine.client.ui.commun.adresse.event.AdresseEventPropertyName;
import com.yolaine.client.ui.commun.adresse.event.AdresseListener;
import com.yolaine.entity.Adresse;


public interface AdresseModel extends
        XSModel<AdresseListener, AdresseEventPropertyName> {

    public Long getId();

    public Adresse getAdresse();
    
    public void setAdresse(Adresse adresse);    
    
    public String getAdresse1();
    
    public void setAdresse1(String adresse1);
    
    public String getAdresse2();
    
    public void setAdresse2(String adresse2);
    
    public String getCodepostal();
    
    public void setCodepostal(String codepostal);
    
    public String getVille();
    
    public void setVille(String ville);
       
   
        
}