package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedExposedCopperGrateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWaxedExposedCopperGrateStackImpl extends ItemStackImpl implements ItemWaxedExposedCopperGrateStack {
    public ItemWaxedExposedCopperGrateStackImpl(ItemStackInitInfo initInfo,
                                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
