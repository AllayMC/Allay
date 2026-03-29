package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityZombie;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityZombieImpl extends EntityImpl implements EntityZombie, EntityContainerHolderComponent {

    @Delegate
    private EntityLivingComponent livingComponent;
    @Delegate
    private EntityContainerHolderComponent containerHolderComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityAIComponent aiComponent;
    @Delegate
    private EntityParallelTickComponent parallelTickComponent;
    @Delegate
    private EntityBabyComponent babyComponent;
    @Delegate
    private EntityHeadYawComponent headYawComponent;

    public EntityZombieImpl(EntityInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
