package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityLingeringPotion;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityLingeringPotionImpl extends EntityImpl implements EntityLingeringPotion {

    @Delegate
    private EntityPotionComponent potionComponent;
    @Delegate
    private EntityProjectileComponent projectileComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityAgeComponent ageComponent;

    public EntityLingeringPotionImpl(EntityInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
