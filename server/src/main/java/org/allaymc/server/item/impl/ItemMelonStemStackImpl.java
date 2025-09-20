package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMelonStemStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemMelonStemStackImpl extends ItemStackImpl implements ItemMelonStemStack {
    public ItemMelonStemStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
