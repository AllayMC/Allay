package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCrimsonHyphaeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCrimsonHyphaeStackImpl extends ItemStackImpl implements ItemCrimsonHyphaeStack {
    public ItemCrimsonHyphaeStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
