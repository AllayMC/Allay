package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlowerBannerPatternStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFlowerBannerPatternStackImpl extends ItemStackImpl implements ItemFlowerBannerPatternStack {
    public ItemFlowerBannerPatternStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
