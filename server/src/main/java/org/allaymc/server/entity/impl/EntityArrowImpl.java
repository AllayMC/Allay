package org.allaymc.server.entity.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.entity.component.projectile.EntityArrowBaseComponentImpl;

public class EntityArrowImpl extends EntityImpl implements EntityArrow {

    @Delegate
    protected EntityAgeComponent ageComponent;

    @Delegate
    protected EntityProjectileComponent projectile;

    @Delegate
    protected EntityPhysicsComponent physicsComponent;

    public EntityArrowImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
