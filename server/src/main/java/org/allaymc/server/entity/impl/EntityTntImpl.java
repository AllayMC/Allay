package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityTntBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityTnt;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityTntImpl extends EntityImpl implements EntityTnt {
    public EntityTntImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityTntBaseComponent getBaseComponent() {
        return (EntityTntBaseComponent) super.getBaseComponent();
    }
}
