package org.allaymc.server.item.impl.coralwallfan;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.coralwallfan.ItemDeadBubbleCoralWallFanStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemDeadBubbleCoralWallFanStackImpl extends ItemStackImpl implements ItemDeadBubbleCoralWallFanStack {
    public ItemDeadBubbleCoralWallFanStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
