package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityLightningBoltImpl extends EntityImpl implements EntityLightningBolt {
    public EntityLightningBoltImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
