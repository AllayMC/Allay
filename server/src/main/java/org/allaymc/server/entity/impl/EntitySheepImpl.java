package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntitySheepImpl extends EntityImpl implements EntitySheep {

    @Delegate
    private EntitySheepBaseComponent sheepBaseComponent;

    @Delegate
    private EntityLivingComponent livingComponent;

    @Delegate
    private EntityPhysicsComponent physicsComponent;

    @Delegate
    private EntityAIComponent aiComponent;

    @Delegate
    private EntityAnimalComponent animalComponent;

    @Delegate
    private EntityBabyComponent babyComponent;

    @Delegate
    private EntityDyeableComponent dyeableComponent;

    @Delegate
    private EntityHeadYawComponent headYawComponent;

    public EntitySheepImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
