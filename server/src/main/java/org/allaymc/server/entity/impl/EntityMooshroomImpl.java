package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityMooshroom;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityMooshroomImpl extends EntityImpl implements EntityMooshroom {
    public EntityMooshroomImpl(EntityInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
