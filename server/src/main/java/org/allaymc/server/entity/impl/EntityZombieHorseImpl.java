package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityZombieHorse;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityZombieHorseImpl extends EntityImpl implements EntityZombieHorse {
    public EntityZombieHorseImpl(EntityInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
