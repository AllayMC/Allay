package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityArrowImpl extends EntityImpl implements EntityArrow {

    @Delegate
    protected EntityAgeComponent ageComponent;
    @Delegate
    protected EntityProjectileComponent projectileComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Delegate
    protected EntityDamageComponent damageComponent;
    @Delegate
    protected EntityAttributeComponent attributeComponent;

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
