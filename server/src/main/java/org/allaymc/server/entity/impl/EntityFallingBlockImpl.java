package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityFallingBlockImpl extends EntityImpl implements EntityFallingBlock {
    public EntityFallingBlockImpl(EntityInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }

    @Delegate
    @Override
    public EntityFallingBlockBaseComponent getBaseComponent() {
        return (EntityFallingBlockBaseComponent) super.getBaseComponent();
    }
}
