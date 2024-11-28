package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityZombieVillagerV2;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityZombieVillagerV2Impl extends EntityImpl implements EntityZombieVillagerV2 {
    public EntityZombieVillagerV2Impl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
