package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLargeFernStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLargeFernStackImpl extends ItemStackImpl implements ItemLargeFernStack {
    public ItemLargeFernStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
