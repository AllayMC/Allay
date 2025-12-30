package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.interfaces.ItemPotionStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPotionStackImpl extends ItemStackImpl implements ItemPotionStack {
    @Delegate
    private ItemPotionComponent potionComponent;

    public ItemPotionStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
