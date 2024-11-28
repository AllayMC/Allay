package org.allaymc.server.entity.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityAxolotl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class EntityAxolotlImpl extends EntityImpl implements EntityAxolotl {
    public EntityAxolotlImpl(EntityInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
