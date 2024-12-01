package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySilverfish;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntitySilverfishImpl extends EntityImpl implements EntitySilverfish {
    public EntitySilverfishImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
