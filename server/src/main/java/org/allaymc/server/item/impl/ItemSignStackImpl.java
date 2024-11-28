package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSignStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemSignStackImpl extends ItemStackImpl implements ItemSignStack {
    public ItemSignStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemSignBaseComponent getBaseComponent() {
        return (ItemSignBaseComponent) super.getBaseComponent();
    }
}
