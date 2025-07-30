package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityProjectileBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityEnderPearl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityEnderPearlImpl extends EntityImpl implements EntityEnderPearl {
    public EntityEnderPearlImpl(EntityInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityProjectileBaseComponent getBaseComponent() {
        return (EntityProjectileBaseComponent) super.getBaseComponent();
    }
}
