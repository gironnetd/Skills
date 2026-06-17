package com.yolaine.client.ui.clients.deposant.event;

import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.Client;
import com.yolaine.entity.client.TypeIdentite;


public interface DeposantListener extends XSListener<DeposantEventPropertyName> {
    
    // ------------------------------------------------------------------------
    //     

    public void civiliteChanged(XSEvent<DeposantEventPropertyName, Civilite> evt);
    
    public void nomChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void prenomChanged(XSEvent<DeposantEventPropertyName, String> evt); 
    
    public void nomComboChanged(XSEvent<DeposantEventPropertyName, Client> evt);
    
    public void prenomComboChanged(XSEvent<DeposantEventPropertyName, Client> evt); 
    
    public void deposantChanged(XSEvent<DeposantEventPropertyName, Boolean> evt);
    
    public void loginChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void passwordChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void telephonefixeChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void telephoneportableChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void emailChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void typeidentiteChanged(XSEvent<DeposantEventPropertyName, TypeIdentite> evt);  
    
    public void numeroidentiteChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void datenaissanceChanged(XSEvent<DeposantEventPropertyName, String> evt);    
    
    public void commentaireChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void champnumerique1Changed(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void champnumerique2Changed(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void montantdeposeChanged(XSEvent<DeposantEventPropertyName, String> evt);
    
    public void montantduChanged(XSEvent<DeposantEventPropertyName, String> evt);
}