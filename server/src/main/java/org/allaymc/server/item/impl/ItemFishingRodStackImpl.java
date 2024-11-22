package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFishingRodStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFishingRodStackImpl extends ItemStackImpl implements ItemFishingRodStack {
    public ItemFishingRodStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
