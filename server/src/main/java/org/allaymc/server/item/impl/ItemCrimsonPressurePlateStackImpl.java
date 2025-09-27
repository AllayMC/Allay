package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCrimsonPressurePlateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCrimsonPressurePlateStackImpl extends ItemStackImpl implements ItemCrimsonPressurePlateStack {
    public ItemCrimsonPressurePlateStackImpl(ItemStackInitInfo initInfo,
                                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
