package com.yolaine.client.ui.clients.client.event;

import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import com.yolaine.entity.Adresse;
import com.yolaine.entity.client.Civilite;
import com.yolaine.entity.client.TypeIdentite;


public class ClientAdapter extends XSAdapter<ClientEventPropertyName>
implements ClientListener {	

	
	
	public void civiliteChanged(XSEvent<ClientEventPropertyName, Civilite> evt){	    	
	}

	public void nomChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}
	
	public void prenomChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}
	
	public void deposantChanged(XSEvent<ClientEventPropertyName, Boolean> evt){	    	
	}
	
	
	public void loginChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void passwordChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}
	
	public void telephonefixeChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void telephoneportableChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void emailChanged(XSEvent<ClientEventPropertyName, String> evt){
	}

	public void typeidentiteChanged(XSEvent<ClientEventPropertyName, TypeIdentite> evt){	    	
	}

	public void numeroidentiteChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void datenaissanceChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void commentaireChanged(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void champnumerique1Changed(XSEvent<ClientEventPropertyName, String> evt){	    	
	}

	public void champnumerique2Changed(XSEvent<ClientEventPropertyName, String> evt){	    	
	}	
}