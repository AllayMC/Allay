package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBedrockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBedrockStackImpl extends ItemStackImpl implements ItemBedrockStack {
    public ItemBedrockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
