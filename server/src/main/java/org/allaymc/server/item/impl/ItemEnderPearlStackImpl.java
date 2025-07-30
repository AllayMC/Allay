package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemEnderPearlStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemEnderPearlStackImpl extends ItemStackImpl implements ItemEnderPearlStack {
    @Delegate
    protected ItemProjectileComponent projectileComponent;

    public ItemEnderPearlStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
