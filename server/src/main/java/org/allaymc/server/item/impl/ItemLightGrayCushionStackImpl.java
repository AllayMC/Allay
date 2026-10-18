package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLightGrayCushionStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemLightGrayCushionStackImpl extends ItemStackImpl implements ItemLightGrayCushionStack {
    public ItemLightGrayCushionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
