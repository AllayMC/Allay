package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityWitch;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityWitchImpl extends EntityImpl implements EntityWitch {
    public EntityWitchImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
