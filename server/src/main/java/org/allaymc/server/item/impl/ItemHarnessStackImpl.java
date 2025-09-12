package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHarnessStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHarnessStackImpl extends ItemStackImpl implements ItemHarnessStack {
    public ItemHarnessStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
