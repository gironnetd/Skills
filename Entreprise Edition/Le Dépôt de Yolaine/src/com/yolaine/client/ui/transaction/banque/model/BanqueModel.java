package com.yolaine.client.ui.transaction.banque.model;

import org.vstm.fwk.client.ui.xswing.core.model.XSModel;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;
import com.yolaine.entity.transaction.Banque;

public interface BanqueModel extends
        XSModel<BanqueListener, BanqueEventPropertyName> {

    public Long getId();

    public Banque getBanque();
    
    public void setBanque(Banque banque);
    
    
}