package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityParrot;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityParrotImpl extends EntityImpl implements EntityParrot {
    public EntityParrotImpl(EntityInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
