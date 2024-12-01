package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlintAndSteelStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFlintAndSteelStackImpl extends ItemStackImpl implements ItemFlintAndSteelStack {
    public ItemFlintAndSteelStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
