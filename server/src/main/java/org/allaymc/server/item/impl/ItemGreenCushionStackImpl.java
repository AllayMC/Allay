package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGreenCushionStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemGreenCushionStackImpl extends ItemStackImpl implements ItemGreenCushionStack {
    public ItemGreenCushionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
