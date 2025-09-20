package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityItemImpl extends EntityImpl implements EntityItem {

    @Delegate
    protected EntityDamageComponent damageComponent;
    @Delegate
    protected EntityAttributeComponent attributeComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Delegate
    protected EntityAgeComponent ageComponent;

    public EntityItemImpl(EntityInitInfo initInfo,
                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityItemBaseComponent getBaseComponent() {
        return (EntityItemBaseComponent) super.getBaseComponent();
    }
}
