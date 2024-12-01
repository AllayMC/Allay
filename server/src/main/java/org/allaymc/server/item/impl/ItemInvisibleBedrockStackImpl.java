package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemInvisibleBedrockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemInvisibleBedrockStackImpl extends ItemStackImpl implements ItemInvisibleBedrockStack {
    public ItemInvisibleBedrockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
