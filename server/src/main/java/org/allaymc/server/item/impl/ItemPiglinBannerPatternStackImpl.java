package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPiglinBannerPatternStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPiglinBannerPatternStackImpl extends ItemStackImpl implements ItemPiglinBannerPatternStack {
    public ItemPiglinBannerPatternStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
