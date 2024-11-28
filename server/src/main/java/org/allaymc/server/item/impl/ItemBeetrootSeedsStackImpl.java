package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBeetrootSeedsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBeetrootSeedsStackImpl extends ItemStackImpl implements ItemBeetrootSeedsStack {
    public ItemBeetrootSeedsStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
