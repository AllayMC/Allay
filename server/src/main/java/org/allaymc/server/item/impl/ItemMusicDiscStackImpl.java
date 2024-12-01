package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemMusicDiscComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemMusicDiscStackImpl extends ItemStackImpl implements ItemMusicDiscStack {
    @Delegate
    protected ItemMusicDiscComponent musicDiscComponent;

    public ItemMusicDiscStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
