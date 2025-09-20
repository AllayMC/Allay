package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPrismarineShardStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPrismarineShardStackImpl extends ItemStackImpl implements ItemPrismarineShardStack {
    public ItemPrismarineShardStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
