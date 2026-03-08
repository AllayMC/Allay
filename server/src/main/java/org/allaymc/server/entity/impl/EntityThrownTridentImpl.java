package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.component.EntityThrownTridentBaseComponent;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Thrown trident entity implementation.
 *
 * @author daoge_cmd
 */
public class EntityThrownTridentImpl extends EntityImpl implements EntityThrownTrident {

    @Delegate
    private EntityThrownTridentBaseComponent tridentBaseComponent;
    @Delegate
    private EntityProjectileComponent projectileComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityAgeComponent ageComponent;

    public EntityThrownTridentImpl(EntityInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
