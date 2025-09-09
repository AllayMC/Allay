package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlintAndSteelStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFlintAndSteelStackImpl extends ItemStackImpl implements ItemFlintAndSteelStack {
    public ItemFlintAndSteelStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
