package org.allaymc.server.item.impl.leaves;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.leaves.ItemAzaleaLeavesFloweredStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemAzaleaLeavesFloweredStackImpl extends ItemStackImpl implements ItemAzaleaLeavesFloweredStack {
    public ItemAzaleaLeavesFloweredStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
