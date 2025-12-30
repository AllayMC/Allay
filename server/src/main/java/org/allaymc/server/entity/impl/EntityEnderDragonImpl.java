package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityEnderDragon;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityEnderDragonImpl extends EntityImpl implements EntityEnderDragon {
    public EntityEnderDragonImpl(EntityInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
