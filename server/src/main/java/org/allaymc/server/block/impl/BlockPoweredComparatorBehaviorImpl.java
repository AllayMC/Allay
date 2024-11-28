package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPoweredComparatorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPoweredComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockPoweredComparatorBehavior {
    public BlockPoweredComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
