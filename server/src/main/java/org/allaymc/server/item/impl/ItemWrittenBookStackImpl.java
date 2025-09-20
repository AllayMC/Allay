package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWrittenBookBaseComponent;
import org.allaymc.api.item.interfaces.ItemWrittenBookStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWrittenBookStackImpl extends ItemStackImpl implements ItemWrittenBookStack {
    public ItemWrittenBookStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemWrittenBookBaseComponent getBaseComponent() {
        return (ItemWrittenBookBaseComponent) super.getBaseComponent();
    }
}
