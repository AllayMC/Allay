package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.interfaces.ItemSignStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSignStackImpl extends ItemStackImpl implements ItemSignStack {

    @Delegate
    private ItemSignBaseComponent signBaseComponent;

    public ItemSignStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
