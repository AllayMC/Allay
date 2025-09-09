package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemSoulCampfireStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemItemSoulCampfireStackImpl extends ItemStackImpl implements ItemItemSoulCampfireStack {
    public ItemItemSoulCampfireStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
