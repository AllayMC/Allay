package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityHorse;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityHorseImpl extends EntityImpl implements EntityHorse {
    public EntityHorseImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
