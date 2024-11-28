package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPurpurStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPurpurStackImpl extends ItemStackImpl implements ItemPurpurStack {
    public ItemPurpurStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
