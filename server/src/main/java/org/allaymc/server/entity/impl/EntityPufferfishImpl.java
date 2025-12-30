package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPufferfish;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityPufferfishImpl extends EntityImpl implements EntityPufferfish {
    public EntityPufferfishImpl(EntityInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
