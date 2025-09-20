package org.allaymc.server.entity.impl;


import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public abstract class EntityImpl extends ComponentClass implements Entity {
    protected EntityBaseComponent baseComponent;

    public EntityImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    public EntityBaseComponent getBaseComponent() {
        return baseComponent;
    }
}
