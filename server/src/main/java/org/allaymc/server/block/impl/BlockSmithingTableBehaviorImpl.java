package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSmithingTableBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmithingTableBehaviorImpl extends BlockBehaviorImpl implements BlockSmithingTableBehavior {
    public BlockSmithingTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
