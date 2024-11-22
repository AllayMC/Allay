package org.allaymc.server.item.impl.wall;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.wall.ItemPolishedBlackstoneWallStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemPolishedBlackstoneWallStackImpl extends ItemStackImpl implements ItemPolishedBlackstoneWallStack {
    public ItemPolishedBlackstoneWallStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
