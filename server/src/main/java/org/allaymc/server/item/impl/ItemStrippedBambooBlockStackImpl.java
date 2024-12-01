package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStrippedBambooBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemStrippedBambooBlockStackImpl extends ItemStackImpl implements ItemStrippedBambooBlockStack {
    public ItemStrippedBambooBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
