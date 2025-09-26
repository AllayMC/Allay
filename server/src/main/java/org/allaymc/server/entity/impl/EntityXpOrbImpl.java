package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityXpOrbImpl extends EntityImpl implements EntityXpOrb {

    @Delegate
    protected EntityLivingComponent livingComponent;
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Delegate
    protected EntityAgeComponent ageComponent;

    public EntityXpOrbImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityXpOrbBaseComponent getBaseComponent() {
        return (EntityXpOrbBaseComponent) super.getBaseComponent();
    }
}
