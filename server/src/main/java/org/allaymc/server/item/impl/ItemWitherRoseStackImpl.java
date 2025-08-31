package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWitherRoseStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemWitherRoseStackImpl extends ItemStackImpl implements ItemWitherRoseStack {
    public ItemWitherRoseStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
