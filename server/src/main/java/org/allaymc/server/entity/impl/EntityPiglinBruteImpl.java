package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPiglinBrute;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityPiglinBruteImpl extends EntityImpl implements EntityPiglinBrute {
    public EntityPiglinBruteImpl(EntityInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
