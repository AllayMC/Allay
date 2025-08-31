package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeprecatedPurpurBlock2Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemDeprecatedPurpurBlock2StackImpl extends ItemStackImpl implements ItemDeprecatedPurpurBlock2Stack {
    public ItemDeprecatedPurpurBlock2StackImpl(ItemStackInitInfo initInfo,
                                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
