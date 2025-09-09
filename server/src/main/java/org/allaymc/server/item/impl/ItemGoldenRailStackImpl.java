package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGoldenRailStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGoldenRailStackImpl extends ItemStackImpl implements ItemGoldenRailStack {
    public ItemGoldenRailStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
