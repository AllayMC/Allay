package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRawGoldBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRawGoldBlockStackImpl extends ItemStackImpl implements ItemRawGoldBlockStack {
    public ItemRawGoldBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
