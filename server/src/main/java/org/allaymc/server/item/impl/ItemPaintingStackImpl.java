package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPaintingStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPaintingStackImpl extends ItemStackImpl implements ItemPaintingStack {
    public ItemPaintingStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
