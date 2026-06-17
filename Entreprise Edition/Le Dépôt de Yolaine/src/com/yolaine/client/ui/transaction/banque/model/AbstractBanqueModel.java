package com.yolaine.client.ui.transaction.banque.model;

import static com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName.*;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;
import com.yolaine.client.ui.transaction.banque.event.BanqueEventPropertyName;
import com.yolaine.client.ui.transaction.banque.event.BanqueListener;

public abstract class AbstractBanqueModel extends
        AbstractXSModel<BanqueListener, BanqueEventPropertyName> implements
        BanqueModel {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(BanqueListener listener,
            XSEvent<BanqueEventPropertyName, ?> evt) {
    	BanqueEventPropertyName propertyName = evt.getPropertyNameEnumType();
         
       if (propertyName == BANQUE_CHANGED) {
            listener
                    .banqueChanged((XSEvent<BanqueEventPropertyName, String>) evt);
        }
    }
    
}