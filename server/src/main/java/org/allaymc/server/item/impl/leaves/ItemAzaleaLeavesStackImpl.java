package org.allaymc.server.item.impl.leaves;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.leaves.ItemAzaleaLeavesStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemAzaleaLeavesStackImpl extends ItemStackImpl implements ItemAzaleaLeavesStack {
    public ItemAzaleaLeavesStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
