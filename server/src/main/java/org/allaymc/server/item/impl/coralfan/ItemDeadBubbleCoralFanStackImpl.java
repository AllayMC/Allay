package org.allaymc.server.item.impl.coralfan;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.coralfan.ItemDeadBubbleCoralFanStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemDeadBubbleCoralFanStackImpl extends ItemStackImpl implements ItemDeadBubbleCoralFanStack {
    public ItemDeadBubbleCoralFanStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
