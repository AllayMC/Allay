package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeprecatedPurpurBlock1Stack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDeprecatedPurpurBlock1StackImpl extends ItemStackImpl implements ItemDeprecatedPurpurBlock1Stack {
    public ItemDeprecatedPurpurBlock1StackImpl(ItemStackInitInfo initInfo,
                                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
