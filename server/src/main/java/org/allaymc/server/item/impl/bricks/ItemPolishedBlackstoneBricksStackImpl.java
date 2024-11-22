package org.allaymc.server.item.impl.bricks;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.bricks.ItemPolishedBlackstoneBricksStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemPolishedBlackstoneBricksStackImpl extends ItemStackImpl implements ItemPolishedBlackstoneBricksStack {
    public ItemPolishedBlackstoneBricksStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
