package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAmethystShardStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAmethystShardStackImpl extends ItemStackImpl implements ItemAmethystShardStack {
    public ItemAmethystShardStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
