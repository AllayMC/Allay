package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChiseledBookshelfStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemChiseledBookshelfStackImpl extends ItemStackImpl implements ItemChiseledBookshelfStack {
    public ItemChiseledBookshelfStackImpl(ItemStackInitInfo initInfo,
                                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
