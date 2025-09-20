package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChemicalHeatStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemChemicalHeatStackImpl extends ItemStackImpl implements ItemChemicalHeatStack {
    public ItemChemicalHeatStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
