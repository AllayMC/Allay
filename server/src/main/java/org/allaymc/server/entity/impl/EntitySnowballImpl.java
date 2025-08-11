package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySnowball;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntitySnowballImpl extends EntityImpl implements EntitySnowball {

    @Delegate
    protected EntityProjectileComponent projectileComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Delegate
    protected EntityAgeComponent ageComponent;

    public EntitySnowballImpl(EntityInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
