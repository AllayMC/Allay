package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSculkShriekerStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSculkShriekerStackImpl extends ItemStackImpl implements ItemSculkShriekerStack {
    public ItemSculkShriekerStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
