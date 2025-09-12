package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSweetBerryBushStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSweetBerryBushStackImpl extends ItemStackImpl implements ItemSweetBerryBushStack {
    public ItemSweetBerryBushStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
