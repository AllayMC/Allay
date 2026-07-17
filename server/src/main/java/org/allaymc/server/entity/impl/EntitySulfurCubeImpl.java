package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySulfurCube;
import org.allaymc.server.component.ComponentProvider;

public class EntitySulfurCubeImpl extends EntityImpl implements EntitySulfurCube {
    public EntitySulfurCubeImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
