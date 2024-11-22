package org.allaymc.server.item.impl.fencegate;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.fencegate.ItemCrimsonFenceGateStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemCrimsonFenceGateStackImpl extends ItemStackImpl implements ItemCrimsonFenceGateStack {
    public ItemCrimsonFenceGateStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
