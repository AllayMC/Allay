package org.allaymc.server.entity.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityShulkerBullet;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityShulkerBulletImpl extends EntityImpl implements EntityShulkerBullet {
    public EntityShulkerBulletImpl(EntityInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
