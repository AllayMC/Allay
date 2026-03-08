package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLightningBoltBaseComponent;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityLightningBoltImpl extends EntityImpl implements EntityLightningBolt {
    @Delegate
    private EntityLightningBoltBaseComponent baseComponent;

    public EntityLightningBoltImpl(EntityInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
