package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkRocketBaseComponent;
import org.allaymc.api.item.interfaces.ItemFireworkRocketStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFireworkRocketStackImpl extends ItemStackImpl implements ItemFireworkRocketStack {
    public ItemFireworkRocketStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemFireworkRocketBaseComponent getBaseComponent() {
        return (ItemFireworkRocketBaseComponent) super.getBaseComponent();
    }
}
