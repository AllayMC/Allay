package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityFishingHookBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityFishingHookImpl extends EntityImpl implements EntityFishingHook {

    @Delegate
    private EntityFishingHookBaseComponent fishingHookBaseComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityProjectileComponent projectileComponent;
    @Delegate
    private EntityAgeComponent ageComponent;

    public EntityFishingHookImpl(EntityInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
