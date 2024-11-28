package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedWeatheredCopperGrateStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemWaxedWeatheredCopperGrateStackImpl extends ItemStackImpl implements ItemWaxedWeatheredCopperGrateStack {
    public ItemWaxedWeatheredCopperGrateStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
