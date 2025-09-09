package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGrindstoneStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGrindstoneStackImpl extends ItemStackImpl implements ItemGrindstoneStack {
    public ItemGrindstoneStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
