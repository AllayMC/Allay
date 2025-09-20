package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStainedGlassPaneStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemStainedGlassPaneStackImpl extends ItemStackImpl implements ItemStainedGlassPaneStack {
    public ItemStainedGlassPaneStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
