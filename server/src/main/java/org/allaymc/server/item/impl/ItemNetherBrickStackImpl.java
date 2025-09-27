package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherBrickStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemNetherBrickStackImpl extends ItemStackImpl implements ItemNetherBrickStack {
    public ItemNetherBrickStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
