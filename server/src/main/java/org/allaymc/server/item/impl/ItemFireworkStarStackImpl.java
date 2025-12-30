package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkStarBaseComponent;
import org.allaymc.api.item.interfaces.ItemFireworkStarStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFireworkStarStackImpl extends ItemStackImpl implements ItemFireworkStarStack {

    @Delegate
    private ItemFireworkStarBaseComponent fireworkStarBaseComponent;

    public ItemFireworkStarStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
