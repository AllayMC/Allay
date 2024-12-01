package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedstoneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRedstoneStackImpl extends ItemStackImpl implements ItemRedstoneStack {
    public ItemRedstoneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
