package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBlastFurnaceStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBlastFurnaceStackImpl extends ItemStackImpl implements ItemBlastFurnaceStack {
    public ItemBlastFurnaceStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
