package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLitFurnaceStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemLitFurnaceStackImpl extends ItemStackImpl implements ItemLitFurnaceStack {
    public ItemLitFurnaceStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
