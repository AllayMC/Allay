package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.item.interfaces.ItemDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDyeStackImpl extends ItemStackImpl implements ItemDyeStack {

    @Delegate
    private ItemDyeComponent dyeComponent;

    public ItemDyeStackImpl(ItemStackInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
