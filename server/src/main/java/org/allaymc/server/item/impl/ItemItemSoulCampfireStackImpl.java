package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemSoulCampfireStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemItemSoulCampfireStackImpl extends ItemStackImpl implements ItemItemSoulCampfireStack {
    public ItemItemSoulCampfireStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
