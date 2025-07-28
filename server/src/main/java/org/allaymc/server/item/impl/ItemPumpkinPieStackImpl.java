package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPumpkinPieStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemPumpkinPieStackImpl extends ItemStackImpl implements ItemPumpkinPieStack {
    @Delegate
    protected ItemEdibleComponent edibleComponent;

    public ItemPumpkinPieStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
