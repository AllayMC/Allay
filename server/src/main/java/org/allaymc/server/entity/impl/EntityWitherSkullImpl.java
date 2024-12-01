package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityWitherSkull;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityWitherSkullImpl extends EntityImpl implements EntityWitherSkull {
    public EntityWitherSkullImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
