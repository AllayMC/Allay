package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPaintingBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPainting;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityPaintingImpl extends EntityImpl implements EntityPainting {
    @Delegate
    protected EntityLivingComponent livingComponent;

    public EntityPaintingImpl(EntityInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityPaintingBaseComponent getBaseComponent() {
        return (EntityPaintingBaseComponent) super.getBaseComponent();
    }
}
