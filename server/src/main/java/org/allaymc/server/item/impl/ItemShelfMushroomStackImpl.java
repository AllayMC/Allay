package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemShelfMushroomStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemShelfMushroomStackImpl extends ItemStackImpl implements ItemShelfMushroomStack {
    public ItemShelfMushroomStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
