package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLitRedstoneOreStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemLitRedstoneOreStackImpl extends ItemStackImpl implements ItemLitRedstoneOreStack {
    public ItemLitRedstoneOreStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
