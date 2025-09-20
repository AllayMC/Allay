package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeprecatedPurpurBlock2Stack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDeprecatedPurpurBlock2StackImpl extends ItemStackImpl implements ItemDeprecatedPurpurBlock2Stack {
    public ItemDeprecatedPurpurBlock2StackImpl(ItemStackInitInfo initInfo,
                                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
