package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySquid;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntitySquidImpl extends EntityImpl implements EntitySquid {
    public EntitySquidImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
