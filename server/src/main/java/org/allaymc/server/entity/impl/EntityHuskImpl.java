package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityHusk;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityHuskImpl extends EntityImpl implements EntityHusk {
    public EntityHuskImpl(EntityInitInfo initInfo,
                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
