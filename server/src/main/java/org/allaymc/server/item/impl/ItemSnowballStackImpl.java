package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemSnowballStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSnowballStackImpl extends ItemStackImpl implements ItemSnowballStack {
    @Delegate
    protected ItemProjectileComponent projectileComponent;

    public ItemSnowballStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
