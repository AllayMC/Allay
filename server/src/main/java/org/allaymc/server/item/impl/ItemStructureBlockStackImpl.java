package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStructureBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemStructureBlockStackImpl extends ItemStackImpl implements ItemStructureBlockStack {
    public ItemStructureBlockStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
