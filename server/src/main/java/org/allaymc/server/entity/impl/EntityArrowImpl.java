package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityArrowImpl extends EntityImpl implements EntityArrow {

    @Delegate
    private EntityArrowBaseComponent arrowBaseComponent;
    @Delegate
    private EntityAgeComponent ageComponent;
    @Delegate
    private EntityProjectileComponent projectileComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityLivingComponent livingComponent;

    public EntityArrowImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
