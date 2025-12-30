package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityTntBaseComponent;
import org.allaymc.api.entity.interfaces.EntityTnt;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityTntImpl extends EntityImpl implements EntityTnt {

    @Delegate
    private EntityTntBaseComponent tntBaseComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;

    public EntityTntImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
