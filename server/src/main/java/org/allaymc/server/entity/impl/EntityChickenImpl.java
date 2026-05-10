package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityChicken;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityChickenImpl extends EntityImpl implements EntityChicken {

    @Delegate
    private EntityLivingComponent livingComponent;
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    @Delegate
    private EntityAIComponent aiComponent;
    @Delegate
    private EntityParallelTickComponent parallelTickComponent;
    @Delegate
    private EntityAnimalComponent animalComponent;
    @Delegate
    private EntityBabyComponent babyComponent;
    @Delegate
    private EntityHeadYawComponent headYawComponent;

    public EntityChickenImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
