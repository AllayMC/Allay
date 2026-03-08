package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAreaEffectCloudBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.interfaces.EntityAreaEffectCloud;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class EntityAreaEffectCloudImpl extends EntityImpl implements EntityAreaEffectCloud {
    @Delegate
    private EntityAreaEffectCloudBaseComponent areaEffectCloudBaseComponent;
    @Delegate
    private EntityPotionComponent potionComponent;

    public EntityAreaEffectCloudImpl(EntityInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
