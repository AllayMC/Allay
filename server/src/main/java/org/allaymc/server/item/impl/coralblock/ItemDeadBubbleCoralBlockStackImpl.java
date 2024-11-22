package org.allaymc.server.item.impl.coralblock;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.coralblock.ItemDeadBubbleCoralBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemDeadBubbleCoralBlockStackImpl extends ItemStackImpl implements ItemDeadBubbleCoralBlockStack {
    public ItemDeadBubbleCoralBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
