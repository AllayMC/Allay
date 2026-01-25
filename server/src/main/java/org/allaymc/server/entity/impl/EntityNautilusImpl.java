package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityNautilus;
import org.allaymc.server.component.ComponentProvider;

public class EntityNautilusImpl extends EntityImpl implements EntityNautilus {
    public EntityNautilusImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
