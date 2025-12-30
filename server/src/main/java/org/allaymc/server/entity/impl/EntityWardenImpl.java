package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityWarden;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityWardenImpl extends EntityImpl implements EntityWarden {
    public EntityWardenImpl(EntityInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
