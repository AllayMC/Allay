package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFletchingTableBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFletchingTableBehaviorImpl extends BlockBehaviorImpl implements BlockFletchingTableBehavior {
    public BlockFletchingTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
