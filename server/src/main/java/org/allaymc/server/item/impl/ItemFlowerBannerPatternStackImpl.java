package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlowerBannerPatternStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFlowerBannerPatternStackImpl extends ItemStackImpl implements ItemFlowerBannerPatternStack {
    public ItemFlowerBannerPatternStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
