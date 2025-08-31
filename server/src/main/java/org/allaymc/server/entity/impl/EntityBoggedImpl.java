package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityBogged;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityBoggedImpl extends EntityImpl implements EntityBogged {
    public EntityBoggedImpl(EntityInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
