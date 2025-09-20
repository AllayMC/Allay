package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPoweredComparatorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPoweredComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockPoweredComparatorBehavior {
    public BlockPoweredComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
