package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySniffer;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntitySnifferImpl extends EntityImpl implements EntitySniffer {
    public EntitySnifferImpl(EntityInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
