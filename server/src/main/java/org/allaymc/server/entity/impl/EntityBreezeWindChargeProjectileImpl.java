package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityBreezeWindChargeProjectile;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class EntityBreezeWindChargeProjectileImpl extends EntityImpl implements EntityBreezeWindChargeProjectile {

    @Delegate
    private EntityProjectileComponent projectileComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityAgeComponent ageComponent;

    public EntityBreezeWindChargeProjectileImpl(EntityInitInfo initInfo,
                                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
