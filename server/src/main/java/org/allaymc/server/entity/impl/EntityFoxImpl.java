package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFox;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityFoxImpl extends EntityImpl implements EntityFox {
    public EntityFoxImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
