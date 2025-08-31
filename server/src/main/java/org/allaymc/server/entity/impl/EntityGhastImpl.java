package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityGhast;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityGhastImpl extends EntityImpl implements EntityGhast {
    public EntityGhastImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
