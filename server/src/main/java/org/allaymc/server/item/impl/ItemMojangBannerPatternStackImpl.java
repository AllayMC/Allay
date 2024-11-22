package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMojangBannerPatternStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMojangBannerPatternStackImpl extends ItemStackImpl implements ItemMojangBannerPatternStack {
    public ItemMojangBannerPatternStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
