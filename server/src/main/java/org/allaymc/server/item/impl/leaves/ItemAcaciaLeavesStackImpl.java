package org.allaymc.server.item.impl.leaves;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.leaves.ItemAcaciaLeavesStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemAcaciaLeavesStackImpl extends ItemStackImpl implements ItemAcaciaLeavesStack {
    public ItemAcaciaLeavesStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
