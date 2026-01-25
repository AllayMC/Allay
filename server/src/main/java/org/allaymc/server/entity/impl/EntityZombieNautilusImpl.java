package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityZombieNautilus;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityZombieNautilusImpl extends EntityImpl implements EntityZombieNautilus {
    public EntityZombieNautilusImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
