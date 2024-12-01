package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedDyeStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRedDyeStackImpl extends ItemStackImpl implements ItemRedDyeStack {
    public ItemRedDyeStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
