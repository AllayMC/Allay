package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntitySplashPotionProjectileComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntitySplashPotionImpl extends EntityImpl implements EntitySplashPotion {

    @Delegate
    protected EntitySplashPotionProjectileComponent projectileComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Delegate
    protected EntityAgeComponent ageComponent;

    public EntitySplashPotionImpl(EntityInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
