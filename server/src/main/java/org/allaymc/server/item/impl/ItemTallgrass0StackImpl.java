package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTallgrass0Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemTallgrass0StackImpl extends ItemStackImpl implements ItemTallgrass0Stack {
    public ItemTallgrass0StackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
