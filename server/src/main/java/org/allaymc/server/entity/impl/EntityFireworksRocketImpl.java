package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.component.EntityFireworksRocketPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityFireworksRocketImpl extends EntityImpl implements EntityFireworksRocket {

    @Delegate
    private EntityFireworksRocketBaseComponent fireworksRocketBaseComponent;
    @Delegate
    private EntityFireworksRocketPhysicsComponent physicsComponent;

    public EntityFireworksRocketImpl(EntityInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
