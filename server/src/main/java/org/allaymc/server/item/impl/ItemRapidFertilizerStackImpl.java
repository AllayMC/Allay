package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRapidFertilizerStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemRapidFertilizerStackImpl extends ItemStackImpl implements ItemRapidFertilizerStack {
    public ItemRapidFertilizerStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
