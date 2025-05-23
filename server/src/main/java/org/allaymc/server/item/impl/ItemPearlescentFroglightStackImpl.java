package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPearlescentFroglightStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPearlescentFroglightStackImpl extends ItemStackImpl implements ItemPearlescentFroglightStack {
    public ItemPearlescentFroglightStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
