package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityPlayerImpl extends EntityImpl implements EntityPlayer {

    @Delegate
    private EntityPlayerBaseComponent playerBaseComponent;
    @Getter
    @Delegate
    private EntityContainerHolderComponent containerHolderComponent;
    @Delegate
    private EntityLivingComponent livingComponent;
    @Getter
    @Delegate
    private EntityPhysicsComponent physicsComponent;

    public EntityPlayerImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}