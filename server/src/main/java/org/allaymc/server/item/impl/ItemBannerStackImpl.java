package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.api.item.interfaces.ItemBannerStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBannerStackImpl extends ItemStackImpl implements ItemBannerStack {
    @Delegate
    private ItemBannerBaseComponent bannerBaseComponent;

    public ItemBannerStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
