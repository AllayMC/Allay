package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityCow;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityCowImpl extends EntityImpl implements EntityCow {
    public EntityCowImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
