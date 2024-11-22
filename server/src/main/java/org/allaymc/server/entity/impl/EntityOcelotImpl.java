package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityOcelot;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityOcelotImpl extends EntityImpl implements EntityOcelot {
    public EntityOcelotImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
