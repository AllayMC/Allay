package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityArrowImpl extends EntityImpl implements EntityArrow {

    @Delegate
    protected EntityAgeComponent ageComponent;
    @Delegate
    protected EntityProjectileComponent projectileComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;

    public EntityArrowImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityArrowBaseComponent getBaseComponent() {
        return (EntityArrowBaseComponent) super.getBaseComponent();
    }
}
