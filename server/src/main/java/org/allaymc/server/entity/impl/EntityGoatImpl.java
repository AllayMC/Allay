package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityGoat;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityGoatImpl extends EntityImpl implements EntityGoat {
    public EntityGoatImpl(EntityInitInfo initInfo,
                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
