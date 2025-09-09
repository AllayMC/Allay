package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedOxidizedCopperGrateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWaxedOxidizedCopperGrateStackImpl extends ItemStackImpl implements ItemWaxedOxidizedCopperGrateStack {
    public ItemWaxedOxidizedCopperGrateStackImpl(ItemStackInitInfo initInfo,
                                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
