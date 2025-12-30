package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityZombieVillagerV2;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityZombieVillagerV2Impl extends EntityImpl implements EntityZombieVillagerV2 {
    public EntityZombieVillagerV2Impl(EntityInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
