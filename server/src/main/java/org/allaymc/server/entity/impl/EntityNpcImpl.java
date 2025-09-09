package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityNpcImpl extends EntityImpl implements EntityNpc {
    public EntityNpcImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
