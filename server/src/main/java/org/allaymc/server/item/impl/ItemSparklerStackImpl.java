package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSparklerStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemSparklerStackImpl extends ItemStackImpl implements ItemSparklerStack {
    public ItemSparklerStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
