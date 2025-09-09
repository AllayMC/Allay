package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemMusicDiscStackImpl extends ItemStackImpl implements ItemMusicDiscStack {
    public ItemMusicDiscStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemMusicDiscBaseComponent getBaseComponent() {
        return (ItemMusicDiscBaseComponent) super.getBaseComponent();
    }
}
