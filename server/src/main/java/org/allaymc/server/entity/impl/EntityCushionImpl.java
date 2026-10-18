package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityCushion;
import org.allaymc.server.component.ComponentProvider;

public class EntityCushionImpl extends EntityImpl implements EntityCushion {
    public EntityCushionImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
