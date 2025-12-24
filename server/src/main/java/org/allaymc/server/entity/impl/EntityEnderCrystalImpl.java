package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.interfaces.EntityEnderCrystal;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityEnderCrystalImpl extends EntityImpl implements EntityEnderCrystal {
    @Delegate
    protected EntityLivingComponent livingComponent;

    public EntityEnderCrystalImpl(EntityInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
