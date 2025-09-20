package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBrownDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBrownDyeStackImpl extends ItemStackImpl implements ItemBrownDyeStack {
    public ItemBrownDyeStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
