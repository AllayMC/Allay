package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityWolf;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityWolfImpl extends EntityImpl implements EntityWolf {
    public EntityWolfImpl(EntityInitInfo initInfo,
                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
