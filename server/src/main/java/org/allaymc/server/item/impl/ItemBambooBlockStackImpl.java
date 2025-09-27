package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBambooBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBambooBlockStackImpl extends ItemStackImpl implements ItemBambooBlockStack {
    public ItemBambooBlockStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
