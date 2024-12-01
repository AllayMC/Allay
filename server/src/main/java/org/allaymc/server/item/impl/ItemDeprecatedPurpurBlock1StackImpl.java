package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeprecatedPurpurBlock1Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDeprecatedPurpurBlock1StackImpl extends ItemStackImpl implements ItemDeprecatedPurpurBlock1Stack {
    public ItemDeprecatedPurpurBlock1StackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
