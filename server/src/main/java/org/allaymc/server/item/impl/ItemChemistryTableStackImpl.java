package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChemistryTableStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemChemistryTableStackImpl extends ItemStackImpl implements ItemChemistryTableStack {
    public ItemChemistryTableStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
