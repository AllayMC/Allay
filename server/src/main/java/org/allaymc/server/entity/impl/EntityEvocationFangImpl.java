package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityEvocationFang;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityEvocationFangImpl extends EntityImpl implements EntityEvocationFang {
    public EntityEvocationFangImpl(EntityInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
