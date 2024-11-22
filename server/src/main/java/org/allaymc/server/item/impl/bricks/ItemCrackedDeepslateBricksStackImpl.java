package org.allaymc.server.item.impl.bricks;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.bricks.ItemCrackedDeepslateBricksStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemCrackedDeepslateBricksStackImpl extends ItemStackImpl implements ItemCrackedDeepslateBricksStack {
    public ItemCrackedDeepslateBricksStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
