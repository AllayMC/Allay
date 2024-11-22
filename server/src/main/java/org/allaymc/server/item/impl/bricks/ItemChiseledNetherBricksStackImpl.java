package org.allaymc.server.item.impl.bricks;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.bricks.ItemChiseledNetherBricksStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemChiseledNetherBricksStackImpl extends ItemStackImpl implements ItemChiseledNetherBricksStack {
    public ItemChiseledNetherBricksStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
