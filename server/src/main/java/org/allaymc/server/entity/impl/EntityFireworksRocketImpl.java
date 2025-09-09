package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityFireworksRocketImpl extends EntityImpl implements EntityFireworksRocket {
    public EntityFireworksRocketImpl(EntityInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
