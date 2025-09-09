package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemResinBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemResinBlockStackImpl extends ItemStackImpl implements ItemResinBlockStack {
    public ItemResinBlockStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
