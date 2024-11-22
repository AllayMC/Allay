package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityTadpole;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityTadpoleImpl extends EntityImpl implements EntityTadpole {
    public EntityTadpoleImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
