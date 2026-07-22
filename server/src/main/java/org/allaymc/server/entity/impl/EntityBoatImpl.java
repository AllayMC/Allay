package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityBoatBaseComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityBoatImpl extends EntityImpl implements EntityBoat {
    @Delegate
    private EntityBoatBaseComponent boatBaseComponent;
    @Delegate
    private EntityLivingComponent livingComponent;
    @Getter
    @Delegate
    private EntityPhysicsComponent physicsComponent;

    public EntityBoatImpl(EntityInitInfo initInfo,
                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
