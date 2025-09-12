package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPotatoesStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPotatoesStackImpl extends ItemStackImpl implements ItemPotatoesStack {
    public ItemPotatoesStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
