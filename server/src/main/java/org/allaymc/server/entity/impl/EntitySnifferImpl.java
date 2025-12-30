package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySniffer;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntitySnifferImpl extends EntityImpl implements EntitySniffer {
    public EntitySnifferImpl(EntityInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
