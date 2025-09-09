package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityVillagerV2Impl extends EntityImpl implements EntityVillagerV2 {

    @Delegate
    protected EntityAttributeComponent attributeComponent;
    @Delegate
    protected EntityDamageComponent damageComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;

    public EntityVillagerV2Impl(EntityInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
