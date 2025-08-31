package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityBlaze;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityBlazeImpl extends EntityImpl implements EntityBlaze {
    public EntityBlazeImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
