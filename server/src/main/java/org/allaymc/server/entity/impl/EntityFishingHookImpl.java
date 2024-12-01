package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityFishingHookImpl extends EntityImpl implements EntityFishingHook {
    public EntityFishingHookImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
