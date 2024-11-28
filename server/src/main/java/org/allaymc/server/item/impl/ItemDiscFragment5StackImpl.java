package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDiscFragment5Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDiscFragment5StackImpl extends ItemStackImpl implements ItemDiscFragment5Stack {
    public ItemDiscFragment5StackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
