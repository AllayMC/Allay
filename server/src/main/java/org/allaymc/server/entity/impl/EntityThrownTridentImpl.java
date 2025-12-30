package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityThrownTridentImpl extends EntityImpl implements EntityThrownTrident {
    public EntityThrownTridentImpl(EntityInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
