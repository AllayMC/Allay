package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityTraderLlama;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityTraderLlamaImpl extends EntityImpl implements EntityTraderLlama {
    public EntityTraderLlamaImpl(EntityInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
