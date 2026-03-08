package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityArmorStandBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityArmorStandImpl extends EntityImpl implements EntityArmorStand {

    @Delegate
    private EntityArmorStandBaseComponent armorStandBaseComponent;

    @Getter
    @Delegate
    private EntityContainerHolderComponent containerHolderComponent;

    @Delegate
    private EntityLivingComponent livingComponent;

    @Delegate
    private EntityPhysicsComponent physicsComponent;

    public EntityArmorStandImpl(EntityInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
