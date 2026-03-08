package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityFireballBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityFireball;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityFireballImpl extends EntityImpl implements EntityFireball {

    @Delegate
    private EntityFireballBaseComponent fireballBaseComponent;
    @Delegate
    private EntityProjectileComponent projectileComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityAgeComponent ageComponent;

    public EntityFireballImpl(EntityInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
