package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedWeatheredCopperGrateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWaxedWeatheredCopperGrateStackImpl extends ItemStackImpl implements ItemWaxedWeatheredCopperGrateStack {
    public ItemWaxedWeatheredCopperGrateStackImpl(ItemStackInitInfo initInfo,
                                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
