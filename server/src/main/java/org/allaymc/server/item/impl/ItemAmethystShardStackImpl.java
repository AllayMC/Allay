package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAmethystShardStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemAmethystShardStackImpl extends ItemStackImpl implements ItemAmethystShardStack {
    public ItemAmethystShardStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
