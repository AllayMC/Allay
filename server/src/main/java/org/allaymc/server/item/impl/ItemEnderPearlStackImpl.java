package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemEnderPearlStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemEnderPearlStackImpl extends ItemStackImpl implements ItemEnderPearlStack {
    @Delegate
    private ItemProjectileComponent projectileComponent;

    public ItemEnderPearlStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
