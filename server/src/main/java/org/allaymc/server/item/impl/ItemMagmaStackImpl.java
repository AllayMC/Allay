package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMagmaStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMagmaStackImpl extends ItemStackImpl implements ItemMagmaStack {
    public ItemMagmaStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
