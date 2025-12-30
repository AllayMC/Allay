package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityCat;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityCatImpl extends EntityImpl implements EntityCat {
    public EntityCatImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
