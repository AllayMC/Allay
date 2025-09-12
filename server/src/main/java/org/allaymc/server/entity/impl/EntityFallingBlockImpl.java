package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityFallingBlockImpl extends EntityImpl implements EntityFallingBlock {

    @Delegate
    protected EntityPhysicsComponent physicsComponent;

    public EntityFallingBlockImpl(EntityInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityFallingBlockBaseComponent getBaseComponent() {
        return (EntityFallingBlockBaseComponent) super.getBaseComponent();
    }
}
