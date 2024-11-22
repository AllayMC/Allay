package org.allaymc.server.item.impl.fence;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.fence.ItemNetherBrickFenceStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemNetherBrickFenceStackImpl extends ItemStackImpl implements ItemNetherBrickFenceStack {
    public ItemNetherBrickFenceStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
