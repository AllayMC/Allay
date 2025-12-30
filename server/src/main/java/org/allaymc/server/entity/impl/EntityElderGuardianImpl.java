package org.allaymc.server.entity.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityElderGuardian;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityElderGuardianImpl extends EntityImpl implements EntityElderGuardian {
    public EntityElderGuardianImpl(EntityInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
