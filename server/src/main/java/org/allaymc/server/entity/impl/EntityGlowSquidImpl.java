package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityGlowSquid;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityGlowSquidImpl extends EntityImpl implements EntityGlowSquid {
    public EntityGlowSquidImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
