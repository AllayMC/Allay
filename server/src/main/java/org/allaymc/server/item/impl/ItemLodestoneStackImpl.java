package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLodestoneStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLodestoneStackImpl extends ItemStackImpl implements ItemLodestoneStack {
    public ItemLodestoneStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
