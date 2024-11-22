package org.allaymc.server.item.impl.stairs;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stairs.ItemStoneBrickStairsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemStoneBrickStairsStackImpl extends ItemStackImpl implements ItemStoneBrickStairsStack {
    public ItemStoneBrickStairsStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
