package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBannerPatternStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBannerPatternStackImpl extends ItemStackImpl implements ItemBannerPatternStack {
    public ItemBannerPatternStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
