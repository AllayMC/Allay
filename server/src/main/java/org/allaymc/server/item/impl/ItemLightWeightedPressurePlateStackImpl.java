package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLightWeightedPressurePlateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLightWeightedPressurePlateStackImpl extends ItemStackImpl implements ItemLightWeightedPressurePlateStack {
    public ItemLightWeightedPressurePlateStackImpl(ItemStackInitInfo initInfo,
                                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
