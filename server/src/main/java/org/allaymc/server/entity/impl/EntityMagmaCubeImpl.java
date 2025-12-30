package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityMagmaCube;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityMagmaCubeImpl extends EntityImpl implements EntityMagmaCube {
    public EntityMagmaCubeImpl(EntityInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
