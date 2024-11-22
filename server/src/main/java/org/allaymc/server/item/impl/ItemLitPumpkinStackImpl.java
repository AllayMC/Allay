package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLitPumpkinStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemLitPumpkinStackImpl extends ItemStackImpl implements ItemLitPumpkinStack {
    public ItemLitPumpkinStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
